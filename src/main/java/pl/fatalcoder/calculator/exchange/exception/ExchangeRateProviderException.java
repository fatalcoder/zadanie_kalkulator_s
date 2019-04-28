package pl.fatalcoder.calculator.exchange.exception;

public class ExchangeRateProviderException extends RuntimeException {
  public ExchangeRateProviderException(String s) {
    super(s);
  }

  public ExchangeRateProviderException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
