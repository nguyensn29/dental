export interface ICollectionProduct {
  id?: number;
  collectionId?: number;
  productId?: number;
}

export class CollectionProduct implements ICollectionProduct {
  constructor(public id?: number, public collectionId?: number, public productId?: number) {}
}
