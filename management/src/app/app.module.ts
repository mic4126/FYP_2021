import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { IndexComponent } from './index/index.component';
import { JwtModule } from '@auth0/angular-jwt';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { HomeComponent } from './home/home.component';
import { HomeModule } from './home/home.module';
import { CreateUserComponent } from './admin/create-user/create-user.component';
import { CreateProjectComponent } from './admin/create-project/create-project.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { DeleteUserComponent } from './admin/delete-user/delete-user.component';
import { AssignDevComponent } from './admin/assign-dev/assign-dev.component';
import { EditProjectComponent } from './dev/edit-project/edit-project.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DescComponent } from './dev/edit-project/desc/desc.component';
import { EditDescComponent } from './dev/edit-project/desc/edit-desc/edit-desc.component';
import { EditProjectNameComponent } from './dev/edit-project/edit-project-name/edit-project-name.component';
import { EditContactComponent } from './dev/edit-project/edit-contact/edit-contact.component';
import { EditTagComponent } from './dev/edit-project/edit-tag/edit-tag.component';
import { DetailsComponent } from './dev/edit-project/details/details.component';
import { EditDetailComponent } from './dev/edit-project/details/edit-detail/edit-detail.component';
import { PhotoComponent } from './dev/edit-project/photo/photo.component';
import { ChangeUserInfoComponent } from './common/change-user-info/change-user-info.component';
import { ChangePasswordComponent } from './common/change-password/change-password.component';
import { NoticeComponent } from './common/notice/notice.component';
import { ErrorInterceptor } from './interceptor/error.interceptor';
import { ProjectStatusComponent } from './dev/edit-project/project-status/project-status.component';
import { ForgetPasswordModule } from './forget-password/forget-password.module';
import { NoticeModule } from './common/notice/notice.module';

export function jwtGetter() {
  return localStorage.getItem("jwt");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    IndexComponent,
    HomeComponent,
    CreateUserComponent,
    CreateProjectComponent,
    DeleteUserComponent,
    AssignDevComponent,
    EditProjectComponent,
    DescComponent,
    EditDescComponent,
    EditProjectNameComponent,
    EditContactComponent,
    EditTagComponent,
    DetailsComponent,
    EditDetailComponent,
    PhotoComponent,
    ChangeUserInfoComponent,
    ChangePasswordComponent,    
    ProjectStatusComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    HomeModule,
    NgSelectModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: jwtGetter,
        allowedDomains: ["localhost:4200"],
        disallowedRoutes: [
          "//localhost:4200/api/login",
          "//localhost:4200/api/forgetpassword"
        ]
      }
    }),
    NgbModule,
    NoticeModule,
    AppRoutingModule,

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
