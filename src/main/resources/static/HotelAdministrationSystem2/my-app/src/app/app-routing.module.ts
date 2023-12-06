import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoomsComponent } from './components/rooms/rooms.component';
import { VisitantsComponent } from './components/visitants/visitants.component';
import { HistoryComponent } from './components/history/history.component';
import { RoomComponent } from './components/room/room.component';
import { VisitantComponent } from './components/visitant/visitant.component';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  { path: '', redirectTo: '/rooms', pathMatch: 'full' },
  { path: 'rooms', component: RoomsComponent },
  { path: 'visitants', component: VisitantsComponent },
  { path: 'history', component: HistoryComponent},
  { path: 'rooms/:id', component: RoomComponent },
  { path: 'visitants/:id', component: VisitantComponent },
  { path: 'visitant', component: VisitantComponent },
  { path: 'registration', component: RegistrationComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
