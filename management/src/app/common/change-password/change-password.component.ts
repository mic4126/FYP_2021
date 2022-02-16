import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
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

  constructor(private authService: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.changePasswordForm.get('username')?.setValue(this.authService.getUserInfo().username)
  }

  submitChangePassword() {
    if (!this.changePasswordForm.valid) {
      return
    }
    this.userService.changeUserPassword(this.authService.getUserInfo().username, this.changePasswordForm.value).subscribe(() => {
      this.authService.logout()
    })
  }

}
