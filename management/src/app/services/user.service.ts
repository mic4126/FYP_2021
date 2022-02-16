import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import * as Globals from 'src/app/globals'
import { CreateUserModel } from '../model/CreateUserModel.model';
import { UserInfo } from '../model/UserInfo.model';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }

  createUser(createUserInfo: CreateUserModel) {
    return this.http.post(`${Globals.API_ROOT}/admin/user`, createUserInfo)
  }

  getAllDev(): Observable<UserInfo[]> {
    return this.http.get<UserInfo>(`${Globals.API_ROOT}/admin/user?userType=dev`).pipe(tap(console.log))
  }

  getAllUsers(): Observable<UserInfo[]> {
    return this.http.get<UserInfo>(`${Globals.API_ROOT}/admin/user`).pipe(tap(console.log))
  }

  deleteUser(username: string) {
    return this.http.delete(`${Globals.API_ROOT}/admin/user/${username}`)
  }

  getUserInfo(username: string) {
    return this.http.get<UserInfo>(`${Globals.API_ROOT}/admin/user/${username}`).pipe(tap(console.log))
  }

  updateUserInfo(username: string, userInfo: UserInfo) {
    return this.http.put(`${Globals.API_ROOT}/admin/user/${username}`, userInfo)
  }

  changeUserPassword(username: string, data: any) {
    return this.http.put(`${Globals.API_ROOT}/admin/user/password/${username}`,data)
  }
}
