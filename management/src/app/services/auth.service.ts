import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import * as Globals from 'src/app/globals'
import { JwtHelperService } from '@auth0/angular-jwt';
import { tap } from 'rxjs';
import { shareReplay } from 'rxjs/operators';
import { JWTUserInfo } from 'src/app/model/UserInfo.model';
import { Router } from '@angular/router';
import { NoticeService } from './notice.service';
import { Notice, NOTICE_TYPE_ENUM } from '../model/notice.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService, private router: Router, private noticeService: NoticeService) { }

  login(username: string, password: string) {
    console.log(`Tring to login as username:${username}`);

    return this.http.post(`${Globals.API_ROOT}/login`, { username: username, password: password }, { 'responseType': 'text' }).pipe(
      tap(console.log),
      tap((resp) => { localStorage.setItem("jwt", resp) }),
      tap((resp) => { this.setUserInfo(resp) }),
      shareReplay()
    ).subscribe({
      next: (resp) => {
        this.router.navigateByUrl("/home/index")

      },
      error: (e: HttpErrorResponse) => {
        console.log(e);
        var n: Notice = { type: NOTICE_TYPE_ENUM.ERROR, message: "Password or Username error" };
        this.noticeService.addNotice(n);
      }
    })

  }

  logout() {
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }

  private setUserInfo(jwt: string) {
    const userInfo: JWTUserInfo = <JWTUserInfo>this.jwtHelper.decodeToken(jwt)
    console.log("set user info called");
    console.log(userInfo);
    localStorage.setItem("userInfo", JSON.stringify(userInfo));
  }

  isLoggined(): boolean {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      if (!this.jwtHelper.isTokenExpired(jwt)) {
        return true;
      }
    }
    return false;
  }

  getUserInfo(): JWTUserInfo {
    const userInfo = <JWTUserInfo>JSON.parse(localStorage.getItem("userInfo") || "");
    return userInfo;
  }

  isAdmin(): boolean {
    return this.getUserInfo().userType.toLowerCase() === 'admin'
  }


  isDev(): boolean {
    return this.getUserInfo().userType.toLowerCase() === 'dev'
  }
}
