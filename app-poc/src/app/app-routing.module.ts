import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComponent } from './view/view.component';
import { InterviewComponent } from './interview/interview.component';
import { PreviewComponent } from './preview/preview.component';
import { DrafteditorComponent } from './drafteditor/drafteditor.component';

const routes: Routes = [
  {path:'',redirectTo:'/home',pathMatch:'full'},
  {path:'home',component:ViewComponent},
  {path:'interview/:id',component:InterviewComponent},
  {path:'preview/:documentName',component:PreviewComponent},
  {path:'draft/:transactiondata',component:DrafteditorComponent}


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
