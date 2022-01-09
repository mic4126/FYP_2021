import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import * as Globals from 'src/app/globals'
import { Project } from '../model/Project.model';
import { UserInfo } from '../model/UserInfo.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  createProject(projectName: string, devs: string[]) {
    return this.http.post(`${Globals.API_ROOT}/project`, { "projectName": projectName, "developers": devs }, { responseType: 'text' })
  }

  getAllProject() {
    return this.http.get<Project>(`${Globals.API_ROOT}/project`).pipe(tap(console.log))
  }

  getProjectDevelopers(projectId: number): Observable<UserInfo[]> {
    return this.http.get<UserInfo[]>(`${Globals.API_ROOT}/project/${projectId}/user`).pipe(tap(console.log))
  }

  addDevToProject(projectId: number, username: string) {
    return this.http.post(`${Globals.API_ROOT}/project/${projectId}/user`, new HttpParams().append('username', username));
  }

  deleteDevFromProject(projectId: number, username: string) {
    return this.http.delete(`${Globals.API_ROOT}/project/${projectId}/user/${username}`)
  }

}
