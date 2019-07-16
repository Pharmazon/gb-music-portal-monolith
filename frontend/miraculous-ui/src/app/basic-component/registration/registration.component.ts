import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {User} from "../../../model/user/user";
import {VisitorRegistrationDto} from "../../../model/visitorRegistrationDto/visitor-registration-dto";
import {RegistrationServiceService} from "../../../services/registration-service/registration-service.service";

@Component({
  selector: 'registration-component',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  visitor: VisitorRegistrationDto = new VisitorRegistrationDto();
  isArtistRoleChosen: boolean = false;
 
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

    console.log(this.registrationService.registrationApi);
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
