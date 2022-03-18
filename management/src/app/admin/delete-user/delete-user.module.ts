import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DeleteUserRoutingModule } from './delete-user-routing.module';
import { DeleteUserComponent } from './delete-user.component';
import { SharedModule } from 'src/shared.module';


@NgModule({
  declarations: [DeleteUserComponent],
  imports: [
    CommonModule,
    DeleteUserRoutingModule,
    SharedModule
  ]
})
export class DeleteUserModule { }
