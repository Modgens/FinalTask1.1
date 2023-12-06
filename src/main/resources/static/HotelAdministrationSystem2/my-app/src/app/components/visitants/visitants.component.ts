import { Component, OnInit } from '@angular/core';
import { VisitantService } from '../../service/visitant.service';
import { Visitants } from '../../model/visitants.model';

@Component({
  selector: 'app-visitants',
  templateUrl: './visitants.component.html',
  styleUrl: './visitants.component.css'
})
export class VisitantsComponent implements OnInit{
  visitants: Visitants[] = [];
  searchTerm: string = '';
  filterType: string = 'all';
  currentPage: number = 0;
  pageSize: number = 10;
  totalItems: number = 0;
  totalPages: number = 0;


  constructor(private visitantService: VisitantService) { }

  ngOnInit(): void {
    this.loadVisitants();
  }

  search() {
    this.currentPage = 0;
    this.loadVisitants();
  }
  filter(filterBy: string) {
    this.filterType = filterBy;
    this.currentPage = 0;
    this.loadVisitants();
  }

  
  loadVisitants() {
    this.visitantService.getVisitants(this.searchTerm, this.filterType, this.currentPage, this.pageSize).subscribe(data => {
      this.visitants = data.content;
      this.totalItems = data.totalElements;
      this.totalPages = data.totalPages;
    });
  }

changePage(offset: number): void {
  var newPage = 0;
  if(offset === -2){
    newPage = this.currentPage + 1;
  } else if(offset === -1) {
    newPage = this.currentPage - 1;
  } else if(offset>=0){
    newPage = offset;
  }
  if (newPage >= 0 && newPage < this.totalPages) {
    this.currentPage = newPage;
    this.loadVisitants();
  }
}

getPages(): number[] {
  return Array.from({ length: this.totalPages }, (_, index) => index);
}
}
