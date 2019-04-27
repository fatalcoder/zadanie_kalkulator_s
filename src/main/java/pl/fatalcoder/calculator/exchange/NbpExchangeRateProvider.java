package pl.fatalcoder.calculator.exchange;

import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class NbpExchangeRateProvider implements ExchangeRateProvider {
  @Override
  public float getExchangeRate(Currency from, Currency to) {
    return (float) 4.2;
  }
}
