package pl.fatalcoder.calculator.model;

import java.util.Currency;

public class Country {
  private final String name;
  private final String code;
  private final int tax;
  private final int constantCosts;
  private final Currency currency;

  public Country(String name, String code, int tax, int constantCosts, Currency currency) {
    this.name = name;
    this.code = code;
    this.tax = tax;
    this.constantCosts = constantCosts;
    this.currency = currency;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public int getTax() {
    return tax;
  }

  public int getConstantCosts() {
    return constantCosts;
  }

  public Currency getCurrency() {
    return currency;
  }
}
