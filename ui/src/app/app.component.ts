import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { BackendService } from "./backend.service";
import { Country } from "./model";
import { Observable } from "rxjs";
import { filter, map, mergeMap, shareReplay, switchMap } from "rxjs/operators";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private countryCode = new FormControl('', [Validators.required]);
  private dailyIncome = new FormControl('', [Validators.required, Validators.pattern("[0-9]+")]);
  private incomeForm = new FormGroup({
    countryCode: this.countryCode,
    dailyIncome: this.dailyIncome
  });

  private counties$: Observable<Country[]>;
  private selectedCountryCurrencyCode$: Observable<string>;
  private monthlyIncome: string = "";
  private errors: string[] = [];

  constructor(private backendService: BackendService) {
    this.counties$ = this.backendService.getCountries().pipe(shareReplay());

    this.selectedCountryCurrencyCode$ = this.countryCode
      .valueChanges
      .pipe(
        switchMap(code => this.countryCurrencyByCode(code)),
        shareReplay()
      );
  }

  private calculateIncome() {
    this.errors = [];
    const income = parseInt(this.dailyIncome.value);

    this.backendService.getIncomeCalculation(income, this.countryCode.value)
      .pipe(
        map(amount => `${amount.value} ${amount.currency}`)
      )
      .subscribe(
        value => {
          this.monthlyIncome = value;
        },
        (error: HttpErrorResponse) => {
          this.errors.push(`Calculation failed because: "${error.message}"`);
        }
      );
  }

  private countryCurrencyByCode(countryCode: string): Observable<string> {
    return this.counties$.pipe(
      mergeMap(items => items),
      filter((country: Country) => country.code == countryCode),
      map(country => country.currency)
    );
  }

  private hasErrors(): boolean {
    return this.errors.length > 0 || !this.incomeForm.valid;
  }

  private getErrors(): string[] {
    const allErrors = [];
    allErrors.concat(this.errors);

    if (!this.countryCode.valid) {
      allErrors.push('Country code field is invalid');
    }

    if (!this.dailyIncome.valid) {
      allErrors.push('Daily income field is invalid');
    }

    return allErrors;
  }
}
