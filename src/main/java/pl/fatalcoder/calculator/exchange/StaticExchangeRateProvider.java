package pl.fatalcoder.calculator.exchange;

import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@Service(value = "StaticRates")
public class StaticExchangeRateProvider implements ExchangeRateProvider {
  private static final Map<String, Double> rates = new HashMap<>();

  static {
    rates.put("EUR-PLN", 4.2878);
    rates.put("EUR-GBP", 0.8634);
    rates.put("EUR-EUR", 1.0);
    rates.put("PLN-PLN", 1.0);
    rates.put("PLN-EUR", 0.2332);
    rates.put("PLN-GBP", 0.2014);
    rates.put("GBP-GBP", 1.0);
    rates.put("GBP-EUR", 1.1582);
    rates.put("GBP-PLN", 4.9662);
  }

  @Override
  public float getExchangeRate(Currency from, Currency to) {
    return rates.get(from.toString() + "-" + to.toString())
        .floatValue();
  }
}
