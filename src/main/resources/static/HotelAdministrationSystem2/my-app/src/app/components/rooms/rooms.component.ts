import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../service/room.service';
import { Rooms } from '../../model/rooms.model';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrl: './rooms.component.css'
})
export class RoomsComponent implements OnInit{
  rooms: Rooms[] = [];
  filterType: string = "all";

  constructor(private roomService: RoomService) { }

  ngOnInit(): void {
    this.loadVisitants();
  }
  filter(filterBy: string) {
    this.filterType = filterBy;
    this.loadVisitants();
  }

  loadVisitants() {
    this.roomService.getAllRooms(this.filterType).subscribe(data => {
      this.rooms = data;
    });
  }
}
