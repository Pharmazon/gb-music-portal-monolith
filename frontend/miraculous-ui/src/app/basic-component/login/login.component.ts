import {Component, EventEmitter, OnInit, Output} from '@angular/core';



@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() closedLoginComponent = new EventEmitter<any>();
  @Output() switchedToRegistrationComponent = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  closeLoginComponent() {
      this.closedLoginComponent.emit();
  }

  switchToRegistrationComponent() {
    this.switchedToRegistrationComponent.emit();
  }
}
