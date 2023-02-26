export interface IOrderDetails {
  id?: number;
  orderId?: number;
  productId?: number;
  productType?: number;
  avatar?: string | null;
  name?: string | null;
  price?: number;
  point?: number;
  qty?: number;
  discount?: number;
  subtotalAmount?: number;
  totalAmount?: number;
  variantId?: number | null;
  shopifyVariantId?: number | null;
  shopifyProductId?: number | null;
  shopifyOrderId?: number | null;
  shopifyId?: number | null;
}

export class OrderDetails implements IOrderDetails {
  constructor(
    public id?: number,
    public orderId?: number,
    public productId?: number,
    public productType?: number,
    public avatar?: string | null,
    public name?: string | null,
    public price?: number,
    public point?: number,
    public qty?: number,
    public discount?: number,
    public subtotalAmount?: number,
    public totalAmount?: number,
    public variantId?: number | null,
    public shopifyVariantId?: number | null,
    public shopifyProductId?: number | null,
    public shopifyOrderId?: number | null,
    public shopifyId?: number | null
  ) {}
}
