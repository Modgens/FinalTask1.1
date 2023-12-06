import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitantComponent } from './visitant.component';

describe('VisitantComponent', () => {
  let component: VisitantComponent;
  let fixture: ComponentFixture<VisitantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VisitantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VisitantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
