package pl.fatalcoder.calculator.command;

import org.springframework.stereotype.Service;
import pl.fatalcoder.calculator.command.exception.InvalidCurrencyException;
import pl.fatalcoder.calculator.controller.dto.IncomeResult;
import pl.fatalcoder.calculator.exchange.ExchangeService;
import pl.fatalcoder.calculator.model.Amount;
import pl.fatalcoder.calculator.model.Country;
import pl.fatalcoder.calculator.model.IncomeCalculation;

import java.util.Currency;

@Service
public class IncomeCalculationCommand {
  private static final Currency RESULT_CURRENCY = Currency.getInstance("PLN");
  private static final int DAYS_IN_MONTH = 22;

  private final ExchangeService exchangeService;

  public IncomeCalculationCommand(ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  public IncomeResult execute(IncomeCalculation income) {
    final Amount dailyIncomeWithTax = income.getDailyIncomeWithTax();
    final Country country = income.getCountry();

    if (dailyIncomeWithTax.getCurrency() != country.getCurrency()) {
      throw new InvalidCurrencyException();
    }

    int monthlyIncomeWithTax = DAYS_IN_MONTH * dailyIncomeWithTax.getAmount();
    float incomeTax = round(getTaxPercentage(country) * monthlyIncomeWithTax);

    float incomeWithoutTax = monthlyIncomeWithTax - incomeTax - country.getConstantCosts();

    float incomeInNewCurrency = exchangeService.exchange(
        incomeWithoutTax,
        dailyIncomeWithTax.getCurrency(),
        RESULT_CURRENCY
    );

    return new IncomeResult(round(incomeInNewCurrency), RESULT_CURRENCY);
  }

  private float getTaxPercentage(Country country) {
    return (float) (country.getTax() / 100.0);
  }

  private float round(float value) {
    return Math.round(value * 100) / 100;
  }
}
