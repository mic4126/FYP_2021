import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDescComponent } from './edit-desc.component';

describe('EditDescComponent', () => {
  let component: EditDescComponent;
  let fixture: ComponentFixture<EditDescComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditDescComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditDescComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
