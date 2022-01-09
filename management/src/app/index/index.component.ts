import { Component, OnInit } from '@angular/core';
import { JWTUserInfo } from '../model/UserInfo.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  userInfo: JWTUserInfo;
  username: string;

  isDev:boolean;
  isAdmin:boolean;

  constructor(private authService:AuthService) {
    this.userInfo = this.authService.getUserInfo();
    this.username = this.userInfo.username;
    this.isDev = authService.isDev();
    this.isAdmin = authService.isAdmin();
   }

  ngOnInit(): void {
  }

}
