
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Group } from '../model/group.model';
import { Observable } from 'rxjs';
import { History } from '../model/history.model';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  private apiUrl = 'http://localhost:8080/api/groups';

  constructor(private http: HttpClient) { }

  createGroup(newGroup: Group) {
    return this.http.post<Group>(`${this.apiUrl}`, newGroup);
  }
  updateGroup(group: Group) {
    return this.http.put<Group>(`${this.apiUrl + '/' + group.id}`, group);
  }
  getAllGroups(): Observable<History[]> {
    return this.http.get<History[]>(this.apiUrl);
  }
  getPdf(groupId: number): Observable<HttpResponse<Blob>>{
    return this.http.get("http://localhost:8080/api/pdf/download/" + groupId, { observe: 'response', responseType: 'blob' });
  }
}
