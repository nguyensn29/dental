export interface IMessage {
  id?: number;
  userId?: number;
  channel?: string | null;
  message?: string;
  isRead?: number;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public userId?: number,
    public channel?: string | null,
    public message?: string,
    public isRead?: number
  ) {}
}
