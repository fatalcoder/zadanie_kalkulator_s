package pl.fatalcoder.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fatalcoder.calculator.model.Country;
import pl.fatalcoder.calculator.repository.CountryRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/countries")
public class CountryController {
  private final CountryRepository countryRepository;

  public CountryController(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @GetMapping(value = "")
  public List<Country> getCountries() {
    return countryRepository.getCountries();
  }
}
