import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VisitantService } from '../../service/visitant.service';
import { NotificationService } from '../../service/notofication.service';
import { Visitant } from '../../model/visitant.model';

@Component({
  selector: 'app-visitant',
  templateUrl: './visitant.component.html',
  styleUrl: './visitant.component.css'
})
export class VisitantComponent implements OnInit {

  visitant: Visitant = {} as Visitant;

  constructor(
    private route: ActivatedRoute,
    private visitantService: VisitantService,
    private notificationService: NotificationService
    ) {}

    ngOnInit(): void {
      const visitantId = this.route.snapshot.paramMap.get('id');
      if (visitantId) {
        this.visitantService.getVisitantById(Number(visitantId)).subscribe(data => {
          this.visitant = data;
        });
      }
    }
}
