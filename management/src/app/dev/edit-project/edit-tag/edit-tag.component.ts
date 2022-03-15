import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Project } from 'src/app/model/Project.model';
import { Tag } from 'src/app/model/Tag.model';
import { NoticeService } from 'src/app/services/notice.service';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-edit-tag',
  templateUrl: './edit-tag.component.html',
  styleUrls: ['./edit-tag.component.scss']
})
export class EditTagComponent implements OnInit {

  @Input() projectId = -1;
  tags: Tag[] = []

  addTagModelRef: NgbModalRef | null = null;

  addTagForm: FormGroup

  constructor(private projectService: ProjectService,
    private modalService: NgbModal, private fb: FormBuilder,
    private ns: NoticeService) {
    this.addTagForm = this.fb.group({
      tag: ['', [Validators.required]],
      tagTC: ['', [Validators.required]],
      tagSC: ['', [Validators.required]],
      projectId: ['', [Validators.required]]
    })

  }

  ngOnInit(): void {
    this.getTags()
  }

  getTags() {
    this.projectService.getTags(this.projectId).subscribe((resp) => { this.tags = resp })
  }

  addTag() {
    if (!this.addTagForm.valid) {
      return
    }
    const tag: Tag = this.addTagForm.value;
    console.log(tag);
    this.projectService.addTag(tag).subscribe(() => {
      this.addTagModelRef?.close();
      this.getTags()
      this.ns.success("Tag added");
    })
  }

  openAddTagModal(content: any) {
    this.addTagModelRef = this.modalService.open(content);
    this.addTagForm.controls['projectId'].setValue(this.projectId);
  }

  deleteTag(tag: Tag) {
    console.log(tag);
    this.projectService.deleteTag(tag).subscribe(
      {
        next: () => {
          this.getTags();
          this.ns.success("Tag deleted.");
        },
        error: () => {
          this.ns.success("Tag failed to deleted.");
          this.getTags();
        }
      }
    )
  }



}
