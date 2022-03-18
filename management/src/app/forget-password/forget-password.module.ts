import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForgetPasswordRoutingModule } from './forget-password-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { HomeModule } from '../home/home.module';
import { ForgetPasswordComponent } from './forget-password.component';
import { NoticeModule } from '../common/notice/notice.module';


@NgModule({
  declarations: [ForgetPasswordComponent],
  imports: [
    CommonModule,
    ForgetPasswordRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    HomeModule,
    NgSelectModule,
    FormsModule,
    NoticeModule,    
  ]
})
export class ForgetPasswordModule { }
