import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserInfo } from 'src/app/model/UserInfo.model';
import { AuthService } from 'src/app/services/auth.service';
import { NoticeService } from 'src/app/services/notice.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-user-info',
  templateUrl: './change-user-info.component.html',
  styleUrls: ['./change-user-info.component.scss']
})
export class ChangeUserInfoComponent implements OnInit {

  userInfo: UserInfo = new Object() as UserInfo;
  changeUserInfoForm: FormGroup;
  constructor(private userService: UserService,
    private authService: AuthService,
    private fb: FormBuilder,
    private ns: NoticeService) {
    this.changeUserInfoForm = fb.group({
      "username": ['', []],
      "email": ['', [Validators.required, Validators.email]],
      "firstName": ['', [Validators.required]],
      "lastName": ['', [Validators.required]],
      "password": ['', [Validators.required]]
    })
    this.changeUserInfoForm.controls["username"].disable();
  }

  ngOnInit(): void {
    this.setUserInfo()
  }

  setUserInfo() {
    this.userService.getUserInfo(this.authService.getUserInfo().username).subscribe(data => {
      this.userInfo = data
      this.changeUserInfoForm.patchValue(data);
      this.changeUserInfoForm.controls["username"].disable();
    })
  }

  submitChangeUserInfo() {
    if (!this.changeUserInfoForm.valid) {
      return
    }
    this.changeUserInfoForm.controls["username"].enable();
    const newUserInfo: UserInfo = this.changeUserInfoForm.value
    this.userService.updateUserInfo(newUserInfo.username, newUserInfo).subscribe({
      next: () => {
        this.setUserInfo()
        this.ns.success("User info updated.")
      },
      error: () => {
        // this.setUserInfo()
        this.ns.error("User info failed to update.")
      }

    }
    )
  }

}
