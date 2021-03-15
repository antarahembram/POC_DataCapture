import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComponent } from './view/view.component';
import { InterviewComponent } from './interview/interview.component';
import { PreviewComponent } from './preview/preview.component';

const routes: Routes = [
  {path:'',redirectTo:'/home',pathMatch:'full'},
  {path:'home',component:ViewComponent},
  {path:'interview/:id',component:InterviewComponent},
  {path:'preview/:documentName',component:PreviewComponent}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
