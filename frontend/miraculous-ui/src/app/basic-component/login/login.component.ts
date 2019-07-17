import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {VisitorAuthorizationDto} from "../../../model/visitorAuthorizationDto/visitor-authorization-dto";
import {AuthServiceService} from "../../../services/auth-service/auth-service.service";
import {TokenStorageServiceService} from "../../../services/token-storage-service/token-storage-service.service";

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  visitor: VisitorAuthorizationDto = new VisitorAuthorizationDto();
  isRememberMeAuthModeOn: boolean = false;
  isLoggedIn: boolean = false;
  isLoginFailed: boolean = false;
  authorizationErrorMessage = "";
  roles: string[] = [];

  @Output() closedLoginComponent = new EventEmitter<any>();
  @Output() switchedToRegistrationComponent = new EventEmitter<any>();
  @Output() switchedToRememberMeAuthMode = new EventEmitter<any>();
  constructor(private authService: AuthServiceService, private tokenStorageService: TokenStorageServiceService) { }

  ngOnInit() {
      if (this.tokenStorageService.getToken(this.isRememberMeAuthModeOn)){
        this.isLoggedIn = true;
        this.roles = this.tokenStorageService.getAuthorities(this.isRememberMeAuthModeOn);
      }
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

  setRememberMeAuthMode() {
    if (this.isRememberMeAuthModeOn == false){
      this.isRememberMeAuthModeOn = true;
      this.switchedToRememberMeAuthMode.emit(this.isRememberMeAuthModeOn);
    } else {
      this.isRememberMeAuthModeOn = false;
      this.switchedToRememberMeAuthMode.emit(this.isRememberMeAuthModeOn);
    }
  }

  submitLoginForm() {
    this.authService.authorizeVisitor(this.visitor).subscribe(
      data => {
        this.tokenStorageService.saveToken(this.isRememberMeAuthModeOn, data.accessToken);
        this.tokenStorageService.saveVisitorId(this.isRememberMeAuthModeOn, data.visitorId);
        this.tokenStorageService.saveLogin(this.isRememberMeAuthModeOn, data.login);
        this.tokenStorageService.saveAuthorities(this.isRememberMeAuthModeOn, data.authorities);
        this.isLoggedIn = true;
        this.isLoginFailed = false;
        this.roles = this.tokenStorageService.getAuthorities(this.isRememberMeAuthModeOn);
        this.reloadPage();
      },
      error => {

        this.authorizationErrorMessage = error.error.message;
        this.isLoginFailed = true;

      }
    )
  }

  reloadPage(){
    window.location.reload();
  }
}
