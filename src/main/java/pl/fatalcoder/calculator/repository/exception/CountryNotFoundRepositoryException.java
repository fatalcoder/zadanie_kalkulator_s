package pl.fatalcoder.calculator.repository.exception;

public class CountryNotFoundRepositoryException extends RepositoryException {
  public CountryNotFoundRepositoryException(String code) {
    super("Country with \"" + code + "\" not found");
  }
}
