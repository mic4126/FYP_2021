import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssignDevComponent } from './assign-dev.component';

const routes: Routes = [{
  path: "",
  component: AssignDevComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignDevRoutingModule { }
