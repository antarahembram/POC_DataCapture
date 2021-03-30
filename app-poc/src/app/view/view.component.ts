import { Component, OnInit,Output ,EventEmitter} from '@angular/core';
import { DatacaptureService } from '../datacapture.service';
import { Router } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { NgxSpinnerService } from "ngx-spinner";
import * as JsonToXML from "js2xmlparser";


@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  public list =[];
  public id:any;
  public fileData:any;
  fileContent: any;
  public prefilledData: any;

  constructor(private _dataCaptureService:DatacaptureService,private router:Router,private http:HttpClient,private spinner: NgxSpinnerService) { }
  public model:any;


  async ngOnInit() {

    this.http.get('../assets/userdetails.json').subscribe((res)=>{console.log(res);
    this.prefilledData=res;
    })
      this.spinner.show();
    
      
    await this._dataCaptureService.getInterviewForms()
        .subscribe((res)=>{
        this.list = res.results
        for(let i=0;i<this.list.length;i++)
        {
            if(this.list[i]['description']=="")
            {
              this.list.splice(i,i+1);
              break;
            }
        }
        console.log(this.list)
   this.spinner.hide()

      });
     
  
  }

  
  form = new FormGroup({
    interviewList: new FormControl('', [Validators.required,Validators.minLength(0)]),
    files: new FormControl(null, Validators.required)
  });
  
  get f(){
    return this.form.controls;
  }
  
  onFileChange(event:any){
    let reader = new FileReader();

  
      this.fileData = event.target.files[0];
      console.log(event.target.files)
      reader.onload = () => {
         this.fileContent = reader.result;
      };
    reader.readAsText(this.fileData)
  }

  submit(){
    
    this.id= (this.form.value.interviewList);
    var userId=this.form.value.files;
    console.log(userId);
    
    if(!this.form.value.interviewList)
    {
      alert("Please select one template")
    }
    else{
    if(userId!=null)
    {
      var m=this.prefilledData.userIds;
      console.log(m);
      var ind=-1;
      var showAlert=-1;
      for(ind=0;ind<m.length;ind++)
      {
        if(m[ind]==userId)
        {
          showAlert=ind;
          break;
        }
      }
      console.log(showAlert);
      if(showAlert==-1)
      {
        alert("No records matched, kindly enter manually.")
      }
      else{
      var a=JsonToXML.parse("dataCapture",this.prefilledData.userDetails[ind]);
      console.log(a);
      if(  !a.includes("formId")){
          for(var i=a.length-1;i>0;i--)
          {
            console.log(a[i])
            if(a[i]=="/" && a[i-1]=="<")
            {
              break;
            }
          }
        var formID = "<formId>"+ this.id+"</formId>";
        var output= [a.slice(0, i-1), formID, a.slice(i-1)].join('');
        console.log(output);

        output=btoa(output);
        this.router.navigateByUrl('interview/'+this.id+","+output)
      }}
    }
    else{
      this.router.navigateByUrl('interview/'+this.id+"," +" ")
    }
    }
         
  }

  changeWebsite(e:any) {
    console.log(e.target.value);
  }
  
  gotoHome()
{

  this.router.navigateByUrl('/home');
}

 
}
