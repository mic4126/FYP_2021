import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChangeUserInfoComponent } from './change-user-info.component';

const routes: Routes = [{
  path: "",
  component: ChangeUserInfoComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChangeUserInfoRoutingModule { }
