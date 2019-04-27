package pl.fatalcoder.calculator.controller.dto;

import pl.fatalcoder.calculator.model.Amount;
import pl.fatalcoder.calculator.model.Country;

public class IncomeCalculation {
  private final Amount dailyIncomeWithTax;
  private final Country country;

  public IncomeCalculation(Amount dailyIncomeWithTax, Country country) {
    this.dailyIncomeWithTax = dailyIncomeWithTax;
    this.country = country;
  }

  public Amount getDailyIncomeWithTax() {
    return dailyIncomeWithTax;
  }

  public Country getCountry() {
    return country;
  }
}
