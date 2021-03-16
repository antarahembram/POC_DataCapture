import { Component, OnInit,Output ,EventEmitter} from '@angular/core';
import { DatacaptureService } from '../datacapture.service';
import { Router } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { NgxSpinnerService } from "ngx-spinner";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
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


  constructor(private _dataCaptureService:DatacaptureService,private router:Router,private http:HttpClient,private spinner: NgxSpinnerService) { }
  public model:any;
  async ngOnInit() {
 
      this.spinner.show();
    
    await this._dataCaptureService.getInterviewForms()
        .subscribe((res)=>{
        this.list = res.results
        console.log(this.list)
   this.spinner.hide()

      });
     
  
  }

  
  form = new FormGroup({
    interviewList: new FormControl('', Validators.required),
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
    console.log(this.form.value)
    console.log(this.fileContent)
   var a=this.fileContent;
    if(this.fileContent &&  !a.includes("formId")){
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
     this.router.navigateByUrl('interview/'+this.id+","+output)}
    
    else{
     this.router.navigateByUrl('interview/'+this.id+"," +" ")

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
