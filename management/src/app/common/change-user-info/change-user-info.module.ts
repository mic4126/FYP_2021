import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChangeUserInfoRoutingModule } from './change-user-info-routing.module';
import { ChangeUserInfoComponent } from './change-user-info.component';
import { SharedModule } from 'src/shared.module';


@NgModule({
  declarations: [ChangeUserInfoComponent],
  imports: [
    CommonModule,
    ChangeUserInfoRoutingModule,
    SharedModule,
  ]
})
export class ChangeUserInfoModule { }
