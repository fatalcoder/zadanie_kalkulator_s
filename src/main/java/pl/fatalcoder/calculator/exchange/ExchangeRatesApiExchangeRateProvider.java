package pl.fatalcoder.calculator.exchange;

import org.springframework.stereotype.Service;

import java.util.Currency;

@Service(value = "ExchangeRatesApi")
public class ExchangeRatesApiExchangeRateProvider implements ExchangeRateProvider {
  @Override
  public float getExchangeRate(Currency from, Currency to) {
    // https://api.exchangeratesapi.io/latest?base=FROM&symbols=TO
    return (float) 1.0;
  }
}
