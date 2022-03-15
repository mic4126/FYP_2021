import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbAccordion, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Attachment } from 'src/app/model/Attachment.model';
import { Detail } from 'src/app/model/Detail.model';
import { AttachmentService } from 'src/app/services/attachment.service';
import { DetailService } from 'src/app/services/detail.service';
import { NoticeService } from 'src/app/services/notice.service';

@Component({
  selector: 'app-edit-detail',
  templateUrl: './edit-detail.component.html',
  styleUrls: ['./edit-detail.component.scss']
})
export class EditDetailComponent implements OnInit, OnChanges {

  @Input() detail: Detail | null = null;
  @Input() projectId: number = -1;
  @Output() detailUpdate = new EventEmitter<string>();
  @Output() detailDelete = new EventEmitter<string>();

  editDetailForm: FormGroup = new FormGroup({});

  addAttachmentForm: FormGroup;
  addAttachmentModalRef: NgbModalRef | null = null;
  uploadFile: any;

  attachments$: Observable<Attachment[]> = new Observable<Attachment[]>();

  constructor(private detailService: DetailService,
    private fb: FormBuilder,
    private accrodionService: NgbAccordion,
    private modalService: NgbModal,
    private attachmentService: AttachmentService,
    private ns: NoticeService
  ) {
    this.addAttachmentForm = fb.group({ file: ['', [Validators.required]] })
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    this.editDetailForm.patchValue(changes);
    console.log("Patched Value");

  }

  ngOnInit(): void {
    this.setFormValue();
    this.getDetailAttachments();
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
    this.editDetailForm.addControl('detailDesc_SC', this.fb.control(this.detail?.detailDesc_SC));
  }

  parseDetail() {
    return JSON.stringify(this.detail)
  }

  getDetailAttachments() {
    if (this.detail) {
      this.attachments$ = this.attachmentService.getDetailAttachmentList(this.detail?.detailId);
    }
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

  onFileChange(file: FileList | null) {
    if (file) {
      this.uploadFile = file.item(0)
    }
  }

  onAddAttachmentClick() {
    console.log(this.addAttachmentForm.value);
    if (this.detail) {
      this.attachmentService.addDetailAttachment(this.detail.projectId, this.detail.detailId, this.uploadFile).subscribe(() => {
        this.addAttachmentModalRef?.close();
        this.getDetailAttachments()
      })

    }
  }

  openAddAttachmentModal(content: any) {
    this.addAttachmentModalRef = this.modalService.open(content)
  }

  onDeleteAttachmen(attachmentId: number) {
    this.attachmentService.deleteAttachment(attachmentId).subscribe({
      next: () => {
        this.getDetailAttachments()
        this.ns.success("Attachment deleted.")
      },
      error: () =>{
        this.ns.error("Attachment failed to deleted.")
      }
    })
  }


}
