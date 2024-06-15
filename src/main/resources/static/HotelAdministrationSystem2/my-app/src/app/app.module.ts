import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu/menu.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { VisitantsComponent } from './components/visitants/visitants.component';
import { RoomComponent } from './components/room/room.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { HistoryComponent } from './components/history/history.component';
import { VisitantComponent } from './components/visitant/visitant.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AuthorizationComponent} from "./components/authorization/authorization.component";
import {AuthInterceptor} from "./service/auth.interceptor";
import {ForbiddenComponent} from "./components/forbidden/forbidden.component";
import {ErrorInterceptor} from "./service/error.interceptor";


@NgModule({
  declarations: [
    AppComponent,
    RoomsComponent,
    MenuComponent,
    VisitantsComponent,
    RoomComponent,
    RegistrationComponent,
    HistoryComponent,
    VisitantComponent,
    AuthorizationComponent,
    ForbiddenComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right'
    }),
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
