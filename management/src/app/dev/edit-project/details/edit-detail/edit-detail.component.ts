import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbAccordion } from '@ng-bootstrap/ng-bootstrap';
import { Detail } from 'src/app/model/Detail.model';
import { DetailService } from 'src/app/services/detail.service';

@Component({
  selector: 'app-edit-detail',
  templateUrl: './edit-detail.component.html',
  styleUrls: ['./edit-detail.component.scss']
})
export class EditDetailComponent implements OnInit, OnChanges {

  @Input() detail: Detail | null = null;
  @Input() projectId: number = -1;
  @Input() getNewDetails: Function = () => { console.log('place holder'); }
  @Output() detailUpdate = new EventEmitter<string>();
  @Output() detailDelete = new EventEmitter<string>();

  editDetailForm: FormGroup = new FormGroup({});


  constructor(private detailService: DetailService, private fb: FormBuilder, private accrodionService: NgbAccordion) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    this.editDetailForm.patchValue(changes);
    console.log("Patched Value");

  }

  ngOnInit(): void {
    this.setFormValue();
  }

  private setFormValue() {
    console.log(this.detail);
    this.editDetailForm.addControl('detailId', this.fb.control(this.detail?.detailId));
    this.editDetailForm.addControl('projectId', this.fb.control(this.detail?.projectId));
    this.editDetailForm.addControl('detailName', this.fb.control(this.detail?.detailName, [Validators.required]));
    this.editDetailForm.addControl('detailName_TC', this.fb.control(this.detail?.detailName_TC));
    this.editDetailForm.addControl('detailName_SC', this.fb.control(this.detail?.detailName_SC));
    this.editDetailForm.addControl('detailDesc', this.fb.control(this.detail?.detailDesc));
    this.editDetailForm.addControl('detailDesc_TC', this.fb.control(this.detail?.detailDesc_TC));
    this.editDetailForm.addControl('detailDesc_SC', this.fb.control(this.detail?.detailName_SC));
  }

  parseDetail() {
    return JSON.stringify(this.detail)
  }

  onUpdateClick() {
    console.log("OnUpdateClicked")
    const detail: Detail = this.editDetailForm.value;
    console.log(detail);
    return this.detailService.updateDetail(detail).subscribe(() => {
      this.accrodionService.collapseAll();
      this.detailUpdate.emit();
    })
  }

  onDeleteClick() {
    if (this.detail) {
      return this.detailService.deleteDetail(this.detail?.detailId, this.detail?.projectId).subscribe(() => {
        console.log("deleted detail:" + this.detail?.detailId);
        this.detailDelete.emit();
      })
    }
    return null;
  }


}
