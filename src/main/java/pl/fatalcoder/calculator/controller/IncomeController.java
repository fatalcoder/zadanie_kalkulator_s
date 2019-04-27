package pl.fatalcoder.calculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fatalcoder.calculator.command.IncomeCalculationCommand;
import pl.fatalcoder.calculator.controller.dto.IncomeCalculation;
import pl.fatalcoder.calculator.controller.dto.IncomeResult;
import pl.fatalcoder.calculator.model.Amount;
import pl.fatalcoder.calculator.model.Country;
import pl.fatalcoder.calculator.repository.CountryRepository;

import java.util.Currency;

@RestController
@RequestMapping(value = "/v1/income")
public class IncomeController {
  private final CountryRepository countryRepository;
  private final IncomeCalculationCommand command;

  public IncomeController(
      CountryRepository countryRepository,
      IncomeCalculationCommand command
  ) {
    this.countryRepository = countryRepository;
    this.command = command;
  }

  @PostMapping(value = "/calculations")
  public IncomeResult calculateResult() {
    Currency pln = Currency.getInstance("PLN");
    Currency euro = Currency.getInstance("EUR");
    Amount dailyIncomeWithTax = new Amount(100, euro);
    Country germany = countryRepository.getCountryByCode("DE");

    IncomeCalculation input = new IncomeCalculation(dailyIncomeWithTax, germany);

    return command.execute(input, pln);
  }
}
