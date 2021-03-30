import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { DatacaptureService } from '../datacapture.service';
declare var Thunderhead:any;

@Component({
  selector: 'app-interview',
  templateUrl: './interview.component.html',
  styleUrls: ['./interview.component.css'],
  
})
export class InterviewComponent implements OnInit {

  

  public  templateResourceId:any;
  public projectId:any;
  public documentName:any;
  public xml:any;
  public dataCaptureComponent:any;
  public showDraft=false;
  constructor(private http:HttpClient, private router:Router,private activeroute:ActivatedRoute,private _datacaptureservice:DatacaptureService) { }
 public dataCaptureTransactionData:any;

   async ngOnInit() {

   

    var id:any=this.activeroute.snapshot.paramMap.get('id');
    var splitted = id.split(",");
    console.log(splitted);
    this.templateResourceId=splitted[0];
    if(splitted[1]!=" "){
    this.xml=atob(splitted[1])}
    else
    {
      this.xml='<?xml version="1.0" encoding="UTF-8"?><dataCapture><formId>'+this.templateResourceId+'</formId></dataCapture>'
    }
    this.dataCaptureComponent = new Thunderhead.DataCaptureComponent({
      'server': 'https://na4.smartcommunications.cloud',
      'context': '/one/data-capture',
      'targetElementID': 'dataCaptureComponentContainer',
      'templateResourceId': this.templateResourceId,
      'projectId':'157697023',
      'loadStartupConfig': function() {
        return {
            'version': '1',
            'showSubmit': true,
            'showOverview': true,
            'showTitle' : true
        };
    },
    
    });

    this.dataCaptureComponent.addListener({
      interviewStarted: async()=> {

        console.log(this.xml)
        this.dataCaptureComponent.setTransactionData(this.xml);
      } 
  });

     this.dataCaptureComponent.addListener({
      interviewCancelled: async()=> {
       alert("Interview cancelled")
      //  dataCaptureComponent.removeListener(interviewStarted)
       this.dataCaptureComponent.destroy()
       this.router.navigateByUrl('')

       
      } 
  });
  this.dataCaptureComponent.addListener({
    interviewFailed: ()=> {
     alert("Interview cancelled")
     this.dataCaptureComponent.destroy()
     this.router.navigateByUrl('')

     
    } 
});

  this.dataCaptureComponent.addListener({
    interviewSubmitted:((transactionData:any)=>{console.log(transactionData);
    this.dataCaptureTransactionData=transactionData.data;
    var a=atob(this.dataCaptureTransactionData)
    if(!a.includes("formId")){
    for(var i=a.length-1;i>0;i--)
    {
      console.log(a[i])
      if(a[i]=="/" && a[i-1]=="<")
      {
        break;
      }
    }
var formID = "<formId>"+this.templateResourceId+"</formId>";
var output= [a.slice(0, i-1), formID, a.slice(i-1)].join('');
console.log(output);

this.dataCaptureTransactionData=btoa(output)  }
console.log(this.dataCaptureTransactionData);
    //this code was for document generation after interview
    //  this._datacaptureservice.getDocument(this.dataCaptureTransactionData).subscribe((res)=>{this.documentName=res;
    // console.log(this.documentName);
    // this.router.navigateByUrl('/preview/'+this.documentName.pdfName);

    this._datacaptureservice.getResource(this.templateResourceId).subscribe((res)=>{console.log(res)
    var resource=res;
    for(i=0;i<resource.keyword.length;i++)
    {
      if(resource.keyword[i]=="EDIT")
      {
        this.showDraft=true;
        break;
      }
    }
    if(this.showDraft)
    {
      this.router.navigateByUrl('/draft/'+encodeURIComponent(this.dataCaptureTransactionData));
    }
    else{
      this._datacaptureservice.getDocument(this.dataCaptureTransactionData).subscribe(
        (res)=>{this.documentName=res;
          console.log(this.documentName);
          this.router.navigateByUrl('/preview/'+this.documentName.pdfName);

        }
      )
    }
    })
    
    // this.router.navigateByUrl('/draft/'+encodeURIComponent(this.dataCaptureTransactionData));
    // this._datacaptureservice.getDraftXml(this.dataCaptureTransactionData).subscribe((res)=>)

  // }+
    this.dataCaptureComponent.destroy();
  })

} 

);

     this.dataCaptureComponent.startInterviewForResource('157697023', this.templateResourceId, null, null);
   
}
delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

gotoHome()
{
  this.dataCaptureComponent.removeListener()
  this.dataCaptureComponent.destroy();
  this.router.navigateByUrl('/home');
}
}
