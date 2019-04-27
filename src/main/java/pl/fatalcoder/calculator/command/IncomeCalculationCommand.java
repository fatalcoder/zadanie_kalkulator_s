package pl.fatalcoder.calculator.command;

import org.springframework.stereotype.Service;
import pl.fatalcoder.calculator.controller.dto.IncomeCalculation;
import pl.fatalcoder.calculator.controller.dto.IncomeResult;
import pl.fatalcoder.calculator.exchange.ExchangeService;

import java.util.Currency;

@Service
public class IncomeCalculationCommand {
  private final ExchangeService exchangeService;

  public IncomeCalculationCommand(ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  public IncomeResult execute(IncomeCalculation income, Currency resultCurrency) {
    int daysInMonth = 22;

    final Currency incomeCurrency = income.getDailyIncomeWithTax()
        .getCurrency();

    int incomeWithTax = daysInMonth * income.getDailyIncomeWithTax()
        .getAmount();

    double tax = income.getCountry().getTax() / 100.0;

    double incomeTax = tax * incomeWithTax;

    float incomeWithoutTax = (float) (incomeWithTax - incomeTax - income.getCountry().getCosts().getAmount());

    float incomeInNewCurrency = exchangeService.exchange(
        incomeWithoutTax,
        incomeCurrency,
        resultCurrency
    );

    return new IncomeResult(incomeInNewCurrency, resultCurrency);
  }
}
