package pl.fatalcoder.calculator.exchange;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.fatalcoder.calculator.exchange.dto.ExchangeRates;
import pl.fatalcoder.calculator.exchange.exception.ExchangeRateProviderException;

import java.util.Currency;

@Service(value = "ExchangeRatesApi")
public class ExchangeRatesApiExchangeRateProvider implements ExchangeRateProvider {
  private static final String BASE_URL = "https://api.exchangeratesapi.io";
  private static final String ENDPOINT_LATEST = "latest";
  private static final String PARAM_BASE = "base";
  private static final String PARAM_SYMBOLS = "symbols";

  private final WebClient webClient;

  public ExchangeRatesApiExchangeRateProvider(WebClient.Builder webClientBuilder) {
    webClient = webClientBuilder.baseUrl(BASE_URL)
        .build();
  }

  @Override
  public float getExchangeRate(Currency from, Currency to) {
    try {
      ExchangeRates ratesResponse = webClient.get()
          .uri(uriBuilder -> uriBuilder.path(ENDPOINT_LATEST)
              .queryParam(PARAM_BASE, from.getCurrencyCode())
              .queryParam(PARAM_SYMBOLS, to.getCurrencyCode())
              .build())
          .retrieve()
          .bodyToMono(ExchangeRates.class)
          .block();

      if (ratesResponse == null) {
        throw new ExchangeRateProviderException("Response body is invalid. Could not parse.");
      }

      return ratesResponse.getRate(to);
    } catch (WebClientResponseException cause) {
      throw new ExchangeRateProviderException("Invalid api response", cause);
    }
  }
}
