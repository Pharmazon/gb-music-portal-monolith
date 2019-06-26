import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-basic-component',
  templateUrl: './basic-component.component.html',
  styleUrls: ['./basic-component.component.css']
})
export class BasicComponentComponent implements OnInit{

  currentYear: Date = new Date();

  @ViewChild("navigationPanel")
  navigationPanel: ElementRef;


  @ViewChild("darkeningOverflow")
  darkeningOverflow: ElementRef;

  @ViewChild("loginComponent", {read: ElementRef})
  loginComponent: ElementRef;

  @ViewChild("container")
  basicComponentContainer: ElementRef;

  @ViewChild("registrationComponent", {read: ElementRef})
  registrationComponent: ElementRef;

  constructor(private router: Router) { }

  ngOnInit() {

  }

  navigate(path: string) {
    this.router.navigate(['miraculous/'+ path]);
  }

  hideOtherComponents(){

    this.darkeningOverflow.nativeElement.style = "display: block";
    this.navigationPanel.nativeElement.style = "opacity: 0.5";
    this.basicComponentContainer.nativeElement.style = "pointer-events: none";
  }

  showOtherComponents(){

    this.darkeningOverflow.nativeElement.style = "display: none";
    this.navigationPanel.nativeElement.style = "opacity: 1";
    this.basicComponentContainer.nativeElement.style = "pointer-events: all";
  }

  showLoginComponent(){

    this.hideOtherComponents();
    this.loginComponent.nativeElement.style = "opacity: 1; pointer-events: all; transition: 2s; position: fixed; width: 930px; height: 510px;";


  }
  closeLoginComponent() {

    this.showOtherComponents();
    this.loginComponent.nativeElement.style = "opacity:0; position: absolute; width: 0; height: 0; pointer-events: none";

  }

  showRegistrationComponent(){

     this.registrationComponent.nativeElement.style = "opacity: 1; transition: 2s; pointer-events: all; position: fixed; width: 930px; height: 510px;";
     this.hideOtherComponents();

  }

  closeRegistrationComponent(){

    this.showOtherComponents();
    this.registrationComponent.nativeElement.style = "opacity:0; position: absolute; width: 0; height: 0; pointer-events: none";

  }

  switchToLoginForm(){

    this.closeRegistrationComponent();
    this.showLoginComponent();
  }


  switchToRegistrationForm() {
    this.showRegistrationComponent();
    this.closeLoginComponent();
  }
}
