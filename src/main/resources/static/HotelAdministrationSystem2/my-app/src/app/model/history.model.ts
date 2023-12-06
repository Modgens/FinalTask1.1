import { Room } from "./room.model";
import { ShortVisitant } from "./shortVisitant.model";

export interface History {
    id: number;
    number: number;
    dateIn: Date;
    dateOut: Date;
    mainVisitant: ShortVisitant;
    visitants: ShortVisitant[];
    totalAmount: number;
  }