import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {User} from "../../../model/user/user";
import {VisitorRegistrationDto} from "../../../model/visitorRegistrationDto/visitor-registration-dto";
import {RegistrationServiceService} from "../../../services/registration-service/registration-service.service";
import {HttpHeaders} from "@angular/common/http";



@Component({
  selector: 'registration-component',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  visitor: VisitorRegistrationDto = new VisitorRegistrationDto();
  isArtistRoleChosen: boolean = false;
  registrationErrorMessage: string = "";
  isRegistrationSuccessful: boolean = false;
  isRegistrationFailed: boolean = false;
 
  @Output() closedRegistrationComponent = new EventEmitter<any>();
  @Output() switchedToLoginComponent = new EventEmitter<any>();

  constructor(private registrationService: RegistrationServiceService) { }

  ngOnInit() {
  }

  resetRegistrationForm(){

    (<HTMLElement>document.querySelector(".registration-form-reset-button")).click();
    this.isArtistRoleChosen = false;

  }

  closeRegistrationComponent() {
    this.closedRegistrationComponent.emit();
    this.resetRegistrationForm();
  }

  switchToLoginComponent() {
    this.switchedToLoginComponent.emit();
    this.resetRegistrationForm();
  }

  submitRegistrationForm() {

    this.registrationService.registerVisitor(this.visitor).subscribe(
      data => {
        this.isRegistrationFailed = false;
        this.isRegistrationSuccessful = true;
      },
      error => {
        this.registrationErrorMessage = error.error.message;
        this.isRegistrationFailed = true;
      }
    )
  }

  showAdditionalDataPanel() {
    this.isArtistRoleChosen = true;
    console.log(this.isArtistRoleChosen);
  }

  hideAdditionalDataPanel() {
    this.isArtistRoleChosen = false;
    console.log(this.isArtistRoleChosen);
  }
}
