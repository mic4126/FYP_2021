import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssignDevRoutingModule } from './assign-dev-routing.module';
import { AssignDevComponent } from './assign-dev.component';
import { SharedModule } from 'src/shared.module';


@NgModule({
  declarations: [AssignDevComponent],
  imports: [
    CommonModule,
    AssignDevRoutingModule,
    SharedModule
  ]
})
export class AssignDevModule { }
