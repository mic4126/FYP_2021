import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateProjectRoutingModule } from './create-project-routing.module';
import { CreateProjectComponent } from './create-project.component';
import { SharedModule } from 'src/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';


@NgModule({
  declarations: [CreateProjectComponent],
  imports: [
    CommonModule,
    CreateProjectRoutingModule,
    NgbModule,
    NgSelectModule,
    SharedModule
  ]
})
export class CreateProjectModule { }
