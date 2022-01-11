import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProjectNameComponent } from './edit-project-name.component';

describe('EditProjectNameComponent', () => {
  let component: EditProjectNameComponent;
  let fixture: ComponentFixture<EditProjectNameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditProjectNameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProjectNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
