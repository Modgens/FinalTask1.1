import { Component, OnInit } from '@angular/core';
import { GroupService } from '../../service/group.service';
import { History } from '../../model/history.model';
import { ShortVisitant } from '../../model/shortVisitant.model';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { NotificationService } from '../../service/notofication.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})

export class HistoryComponent implements OnInit{

  groups: History[] = [];

  constructor(
    private groupService: GroupService,
    private route: ActivatedRoute,
    private notification: NotificationService
    ) { }

  ngOnInit(): void {
    this.groupService.getAllGroups().subscribe(data => {
      this.groups = data;
    });
  }
  getVisitantsString(visitants: ShortVisitant[]): string {
    return visitants.length > 0
      ? visitants.map((v) => v.fullName).join(', ')
      : 'Нема';
  }
  downloadPdf(groupId: number): void {
    this.groupService.getPdf(groupId).subscribe(
      (response: HttpResponse<Blob>) => {
        const contentDisposition = response.headers.get('content-disposition');
        const filename = contentDisposition?.split(';')[1]?.trim()?.split('=')[1] || 'check.pdf';
  
        const blobPart = response.body;
        if (blobPart) {
          const blob = new Blob([blobPart], { type: 'application/pdf' });
          const downloadLink = document.createElement('a');
          downloadLink.href = window.URL.createObjectURL(blob);
          downloadLink.download = filename;
          document.body.appendChild(downloadLink);
  
          downloadLink.click();
          document.body.removeChild(downloadLink);
        } else {
          this.notification.showError("Помилка: Тіло відповіді є нульовим.");
        }
      },
      error => {
        this.notification.showError("Помилка при завантаженні PDF:" + error.error.message);
      }
    );
  }
  
}
