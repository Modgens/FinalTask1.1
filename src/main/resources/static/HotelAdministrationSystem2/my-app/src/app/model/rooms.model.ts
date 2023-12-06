// roomsRooms.model.ts

export interface Rooms {
    id: number;
    type: string;
    number: number;
    maxPeople: number;
    price: number;
    floor: number;
    isAvailable: boolean;
  }
  