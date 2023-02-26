export interface IProduct {
  id?: number;
  name?: string;
  point?: number;
  description?: string | null;
  liked?: number;
  shopifyId?: number | null;
  vendorId?: number | null;
  categoryId?: number | null;
  isCombo?: number | null;
  discount?: number | null;
  bought?: number;
  price?: number | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public point?: number,
    public description?: string | null,
    public liked?: number,
    public shopifyId?: number | null,
    public vendorId?: number | null,
    public categoryId?: number | null,
    public isCombo?: number | null,
    public discount?: number | null,
    public bought?: number,
    public price?: number | null
  ) {}
}
