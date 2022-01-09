import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { shareReplay, tap } from 'rxjs';
import * as Globals from 'src/app/globals'

@Injectable({
  providedIn: 'root'
})
export class ForgetPasswordService {

  constructor(private http: HttpClient) {

  }

  resetPassword(username: string, email: string) {

    return this.http.post(`${Globals.API_ROOT}/admin/user/password/${username}`,
      { "username": username, "email": email }, {
    }).pipe(
      tap(console.log),
      shareReplay()
    );
  }
}
