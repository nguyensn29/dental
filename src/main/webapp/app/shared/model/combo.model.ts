export interface ICombo {
  id?: number;
  name?: string;
  price?: number;
  discount?: number | null;
  weight?: number;
  point?: number;
  description?: string | null;
  liked?: number;
}

export class Combo implements ICombo {
  constructor(
    public id?: number,
    public name?: string,
    public price?: number,
    public discount?: number | null,
    public weight?: number,
    public point?: number,
    public description?: string | null,
    public liked?: number
  ) {}
}
