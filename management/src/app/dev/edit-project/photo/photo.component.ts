import { Component, Input, OnInit, TemplateRef } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalOptions, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Attachment } from 'src/app/model/Attachment.model';
import { AttachmentService } from 'src/app/services/attachment.service';
import { NoticeService } from 'src/app/services/notice.service';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.scss']
})
export class PhotoComponent implements OnInit {

  @Input() projectId = -1;

  photos$: Observable<Attachment[]> = new Observable<Attachment[]>();

  uploadPhoto: File = new File([""], "");

  addPhotoModalRef: NgbModalRef | null = null;

  constructor(private attachmentService: AttachmentService,
    private modalService: NgbModal,
    private ns: NoticeService) { }

  ngOnInit(): void {
    this.getPhotos();
  }

  getPhotos() {
    this.photos$ = this.attachmentService.getProjetAttachmentList(this.projectId)
  }

  deletePhoto(attachmentId: number) {
    this.attachmentService.deleteAttachment(attachmentId).subscribe(
      {
        next: () => {
          this.getPhotos()
          this.ns.success("Photo deleted.")
        },
        error: () => {
          this.getPhotos()
          this.ns.error("Photo failed to delete.")
        }
      })
  }

  onFileChange(file: FileList | null) {
    if (file) {
      let f = file.item(0)
      if (f) {
        this.uploadPhoto = f;
      }
    }
  }

  openAddPhotoModal(modal: TemplateRef<any>) {
    this.addPhotoModalRef = this.modalService.open(modal);
  }

  onAddPhotoClick() {
    this.attachmentService.addProjectAttachment(this.projectId, this.uploadPhoto)
      .subscribe({
        next: () => {
          this.getPhotos();
          this.addPhotoModalRef?.close()
          this.ns.success("Photo added successfully.")
        },
        error: () => {
          this.ns.success("Photo failed to add. Please try again.")
        }
      })
  }






}
