import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Amount, Country } from "./model";
import { map, mergeMap, toArray } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  private static readonly BASE_URL = "http://localhost:8080/v1";
  private static readonly COUNTRIES_URL = BackendService.BASE_URL + "/countries";
  private static readonly INCOME_CALCULATOR_URL = BackendService.BASE_URL + "/income/calculations";

  constructor(private httpClient: HttpClient) {
  }

  getCountries(): Observable<Country[]> {
    return this.httpClient.get(BackendService.COUNTRIES_URL)
      .pipe(
        mergeMap((items: any[]) => items),
        map(item => new Country(
          item['name'],
          item['code'],
          item['tax'],
          item['constantCosts'],
          item['currency']
        )),
        toArray()
      );
  }

  getIncomeCalculation(dailyIncome: number, countryCode: string): Observable<Amount> {
    const payload = {
      dailyIncomeWithTax: dailyIncome,
      countryCode: countryCode,
    };

    return this.httpClient.post(BackendService.INCOME_CALCULATOR_URL, payload)
      .pipe(
        map(data => new Amount(data['value'], data['currency']))
      );
  }
}
