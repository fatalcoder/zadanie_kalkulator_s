package pl.fatalcoder.calculator.repository.exception;

public class RepositoryException extends RuntimeException {
  public RepositoryException(String s) {
    super(s);
  }

  public RepositoryException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
