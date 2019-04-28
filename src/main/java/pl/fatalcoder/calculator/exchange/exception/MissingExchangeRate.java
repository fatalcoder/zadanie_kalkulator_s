package pl.fatalcoder.calculator.exchange.exception;

import java.util.Currency;

public class MissingExchangeRate extends RuntimeException {
  public MissingExchangeRate(Currency currency) {
    super("Missing exchange rate for currency: " + currency);
  }
}
