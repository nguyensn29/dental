export interface IVariant {
  id?: number;
  productId?: number;
  shopifyId?: number | null;
  title?: string;
  price?: number;
  discount?: number;
  option1?: string | null;
  option2?: string | null;
  option3?: string | null;
  imageId?: number | null;
  weight?: number;
  compareAtPrice?: number | null;
}

export class Variant implements IVariant {
  constructor(
    public id?: number,
    public productId?: number,
    public shopifyId?: number | null,
    public title?: string,
    public price?: number,
    public discount?: number,
    public option1?: string | null,
    public option2?: string | null,
    public option3?: string | null,
    public imageId?: number | null,
    public weight?: number,
    public compareAtPrice?: number | null
  ) {}
}
