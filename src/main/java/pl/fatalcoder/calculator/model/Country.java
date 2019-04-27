package pl.fatalcoder.calculator.model;

public class Country {
  private final String name;
  private final String code;
  private final int tax;
  private final Amount costs;

  public Country(String name, String code, int tax, Amount costs) {
    this.name = name;
    this.code = code;
    this.tax = tax;
    this.costs = costs;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public int getTax() {
    return tax;
  }

  public Amount getCosts() {
    return costs;
  }
}
