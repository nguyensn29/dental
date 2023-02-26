export interface IBlog {
  id?: number;
  name?: string;
  content?: string | null;
  avatar?: string | null;
  imgPublicId?: string | null;
  categoryId?: number;
  isShow?: number;
}

export class Blog implements IBlog {
  constructor(
    public id?: number,
    public name?: string,
    public content?: string | null,
    public avatar?: string | null,
    public imgPublicId?: string | null,
    public categoryId?: number,
    public isShow?: number
  ) {}
}
