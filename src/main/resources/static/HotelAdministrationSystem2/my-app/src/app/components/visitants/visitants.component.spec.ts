import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitantsComponent } from './visitants.component';

describe('VisitantsComponent', () => {
  let component: VisitantsComponent;
  let fixture: ComponentFixture<VisitantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VisitantsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VisitantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
