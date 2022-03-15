import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { forkJoin, Observable } from 'rxjs';
import { LANG } from 'src/app/model/Lang';
import { NoticeService } from 'src/app/services/notice.service';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-edit-project-name',
  templateUrl: './edit-project-name.component.html',
  styleUrls: ['./edit-project-name.component.scss']
})
export class EditProjectNameComponent implements OnInit {

  @Input() projectId: number = -1;
  projectNameForm: FormGroup;

  constructor(private fb: FormBuilder,
    private projectService: ProjectService,
    private ns: NoticeService
  ) {
    this.projectNameForm = this.fb.group({
      "engName": ['', [Validators.required]],
      "TCName": ['', []],
      "SCName": ['', []]
    })
  }

  ngOnInit(): void {
    this.setInitValue()
  }

  setInitValue() {
    this.projectService.getProjectName(this.projectId, LANG.ENG).subscribe((resp) => {
      this.projectNameForm.controls['engName'].setValue(resp);
    })
    this.projectService.getProjectName(this.projectId, LANG.TC).subscribe((resp) => {
      this.projectNameForm.controls['TCName'].setValue(resp);
    })
    this.projectService.getProjectName(this.projectId, LANG.SC).subscribe((resp) => {
      this.projectNameForm.controls['SCName'].setValue(resp);
    })
    console.log("Set Init Value Done");

  }

  onSubmit() {
    var projectName$ = [this.projectService.setProjectName(this.projectId, LANG.ENG, this.projectNameForm.controls['engName'].value),
    this.projectService.setProjectName(this.projectId, LANG.TC, this.projectNameForm.controls['TCName'].value),
    this.projectService.setProjectName(this.projectId, LANG.SC, this.projectNameForm.controls['SCName'].value)]
    forkJoin(projectName$).subscribe({
      next: () => {
        this.ns.success("Project Name updated");
      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        this.ns.error("Project Name failed to update. Please try again.");
      }
    })
  }



}
