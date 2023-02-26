export interface IProductUser {
  id?: number;
  userId?: number;
  productId?: number;
  productType?: number;
}

export class ProductUser implements IProductUser {
  constructor(public id?: number, public userId?: number, public productId?: number, public productType?: number) {}
}
