export interface ICollection {
  id?: number;
  shopifyId?: number | null;
  title?: string;
  description?: string | null;
  handle?: string | null;
}

export class Collection implements ICollection {
  constructor(
    public id?: number,
    public shopifyId?: number | null,
    public title?: string,
    public description?: string | null,
    public handle?: string | null
  ) {}
}
