package pl.fatalcoder.calculator.repository;

import org.springframework.stereotype.Repository;
import pl.fatalcoder.calculator.model.Amount;
import pl.fatalcoder.calculator.model.Country;
import pl.fatalcoder.calculator.repository.exception.CountryNotFoundRepositoryException;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CountryRepository {
  static private final List<Country> countries = new LinkedList<>();

  static {
    countries.add(
        new Country(
            "United Kingdom",
            "UK",
            25,
            new Amount(600, Currency.getInstance("GBP"))
        )
    );
    countries.add(
        new Country(
            "Deutschland",
            "DE",
            20,
            new Amount(800, Currency.getInstance("EUR"))
        )
    );
    countries.add(
        new Country(
            "Polska",
            "PL",
            19,
            new Amount(1200, Currency.getInstance("PLN"))
        )
    );
  }

  public List<Country> getCountries() {
    return countries;
  }

  public Country getCountryByCode(String code) {
    return countries.stream()
        .filter(country -> country.getCode().equals(code))
        .findFirst()
        .orElseThrow(() -> new CountryNotFoundRepositoryException(code));
  }
}
