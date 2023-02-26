export interface ISetting {
  id?: number;
  name?: string;
  keyName?: string | null;
  value?: string | null;
  rule?: string | null;
  isNumber?: number;
  isObject?: number;
}

export class Setting implements ISetting {
  constructor(
    public id?: number,
    public name?: string,
    public keyName?: string | null,
    public value?: string | null,
    public rule?: string | null,
    public isNumber?: number,
    public isObject?: number
  ) {}
}
