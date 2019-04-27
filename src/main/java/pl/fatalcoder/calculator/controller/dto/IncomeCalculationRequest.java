package pl.fatalcoder.calculator.controller.dto;

public class IncomeCalculationRequest {
  private final String countryCode;
  private final int dailyIncomeWithTax;

  public IncomeCalculationRequest(String countryCode, int dailyIncomeWithTax) {
    this.countryCode = countryCode;
    this.dailyIncomeWithTax = dailyIncomeWithTax;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public int getDailyIncomeWithTax() {
    return dailyIncomeWithTax;
  }
}
