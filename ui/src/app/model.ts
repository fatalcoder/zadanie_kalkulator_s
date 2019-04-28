export class Amount {
  readonly value: number;
  readonly currency: string;

  constructor(amount: number, currency: string) {
    this.value = amount;
    this.currency = currency;
  }
}

export class Country {
  readonly name: string;
  readonly code: string;
  readonly tax: number;
  readonly constantCosts: number;
  readonly currency: string;

  constructor(name: string, code: string, tax: number, constantCosts: number, currency: string) {
    this.name = name;
    this.code = code;
    this.tax = tax;
    this.constantCosts = constantCosts;
    this.currency = currency;
  }
}
