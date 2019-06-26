import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'registration-component',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Output() closedRegistrationComponent = new EventEmitter<any>();
  @Output() switchedToLoginComponent = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }


  closeRegistrationComponent() {
    this.closedRegistrationComponent.emit();
  }

  switchToLoginComponent() {
    this.switchedToLoginComponent.emit();
  }
}
