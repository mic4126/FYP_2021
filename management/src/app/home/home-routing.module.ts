import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssignDevComponent } from '../admin/assign-dev/assign-dev.component';
import { CreateProjectComponent } from '../admin/create-project/create-project.component';
import { CreateUserComponent } from '../admin/create-user/create-user.component';
import { DeleteUserComponent } from '../admin/delete-user/delete-user.component';
import { ChangeUserInfoComponent } from '../common/change-user-info/change-user-info.component';
import { AdminGuard } from '../guard/admin.guard';
import { DevGuard } from '../guard/dev.guard';
import { LoginGuard } from '../guard/login-guard.guard';
import { IndexComponent } from '../index/index.component';
import { HomeComponent } from './home.component';

const routes: Routes = [{
  path: 'home', component: HomeComponent,
  canActivate: [LoginGuard],
  canActivateChild: [LoginGuard],
  children: [
    { path: 'index', component: IndexComponent, canActivate: [LoginGuard] },
    { path: 'create-user', component: CreateUserComponent, canActivate: [AdminGuard] },
    { path: 'create-project', component: CreateProjectComponent, canActivate: [AdminGuard] },
    { path: 'delete-user', component: DeleteUserComponent, canActivate: [AdminGuard] },
    { path: 'assign-dev', component: AssignDevComponent, canActivate: [AdminGuard] },
    {
      path: 'edit-project',
      loadChildren: () => import('../dev/dev.module').then(m => m.DevModule),
      canActivate: [DevGuard]
    },
    { path: 'change-info', component: ChangeUserInfoComponent, canActivate: [LoginGuard] },
    {
      path: 'change-password', canActivate: [LoginGuard],
      loadChildren: () => import('../common/change-password/change-password.module')
        .then(m => m.ChangePasswordModule)
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

