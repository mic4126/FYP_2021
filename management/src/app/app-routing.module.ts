import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: "login", loadChildren: () => import('./login/login.module').then(m => m.LoginModule) },
  { path: "", redirectTo: "login", pathMatch: "full" },
  {
    path: "forgetpassword", loadChildren: () =>
      import('./forget-password/forget-password.module')
        .then(m => m.ForgetPasswordModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
