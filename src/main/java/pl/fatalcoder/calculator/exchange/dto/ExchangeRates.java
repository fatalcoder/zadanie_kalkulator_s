package pl.fatalcoder.calculator.exchange.dto;

import pl.fatalcoder.calculator.exchange.exception.MissingExchangeRate;

import java.util.Currency;
import java.util.Map;

public class ExchangeRates {
  private final Currency base;
  private final Map<Currency, Double> rates;

  public ExchangeRates(Currency base, Map<Currency, Double> rates) {
    this.base = base;
    this.rates = rates;
  }

  public Currency getBase() {
    return base;
  }

  public float getRate(Currency currency) {
    Double rate = rates.get(currency);

    if (rate == null) {
      throw new MissingExchangeRate(currency);
    }

    return rate.floatValue();
  }
}
