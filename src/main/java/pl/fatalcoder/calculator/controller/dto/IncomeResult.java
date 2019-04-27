package pl.fatalcoder.calculator.controller.dto;

import java.util.Currency;

public class IncomeResult {
  private final float value;
  private final Currency currency;

  public IncomeResult(float value, Currency currency) {
    this.value = value;
    this.currency = currency;
  }

  public float getValue() {
    return value;
  }

  public Currency getCurrency() {
    return currency;
  }
}
