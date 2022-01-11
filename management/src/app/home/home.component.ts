import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private authService: AuthService,private router:Router) {
    this.userInfo = this.authService.getUserInfo();
    this.username = this.userInfo.username;
    this.isAdmin = authService.isAdmin();
    this.isDev = authService.isDev();
  }

  ngOnInit(): void {
  }

  onLogoutClick(){
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }
}
