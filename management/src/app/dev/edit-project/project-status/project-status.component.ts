import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { pairwise } from 'rxjs';
import { FRONTEND_PROJECT_LINK } from 'src/app/globals';
import { NoticeService } from 'src/app/services/notice.service';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-project-status',
  templateUrl: './project-status.component.html',
  styleUrls: ['./project-status.component.scss']
})
export class ProjectStatusComponent implements OnInit {

  @Input() projectId: number = -1

  projectStautsControl: FormControl = new FormControl();

  frontendLink = ""

  constructor(private projectService: ProjectService, private ns: NoticeService) { }

  ngOnInit(): void {
    this.frontendLink = `${FRONTEND_PROJECT_LINK}${this.projectId}`
    this.initFormControl();
    this.projectStautsControl.valueChanges.pipe(pairwise())
      .subscribe(([old, newValue]) => {
        console.log(newValue);

        if (newValue) {
          this.projectService.enableProject(this.projectId).subscribe(
            {
              next: () => {
                this.ns.success("This project will show in public site.")
              },
              error: () => {
                this.ns.error("Error when updating database.")
                this.initFormControl()
              }
            }
          )
        } else {
          this.projectService.disableProject(this.projectId).subscribe({
            next: () => {
              this.ns.success("This project will hide from public site.")
            },
            error: () => {
              this.ns.error("Error when updating database.")
              this.initFormControl()
            }
          });
        }
      })
  }



  private initFormControl() {
    this.projectService.getProjectStatus(this.projectId).subscribe({
      next: (resp: boolean) => {

        this.projectStautsControl.setValue(resp);
      }
    });
  }
}
