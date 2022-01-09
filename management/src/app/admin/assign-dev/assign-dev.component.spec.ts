import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignDevComponent } from './assign-dev.component';

describe('AssignDevComponent', () => {
  let component: AssignDevComponent;
  let fixture: ComponentFixture<AssignDevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignDevComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignDevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
