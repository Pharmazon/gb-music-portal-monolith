import { Injectable } from '@angular/core';
import {DomainServiceService} from "../domain-service/domain-service.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {VisitorAuthorizationDto} from "../../model/visitorAuthorizationDto/visitor-authorization-dto";
import {Observable} from "rxjs";
import {JwtResponseDto} from "../../model/jwt-response-dto/jwt-response-dto";

const httpOptions = {
  headers: new HttpHeaders({'ContentType':'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  authorizationApiUrl: string = DomainServiceService.DOMAIN + "/api/miraculous/authorization";

  constructor(private httpClient: HttpClient) { }

  authorizeVisitor(visitor: VisitorAuthorizationDto): Observable<JwtResponseDto>{
    return this.httpClient.post<JwtResponseDto>(this.authorizationApiUrl, visitor, httpOptions);
  }
}
