import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VisitorRegistrationDto} from "../../model/visitorRegistrationDto/visitor-registration-dto";
import {DomainServiceService} from "../domain-service/domain-service.service";

@Injectable({
  providedIn: 'root'
})
export class RegistrationServiceService {

  registrationApi: string = DomainServiceService.DOMAIN + "/api/registration";

  constructor(private httpClient: HttpClient) { }

  registerVisitor(visitor: VisitorRegistrationDto){


  }
}
