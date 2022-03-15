import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { NoticeService } from 'src/app/services/notice.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  changePasswordForm: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('', [Validators.required]),
    newPassword: new FormControl('', [Validators.required]),
    newPasswordCheck: new FormControl('', [Validators.required])
  })

  constructor(private authService: AuthService,
    private userService: UserService,
    private ns: NoticeService) { }

  ngOnInit(): void {
    this.changePasswordForm.get('username')?.setValue(this.authService.getUserInfo().username)
  }

  submitChangePassword() {
    if (!this.changePasswordForm.valid) {
      return
    }
    this.userService.changeUserPassword(this.authService.getUserInfo().username, this.changePasswordForm.value).subscribe({
      next: () => {
        this.authService.logout()
        this.ns.success("Password updated")
      },
      error: () => {
        this.ns.error("Password failed to update")
      }
    })
  }

}
