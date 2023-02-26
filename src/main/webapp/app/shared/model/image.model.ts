export interface IImage {
  id?: number;
  src?: string;
  shopifyId?: number | null;
  imgPublicId?: string | null;
  isAvatar?: number;
  comboId?: number | null;
  productId?: number | null;
}

export class Image implements IImage {
  constructor(
    public id?: number,
    public src?: string,
    public shopifyId?: number | null,
    public imgPublicId?: string | null,
    public isAvatar?: number,
    public comboId?: number | null,
    public productId?: number | null
  ) {}
}
