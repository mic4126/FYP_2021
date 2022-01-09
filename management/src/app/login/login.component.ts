import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators,ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss', "./../../styles.scss"]
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;  

  constructor(private fb:FormBuilder, private authService: AuthService, private router:Router) {
    this.loginForm = this.fb.group({
      "username":['',Validators.required],
      "password":['',Validators.required]
    })  }


  ngOnInit(): void {
    if (this.authService.isLoggined()){
      this.router.navigate(['/home/index'])
    }
  }

  submit():void{
    if (!this.loginForm.valid){
      return
    }
    console.log("login form submit called")
    const username = this.loginForm.value['username'];
    const password = this.loginForm.value['password'];
    this.authService.login(username,password);
  }

}
