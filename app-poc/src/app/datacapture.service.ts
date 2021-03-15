import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams} from '@angular/common/http';
import { Observable} from 'rxjs';
//import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class DatacaptureService {
  constructor(private http: HttpClient) { }

  interviewUrl : string ='http://localhost:8080/api/v1/interviewtemplates';
  docUrl : string ='http://localhost:8080/api/v1/generate';
  proectIdUrl : string ='http://localhost:8080/api/v1/getproject/';
  locationUrl : string='http://localhost:8080/api/v1/getDocList'
  
  // private loginUrl sss:string= 'https://na4.smartcommunications.cloud/one/idm_login';

  // login2()
  // {
  //   return this.http.get('http://localhost:8080/api/v1/auth')
  // }
  // login(username:any,password:any)
  // {
  //   const body = new HttpParams()
  //   .set(`username`, username)
  //   .set(`password`, password);
  // const headers = new HttpHeaders()
  // .set('content-type','application/x-www-form-urlencoded')
  // .set('Access-Control-Allow-Origin','http://localhost/4200/')
  // .set('Access-Control-Allow-Credentials','true' )
  // .set('Access-Control-Allow-Methods','POST')
  // .set('Access-Control-Request-Headers','Special-Request-Header')
  //   console.log(body.toString)
  //   console.log(headers)
  //   return this.http.post(this.loginUrl,body.toString(),{ headers, observe: 'response' })
  // }
  getInterviewForms():Observable<any>
  {
  
    return this.http.get<any>(this.interviewUrl);
  }
  getProjectId(resourceId:any)
  {
    return this.http.get<any>(this.proectIdUrl+resourceId);
  }

  getDocument( transactionData:any):Observable<any>
  {
    return this.http.post<any>(this.docUrl,transactionData)
  }

  getList()
  {
     return this.http.get(this.locationUrl);
  }
}
