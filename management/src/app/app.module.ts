import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { IndexComponent } from './index/index.component';
import { JwtModule } from '@auth0/angular-jwt';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { HomeModule } from './home/home.module';
import { CreateUserComponent } from './admin/create-user/create-user.component';
import { CreateProjectComponent } from './admin/create-project/create-project.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { DeleteUserComponent } from './admin/delete-user/delete-user.component';
import { AssignDevComponent } from './admin/assign-dev/assign-dev.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ChangeUserInfoComponent } from './common/change-user-info/change-user-info.component';
import { ErrorInterceptor } from './interceptor/error.interceptor';
import { NoticeModule } from './common/notice/notice.module';

export function jwtGetter() {
  return localStorage.getItem("jwt");
}

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HomeComponent,
    CreateUserComponent,
    CreateProjectComponent,
    DeleteUserComponent,
    AssignDevComponent,
    ChangeUserInfoComponent,
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
