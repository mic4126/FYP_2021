import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DevRoutingModule } from './dev-routing.module';
import { DescComponent } from './edit-project/desc/desc.component';
import { EditDescComponent } from './edit-project/desc/edit-desc/edit-desc.component';
import { DetailsComponent } from './edit-project/details/details.component';
import { EditDetailComponent } from './edit-project/details/edit-detail/edit-detail.component';
import { EditContactComponent } from './edit-project/edit-contact/edit-contact.component';
import { EditProjectNameComponent } from './edit-project/edit-project-name/edit-project-name.component';
import { EditProjectComponent } from './edit-project/edit-project.component';
import { EditTagComponent } from './edit-project/edit-tag/edit-tag.component';
import { PhotoComponent } from './edit-project/photo/photo.component';
import { ProjectStatusComponent } from './edit-project/project-status/project-status.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { NoticeModule } from '../common/notice/notice.module';
import { HomeModule } from '../home/home.module';


@NgModule({
  declarations: [
    EditProjectComponent,
    DescComponent,
    EditDescComponent,
    EditProjectNameComponent,
    EditContactComponent,
    EditTagComponent,
    DetailsComponent,
    EditDetailComponent,
    PhotoComponent,
    ProjectStatusComponent,    
  ],
  imports: [
    CommonModule,
    DevRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    HomeModule,
    NgSelectModule,
    FormsModule,
    NgbModule,
    NoticeModule,
  ]
})
export class DevModule { }
