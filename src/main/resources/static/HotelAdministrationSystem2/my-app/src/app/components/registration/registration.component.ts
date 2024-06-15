import { Component } from '@angular/core';
import { VisitantService } from '../../service/visitant.service';
import { NotificationService } from '../../service/notofication.service';
import { Visitants } from '../../model/visitants.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {

  constructor(
    private visitantService: VisitantService,
    private notificationService: NotificationService
    ) {}

  selectedFirstName: string = '';
  selectedMiddleName: string = '';
  selectedLastName: string = '';
  selectedBirthDate: Date = new Date();
  selectedPasswordId: string = '';

  createVisitant():void {
    console.log(this.selectedFirstName)
    const newVisitant: Visitants = {
      id: -1,
      lastName: this.selectedLastName,
      firstName: this.selectedFirstName,
      middleName: this.selectedMiddleName,
      birthDate: this.selectedBirthDate,
      passportNumber: this.selectedPasswordId
    }
    this.visitantService.createVisitant(newVisitant).subscribe(
      () => {
        this.notificationService.showSuccess("Гість успішно зареєстрований");
      },
      (error) => {
        this.notificationService.showError(error.error.errors[0].defaultMessage);
      }
    );
  }
}


