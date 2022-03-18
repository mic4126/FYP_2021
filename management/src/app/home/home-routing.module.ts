import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
    {
      path: 'create-user',
      loadChildren: () => import('../admin/create-user/create-user.module')
        .then(m => m.CreateUserModule)
      , canActivate: [AdminGuard]
    },
    {
      path: 'create-project',
      loadChildren: () => import('../admin/create-project/create-project.module')
        .then(m => m.CreateProjectModule)
      , canActivate: [AdminGuard]
    },
    {
      path: 'delete-user',
      loadChildren: () => import('../admin/delete-user/delete-user.module')
        .then(m => m.DeleteUserModule)
      , canActivate: [AdminGuard]
    },
    {
      path: 'assign-dev',
      loadChildren: () => import('../admin/assign-dev/assign-dev.module').then(m => m.AssignDevModule),
      canActivate: [AdminGuard]
    },
    {
      path: 'edit-project',
      loadChildren: () => import('../dev/dev.module').then(m => m.DevModule),
      canActivate: [DevGuard]
    },
    {
      path: 'change-info',
      loadChildren: () => import('../common/change-user-info/change-user-info.module')
        .then(m => m.ChangeUserInfoModule)
      , canActivate: [LoginGuard]
    },
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

