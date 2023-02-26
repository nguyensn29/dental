export interface ICategory {
  id?: number;
  name?: string;
  type?: number;
  icon?: string | null;
  imgPublicId?: string | null;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public name?: string,
    public type?: number,
    public icon?: string | null,
    public imgPublicId?: string | null
  ) {}
}
