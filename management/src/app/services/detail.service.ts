import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { API_ROOT } from '../globals';
import { Detail } from '../model/Detail.model';

@Injectable({
  providedIn: 'root'
})
export class DetailService {


  constructor(private http: HttpClient) { }

  getDetails(projectId: number) {
    return this.http.get<Detail[]>(`${API_ROOT}/project/${projectId}/detail`).pipe(tap(console.log));
  }

  addDetail(projectId: number, detailName: string): Observable<number> {
    return this.http.post(`${API_ROOT}/project/${projectId}/detail`,
      new HttpParams().append('detailName', detailName), { responseType: 'text' })
      // return detailId as text 
      // parse it as number
      .pipe(map(resp => parseInt(resp)))
  }

  deleteDetail(detailId: number, projectId: number) {
    return this.http.delete(`${API_ROOT}/project/${projectId}/detail/${detailId}`)
  }

  updateDetail(detail: Detail) {
    return this.http.put(`${API_ROOT}/project/${detail.projectId}/detail/${detail.detailId}`, detail)
  }

}
