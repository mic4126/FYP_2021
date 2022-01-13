import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { API_ROOT } from '../globals';
import { Attachment } from '../model/Attachment.model';

@Injectable({
  providedIn: 'root'
})
export class AttachmentService {

  constructor(private http: HttpClient) {

  }

  addDetailAttachment(projectId: number, detailId: number, file: any) {
    const formData = new FormData()
    formData.append('file', file)
    return this.http.post(`${API_ROOT}/project/${projectId}/detail/${detailId}/attachment`, formData);
  }

  addProjectAttachment(projectId: number, file: File) {
    const formData = new FormData()
    formData.append('file', file)
    return this.http.post(`${API_ROOT}/project/${projectId}/attachment`, formData);
  }

  getDetailAttachmentList(detailId: number): Observable<Attachment[]> {
    return this.http.get<Attachment[]>(`${API_ROOT}/project/detail/${detailId}/attachment`).pipe(tap(console.log))
  }

  getProjetAttachmentList(projectId: number): Observable<Attachment[]> {
    return this.http.get<Attachment[]>(`${API_ROOT}/project/${projectId}/attachment`).pipe(tap(console.log))
  }


  deleteAttachment(attachmentId:number){
    return this.http.delete(`${API_ROOT}/project/attachment/${attachmentId}`).pipe(tap(()=>{console.log("Attachment deleted");
    }))
  }
}
