import { Component, OnInit } from '@angular/core';
import { Room } from '../../model/room.model';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from '../../service/room.service';
import { ShortVisitant } from '../../model/shortVisitant.model';
import { Group } from '../../model/group.model';
import { GroupService } from '../../service/group.service';
import { NotificationService } from '../../service/notofication.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit{
  room: Room = {} as Room;
  shortVisitants: ShortVisitant[] = [];
  selectedVisitantIds: string[] = [];
  selectedMainVisitant: string = '';
  selectedDateIn: Date = new Date();
  selectedDateOut: Date = new Date();

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private groupService: GroupService,
    private notificationService: NotificationService
    ) {}

  createGroup():void {
    const visitant: ShortVisitant = {
      passportNumber: this.selectedMainVisitant,
      fullName: ''
    };
    let visitants: ShortVisitant[] = [];
    for (let i = 0; i < this.selectedVisitantIds.length; i++) {
      const visitant: ShortVisitant = {
        passportNumber: this.selectedVisitantIds[i],
        fullName: ''
      };
      visitants[i] = visitant;
    }

    const newGroup: Group = {
      id: -1,
      dateIn: this.selectedDateIn,
      dateOut: this.selectedDateOut,
      room: this.room,
      mainVisitant: visitant,
      visitants: visitants,
    };
  
    this.groupService.createGroup(newGroup).subscribe(
      (result) => {
        window.location.reload();
      },
      (error) => {
        if(error.error.errors == undefined) {
          this.notificationService.showError(error.error.message);
        } else {
          this.notificationService.showError(error.error.errors[0].defaultMessage);
        }
      }
    );
    
  }
  
  updateGroup() {
    const group: Group = {
      id: this.room.group?.id || -1,
      dateIn: this.selectedDateIn,
      dateOut: this.selectedDateOut,
      room: this.room,
      mainVisitant: this.room.group?.mainVisitant,
      visitants: this.room.group?.visitants,
    };
  
    this.groupService.updateGroup(group).subscribe(
      (result) => {
        this.notificationService.showSuccess("Дата виселення успішно оновленна!");
      },
      (error) => {
        if(error.error.errors == undefined) {
          this.notificationService.showError(error.error.message);
        } else {
          this.notificationService.showError(error.error.errors[0].defaultMessage);
        }      }
    );
  }
  evictGroup() {
    this.roomService.evictGroup(this.room.id).subscribe(
      (result) => {
        window.location.reload();
      },
      (error) => {
        if(error.error.errors == undefined) {
          this.notificationService.showError(error.error.message);
        } else {
          this.notificationService.showError(error.error.errors[0].defaultMessage);
        }      
      }
    );
  }
  downloadPdf(groupId: number | undefined): void {

    if (groupId !== undefined) {
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
          this.notificationService.showError("Помилка: Тіло відповіді є нульовим.");
        }
      },
      error => {
        this.notificationService.showError("Помилка при завантаженні PDF:" + error.error.message);
      }
    );
  }
}

  ngOnInit(): void {
    const roomId = this.route.snapshot.paramMap.get('id');
    if (roomId) {
      this.roomService.getRoomById(Number(roomId)).subscribe(data => {
        this.room = data;
      });
    }
  }
  getRange(count: number): number[]{
    return new Array(count).fill(0);
  }
}
