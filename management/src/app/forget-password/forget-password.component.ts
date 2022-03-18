import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ForgetPasswordService } from '../services/forget-password.service';
import { catchError } from 'rxjs';
import { NoticeService } from '../services/notice.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {

  forgetPasswordForm: FormGroup

  constructor(private fb: FormBuilder, private router: Router, private forgetPWService: ForgetPasswordService, private ns:NoticeService) {
    this.forgetPasswordForm = this.fb.group({
      "username": ['', Validators.required],
      "email": ['', [Validators.required,Validators.email]]
    })
  }

  ngOnInit(): void {
  }

  onForgetFormSubmit(): void {
    console.log("Forget Password Form submitted");
    if (!this.forgetPasswordForm.valid) {
      console.log("Forget Password Form invalid");      
      return;
    }
    const username = this.forgetPasswordForm.value['username'];
    const email= this.forgetPasswordForm.value['email'];

    this.forgetPWService.resetPassword(username, email).subscribe(() => {
      localStorage.removeItem('jwt');
      this.router.navigateByUrl('/login');
    },()=>{
      this.ns.error("Email or Username incorrent");
    });

  }

}
