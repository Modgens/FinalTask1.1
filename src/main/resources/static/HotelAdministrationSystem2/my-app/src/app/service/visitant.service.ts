import { HttpClient, HttpParams } from "@angular/common/http";
import { Visitant } from "../model/visitant.model";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Visitants } from "../model/visitants.model";
import { Page } from "../model/page.model";

@Injectable({
    providedIn: 'root'
  })
  export class VisitantService {
    private apiUrl = 'http://localhost:8080/api/visitants';
  
    constructor(private http: HttpClient) { }
  
    getVisitants(searchTerm: string, filterType: string, page: number, size: number): Observable<Page<Visitants>> {
      const params = new HttpParams()
        .set('searchTerm', searchTerm)
        .set('filterType', filterType)
        .set('page', page.toString())
        .set('size', size.toString());
    
      return this.http.get<Page<Visitants>>(`${this.apiUrl}`, { params });
    }
    getVisitantById(id: number): Observable<Visitant> {
      const url = `${this.apiUrl}/${id}`;
      return this.http.get<Visitant>(url);
    }
    createVisitant(newVisitant: Visitants) {
      return this.http.post<Visitants>(`${this.apiUrl}`, newVisitant);
    }
  }
  