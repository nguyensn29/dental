export interface ICart {
  id?: number;
  userId?: number;
  productId?: number;
  variantId?: number | null;
  productType?: number;
  qty?: number;
}

export class Cart implements ICart {
  constructor(
    public id?: number,
    public userId?: number,
    public productId?: number,
    public variantId?: number | null,
    public productType?: number,
    public qty?: number
  ) {}
}
