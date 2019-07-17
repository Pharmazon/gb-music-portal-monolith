import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {VisitorRegistrationDto} from "../../model/visitorRegistrationDto/visitor-registration-dto";
import {DomainServiceService} from "../domain-service/domain-service.service";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RegistrationServiceService {

  registrationApi: string = DomainServiceService.DOMAIN + "/api/registration";

  constructor(private httpClient: HttpClient) { }

  registerVisitor(visitor: VisitorRegistrationDto): Observable<string>{
     return this.httpClient.post<string>(this.registrationApi, visitor, httpOptions);
  }
}
