import { Component, OnInit } from '@angular/core';
import { JWTUserInfo } from '../model/UserInfo.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  userInfo: JWTUserInfo;
  username: string;
  isAdmin:boolean;
  isDev:boolean;

  constructor(private authService: AuthService) {
    this.userInfo = this.authService.getUserInfo();
    this.username = this.userInfo.username;
    this.isAdmin = authService.isAdmin();
    this.isDev = authService.isDev();
  }

  ngOnInit(): void {
  }
}
