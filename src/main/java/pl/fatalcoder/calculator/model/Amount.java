package pl.fatalcoder.calculator.model;

import java.util.Currency;

public class Amount {
  private final int amount;
  private final Currency currency;

  public Amount(int amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public int getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }
}
