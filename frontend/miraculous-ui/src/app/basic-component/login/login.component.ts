import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {VisitorAuthorizationDto} from "../../../model/visitorAuthorizationDto/visitor-authorization-dto";



@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  visitor: VisitorAuthorizationDto = new VisitorAuthorizationDto();

  @Output() closedLoginComponent = new EventEmitter<any>();
  @Output() switchedToRegistrationComponent = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  closeLoginComponent() {
      this.closedLoginComponent.emit();
      this.resetAuthorizationForm();
  }

  switchToRegistrationComponent() {
    this.switchedToRegistrationComponent.emit();
    this.resetAuthorizationForm();
  }

  resetAuthorizationForm(){
    (<HTMLElement>document.querySelector(".reset-authorization-form-button")).click();

  }
}
