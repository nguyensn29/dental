export interface IUserPayment {
  id?: number;
  userId?: number;
  name?: string | null;
  phone?: string | null;
  adress?: string | null;
  provincial?: string | null;
  district?: string | null;
  ward?: string | null;
  email?: string | null;
  street?: string | null;
}

export class UserPayment implements IUserPayment {
  constructor(
    public id?: number,
    public userId?: number,
    public name?: string | null,
    public phone?: string | null,
    public adress?: string | null,
    public provincial?: string | null,
    public district?: string | null,
    public ward?: string | null,
    public email?: string | null,
    public street?: string | null
  ) {}
}
