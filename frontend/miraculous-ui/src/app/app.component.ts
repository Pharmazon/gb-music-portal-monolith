import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  currentYear: Date = new Date();


  showLoginForm(){
     
  }

  showRegistrationForm(){

    let registrationButton = document.getElementsByClassName("register-button");

  }
}
