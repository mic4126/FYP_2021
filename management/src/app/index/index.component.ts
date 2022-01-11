import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../model/Project.model';
import { JWTUserInfo } from '../model/UserInfo.model';
import { AuthService } from '../services/auth.service';
import { ProjectService } from '../services/project.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  userInfo: JWTUserInfo;
  username: string;

  projectList$:Observable<Project[]> | null = null;

  isDev:boolean;
  isAdmin:boolean;

  constructor(private authService:AuthService,private projectService:ProjectService) {
    this.userInfo = this.authService.getUserInfo();
    this.username = this.userInfo.username;
    this.isDev = authService.isDev();
    this.isAdmin = authService.isAdmin();
   }

  ngOnInit(): void {
    if (this.isDev){
      this.projectList$ = this.projectService.getDevProjects(this.userInfo.username);
    }
  }

}
