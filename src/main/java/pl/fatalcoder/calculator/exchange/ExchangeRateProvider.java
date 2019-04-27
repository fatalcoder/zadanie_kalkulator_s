package pl.fatalcoder.calculator.exchange;

import java.util.Currency;

public interface ExchangeRateProvider {
  float getExchangeRate(Currency from, Currency to);
}
