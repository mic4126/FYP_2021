import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import * as Globals from 'src/app/globals'
import { LabeledStatement } from 'typescript';
import { Contact } from '../model/Contact.model';
import { LANG } from '../model/Lang';
import { Project } from '../model/Project.model';
import { Tag } from '../model/Tag.model';
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
    return this.http.get<Project>(`${Globals.API_ROOT}/project?all=true`).pipe(tap(console.log))
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

  getDevProjects(username: string): Observable<Project[]> {
    return this.http.get<Project[]>(`${Globals.API_ROOT}/admin/user/${username}/project`)
  }

  getProjectDesc(projectId: number, lang: LANG): Observable<string> {
    return this.http.get(`${Globals.API_ROOT}/project/desc`, {
      responseType: 'text',
      params: {
        projectId: projectId,
        lang: lang
      }
    })
  }

  setProjectDesc(projectId: number, lang: LANG, desc: string) {
    return this.http.put(`${Globals.API_ROOT}/project/desc`, {
      projectId: projectId,
      desc: desc,
      lang: lang
    })
  }

  getProjectName(projectId: number, lang: LANG): Observable<string> {
    return this.http.get(`${Globals.API_ROOT}/project/name`, {
      responseType: 'text',
      params: {
        projectId: projectId,
        lang: lang
      }
    }
    )
  }

  setProjectName(projectId: number, lang: LANG, name: string) {
    const param = new HttpParams()
      .append('projectId', projectId)
      .append('lang', lang)
      .append('projectName', name)
    return this.http.put(`${Globals.API_ROOT}/project/${projectId}/name`, param)
  }

  getContact(projectId: number): Observable<Contact> {
    return this.http.get<Contact>(`${Globals.API_ROOT}/project/contact`, {
      params: {
        'projectId': projectId
      }
    })
  }

  setContact(contact: Contact) {
    return this.http.put(`${Globals.API_ROOT}/project/${contact.projectId}/contact`, contact)
  }

  addTag(tag: Tag) {
    return this.http.post(`${Globals.API_ROOT}/project/${tag.projectId}/tag`, tag)
  }

  getTags(projectId: number): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${Globals.API_ROOT}/project/${projectId}/tag`)
  }

  deleteTag(tag: Tag) {
    return this.http.request('delete', `${Globals.API_ROOT}/project/${tag.projectId}/tag`, { body: tag })
  }

  getProjectStatus(projectId: number) {
    return this.http.get(`${Globals.API_ROOT}/project/${projectId}/status`).pipe(tap(console.log))
  }

  enableProject(projectId: number) {
    return this.http.put(`${Globals.API_ROOT}/project/${projectId}/status/enable`,"")
  }

  disableProject(projectId: number) {
    return this.http.put(`${Globals.API_ROOT}/project/${projectId}/status/disable`,"")
  }

}
