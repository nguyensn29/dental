export interface IBanner {
  id?: number;
  name?: string;
  src?: string;
  isShow?: number;
  imgPublicId?: string | null;
}

export class Banner implements IBanner {
  constructor(public id?: number, public name?: string, public src?: string, public isShow?: number, public imgPublicId?: string | null) {}
}
