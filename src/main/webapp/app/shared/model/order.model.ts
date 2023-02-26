export interface IOrder {
  id?: number;
  orderCode?: string;
  transId?: string | null;
  paymentStatus?: number;
  totalAmount?: number;
  totalDiscount?: number;
  point?: number;
  userId?: number | null;
  payMethod?: number;
  shippingDate?: Date | null;
  shippingStatus?: number;
  deliveryCode?: string | null;
  name?: string | null;
  phone?: string | null;
  address?: string | null;
  provincial?: string | null;
  district?: string | null;
  shopifyId?: number | null;
  shopifyUserId?: number | null;
  totalWeight?: number | null;
  fulfillmentStatus?: string | null;
  gateway?: string | null;
  ward?: string | null;
  street?: string | null;
  fulfillmentDate?: Date | null;
  cancelledAt?: Date | null;
  cancelReason?: string | null;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public orderCode?: string,
    public transId?: string | null,
    public paymentStatus?: number,
    public totalAmount?: number,
    public totalDiscount?: number,
    public point?: number,
    public userId?: number | null,
    public payMethod?: number,
    public shippingDate?: Date | null,
    public shippingStatus?: number,
    public deliveryCode?: string | null,
    public name?: string | null,
    public phone?: string | null,
    public address?: string | null,
    public provincial?: string | null,
    public district?: string | null,
    public shopifyId?: number | null,
    public shopifyUserId?: number | null,
    public totalWeight?: number | null,
    public fulfillmentStatus?: string | null,
    public gateway?: string | null,
    public ward?: string | null,
    public street?: string | null,
    public fulfillmentDate?: Date | null,
    public cancelledAt?: Date | null,
    public cancelReason?: string | null
  ) {}
}
