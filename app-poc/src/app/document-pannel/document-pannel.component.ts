import { Component, OnInit } from '@angular/core';
import {DatacaptureService} from '../datacapture.service';
// import {listFiles} from 'list-files-in-dir';
// import * as fs from 'fs';
@Component({
  selector: 'app-document-pannel',
  templateUrl: './document-pannel.component.html',
  styleUrls: ['./document-pannel.component.css']
})
export class DocumentPannelComponent implements OnInit {

  public listDoc:any;
  constructor(private _dataCaptureService:DatacaptureService) { }
  public nothing="No document found";
  ngOnInit(): void {
    this._dataCaptureService.getList().subscribe((res)=>{console.log(res)
    this.listDoc=res;
    
    })

  }
  

}
