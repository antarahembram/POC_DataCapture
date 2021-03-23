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
  draftXmlUrl : string = 'http://localhost:8080/api/v1/draftXml';
  draftDocGenUrl : string = 'http://localhost:8080/api/v1/generateDraft';

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

  getDraftXml(transactionData:any):Observable<any>
  {
    return this.http.post<any>(this.draftXmlUrl,transactionData)
  }

  generateDocumentDraft(transactionData:any):Observable<any>
  {
    return this.http.post<any>(this.draftDocGenUrl,transactionData)

  }
}
