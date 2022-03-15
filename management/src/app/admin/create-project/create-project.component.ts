import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo.model';
import { NoticeService } from 'src/app/services/notice.service';
import { ProjectService } from 'src/app/services/project.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.scss']
})
export class CreateProjectComponent implements OnInit {

  devs$: Observable<UserInfo[]>;
  selectedDevs = [];
  createProjectForm: FormGroup

  constructor(private userService: UserService,
    private projectService: ProjectService,
    private fb: FormBuilder,
    private ns: NoticeService) {
    this.createProjectForm = fb.group({
      "projectName": ['', [Validators.required]]
    })
    this.devs$ = this.userService.getAllDev();
  }

  ngOnInit(): void {
  }

  onCreateProjectFormSubmit() {
    const projectName = this.createProjectForm.value['projectName'];
    console.log("Project Name: " + projectName)
    console.log("selected devs:")
    console.log(this.selectedDevs);

    if (this.createProjectForm.valid && this.selectedDevs.length > 0) {
      this.projectService.createProject(projectName, this.selectedDevs).subscribe(
        {
          next: (projectid) => {
            console.log("ProjectID:" + projectid);
            this.createProjectForm.reset();
            this.selectedDevs = [];
            this.ns.success("New project created.")
          },
          error: (e: any) => {
            this.ns.error("Failed to create project.")
          }

        })
    }

  }

}
