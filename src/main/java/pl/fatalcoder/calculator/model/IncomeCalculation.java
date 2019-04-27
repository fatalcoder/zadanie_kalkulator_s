package pl.fatalcoder.calculator.model;

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
