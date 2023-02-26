export interface IMigration {
  id?: number;
  migration?: string;
  batch?: number;
}

export class Migration implements IMigration {
  constructor(public id?: number, public migration?: string, public batch?: number) {}
}
