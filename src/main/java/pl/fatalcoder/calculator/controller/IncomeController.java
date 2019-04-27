package pl.fatalcoder.calculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fatalcoder.calculator.command.IncomeCalculationCommand;
import pl.fatalcoder.calculator.controller.dto.IncomeCalculationRequest;
import pl.fatalcoder.calculator.controller.dto.IncomeResult;
import pl.fatalcoder.calculator.model.Amount;
import pl.fatalcoder.calculator.model.Country;
import pl.fatalcoder.calculator.model.IncomeCalculation;
import pl.fatalcoder.calculator.repository.CountryRepository;

import javax.validation.Valid;

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
  public IncomeResult calculateResult(@Valid @RequestBody IncomeCalculationRequest request) {
    Country country = countryRepository.getCountryByCode(request.getCountryCode());
    Amount dailyIncomeWithTax = new Amount(
        request.getDailyIncomeWithTax(),
        country.getCurrency()
    );

    IncomeCalculation input = new IncomeCalculation(dailyIncomeWithTax, country);

    return command.execute(input);
  }
}
