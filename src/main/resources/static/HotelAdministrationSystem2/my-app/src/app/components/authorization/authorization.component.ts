import { Component } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Auth} from "../../model/auth.model";
import {NotificationService} from "../../service/notofication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrl: './authorization.component.css'
})
export class AuthorizationComponent {
  constructor(private service: AuthService,
              private notificationService: NotificationService,
              private router: Router,
              private authService: AuthService) {}

  selectedLogin: string = '';
  selectedPassword: string = '';

  authenticate() : void {
    const auth: Auth = {
      login: this.selectedLogin,
      password: this.selectedPassword
    }

    this.service.authenticate(auth).subscribe(
      (response: any) => {
        this.authService.saveAuthToken(response.token);
        this.router.navigate(['/rooms']);
      },
      (error) => {
        this.notificationService.showError(error.error.errors[0].defaultMessage);
      }
    );
  }

}
