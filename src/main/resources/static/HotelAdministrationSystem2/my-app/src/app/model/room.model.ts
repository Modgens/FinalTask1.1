import { Group } from "./group.model";
import { ShortVisitant } from "./shortVisitant.model";

export interface Room {
  id: number;
  type: string;
  maxPeople: number;
  number: number;
  price: number;
  floor: number;
  isAvailable: boolean;
  group?: Group;
}