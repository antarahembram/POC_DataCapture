import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { RouterModule} from '@angular/router';
import { AppComponent } from './app.component';
import { ViewComponent } from './view/view.component';
import { HttpClientModule } from '@angular/common/http';
import { DatacaptureService } from './datacapture.service';
import { InterviewComponent } from './interview/interview.component';
import { PreviewComponent } from './preview/preview.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DocumentPannelComponent } from './document-pannel/document-pannel.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { DrafteditorComponent } from './drafteditor/drafteditor.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    ViewComponent,
    InterviewComponent,
    PreviewComponent,
    DocumentPannelComponent,
    DrafteditorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    PdfViewerModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    NgbModule
  ],
  
  providers: [DatacaptureService],
  bootstrap: [AppComponent]
})
export class AppModule { }
