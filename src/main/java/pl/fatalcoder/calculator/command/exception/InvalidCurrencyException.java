package pl.fatalcoder.calculator.command.exception;

public class InvalidCurrencyException extends RuntimeException {
  public InvalidCurrencyException() {
    super("Income must be in the same currency as country where it was earned");
  }
}
