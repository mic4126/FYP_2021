import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Notice, NOTICE_TYPE_ENUM } from 'src/app/model/notice.model';
import { Project } from 'src/app/model/Project.model';
import { UserInfo } from 'src/app/model/UserInfo.model';
import { NoticeService } from 'src/app/services/notice.service';
import { ProjectService } from 'src/app/services/project.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-assign-dev',
  templateUrl: './assign-dev.component.html',
  styleUrls: ['./assign-dev.component.scss']
})
export class AssignDevComponent implements OnInit {

  projects$: Observable<Project[]>;
  selectProjectId: number | null = null;

  projectDev: UserInfo[] = [];
  devs$: Observable<UserInfo[]> | null = null;

  constructor(private projectService: ProjectService, private userService: UserService, private noticeService: NoticeService) {
    this.projects$ = this.projectService.getAllProject();
  }

  ngOnInit(): void {
  }

  onProjectChange() {
    console.log("Project changed:" + this.selectProjectId);
    if (!this.selectProjectId) {
      this.projectDev = [];
      return
    }
    this.getProjectDevs();
  }

  private getProjectDevs() {
    if (this.selectProjectId) {
      this.projectService.getProjectDevelopers(this.selectProjectId).subscribe((resp) => {
        this.projectDev = resp;
      });
      this.devs$ = this.userService.getAllDev();
    }
  }

  onDevAdd($event: any) {
    console.log($event);
    const user: UserInfo = $event
    if (this.selectProjectId) {
      this.projectService.addDevToProject(this.selectProjectId, user.username).subscribe({
        error: (e: HttpErrorResponse) => {
          this.getProjectDevs()        
        }
      })

    }
  }

  onDevRemoved($event: any) {
    console.log($event);
    const user: UserInfo = <UserInfo>$event.value;
    if (this.selectProjectId) {
      this.projectService.deleteDevFromProject(this.selectProjectId, user.username)
        .subscribe({
          error: () => {
            this.getProjectDevs();
          }
        })
    }
  }

}
