import { Room } from "./room.model";
import { ShortVisitant } from "./shortVisitant.model";

export interface Group {
    id: number;
    room: Room;
    dateIn: Date;
    dateOut: Date;
    mainVisitant: ShortVisitant | undefined;
    visitants: ShortVisitant[] | undefined;
  }