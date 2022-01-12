import { Component, Input, OnInit, TemplateRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Detail } from 'src/app/model/Detail.model';
import { DetailService } from 'src/app/services/detail.service';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {

  @Input() projectId: number = -1;
  details$: Observable<Detail[]> = new Observable<[]>();
  addDetailModalRef: NgbModalRef | null = null;

  addDetailForm: FormGroup = new FormGroup({});

  constructor(private projectService: ProjectService, private detailService: DetailService, private modalService: NgbModal) {

  }

  ngOnInit(): void {
    this.getNewDetails();
    this.addDetailForm.addControl('projectId', new FormControl(this.projectId))
    this.addDetailForm.addControl('detailName', new FormControl('', [Validators.required]))
  }

  openAddDetailModal(modal: any) {
    this.addDetailModalRef = this.modalService.open(modal);
  }

  addDetailClicked() {
    const newDetail = this.addDetailForm.value;
    console.log(newDetail);
    this.detailService.addDetail(newDetail.projectId, newDetail.detailName).subscribe(() => {
      this.getNewDetails();
      this.addDetailModalRef?.close()
    })

  }

  parseDetails() {

  }


  getNewDetails() {
    console.log("Called getNewDetails");
    this.details$ = this.detailService.getDetails(this.projectId)
    console.log("Done getNewDetails");
  }
}
