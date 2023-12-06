// room.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rooms } from '../model/rooms.model';
import { Room } from '../model/room.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private apiUrl = 'http://localhost:8080/api/rooms';

  constructor(private http: HttpClient) { }

  getAllRooms(filterBy: string): Observable<Rooms[]> {
    const params = new HttpParams().set('filterBy', filterBy);
    return this.http.get<Rooms[]>(this.apiUrl, {params});
  }
  getRoomById(id: number): Observable<Room> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Room>(url);
  }
  evictGroup(room: number) {
    return this.http.request('PUT', `${this.apiUrl+ '/' +room}`);
  }
}
