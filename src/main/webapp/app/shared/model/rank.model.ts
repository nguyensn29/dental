export interface IRank {
  id?: number;
  name?: string;
  turnoverCondition?: number;
  discount?: number;
}

export class Rank implements IRank {
  constructor(public id?: number, public name?: string, public turnoverCondition?: number, public discount?: number) {}
}
