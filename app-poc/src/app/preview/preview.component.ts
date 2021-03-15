import { Component, OnInit } from '@angular/core';
import { DatacaptureService } from '../datacapture.service';
import { Route } from '@angular/compiler/src/core';
import { Router ,ActivatedRoute} from '@angular/router';
import {PDFSource} from 'ng2-pdf-viewer'
import { NgxSpinnerService } from "ngx-spinner";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.css']
})
export class PreviewComponent implements OnInit {

  constructor(private _dataCaptureService:DatacaptureService,private router:Router,private activeroute:ActivatedRoute,private spinner:NgxSpinnerService) { }
  public pdfSrc:any;

  
  async ngOnInit() {
    this.spinner.show();
    let documentName=this.activeroute.snapshot.paramMap.get('documentName');

    this.pdfSrc ="../assets/document/"+documentName; 
    console.log(this.pdfSrc)
    this.spinner.hide();
  }

  
  gotoHome()
  {
    this.router.navigateByUrl('/home');
  }

}
