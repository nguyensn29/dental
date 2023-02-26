export interface IVendor {
  id?: number;
  name?: string;
}

export class Vendor implements IVendor {
  constructor(public id?: number, public name?: string) {}
}
