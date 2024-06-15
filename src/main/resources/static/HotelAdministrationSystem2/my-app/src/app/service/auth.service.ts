import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Rooms} from "../model/rooms.model";
import {Visitants} from "../model/visitants.model";
import {Auth} from "../model/auth.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth/authenticate';
  private authTokenKey = '';

  constructor(private http: HttpClient) {
  }

  authenticate(auth: Auth) {
    return this.http.post<Auth>(`${this.apiUrl}`, auth);
  }

  // Зберігаємо токен у локальному сховищі
  saveAuthToken(token: string): void {
    localStorage.setItem(this.authTokenKey, token);
  }

  // Отримуємо токен з локального сховища
  getAuthToken(): string | null {
    return localStorage.getItem(this.authTokenKey);
  }

}
