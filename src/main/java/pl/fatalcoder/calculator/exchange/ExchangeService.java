package pl.fatalcoder.calculator.exchange;

import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class ExchangeService {
  private final ExchangeRateProvider exchangeRateProvider;

  public ExchangeService(ExchangeRateProvider exchangeRateProvider) {
    this.exchangeRateProvider = exchangeRateProvider;
  }

  public float exchange(float amount, Currency inputCurrency, Currency resultCurrency) {
    float exchangeRate = exchangeRateProvider.getExchangeRate(inputCurrency, resultCurrency);

    return amount * exchangeRate;
  }
}
