import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-basic-component',
  templateUrl: './basic-component.component.html',
  styleUrls: ['./basic-component.component.css']
})
export class BasicComponentComponent implements OnInit{

  currentYear: Date = new Date();
  isPlayerHidden: boolean = false;

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

  @ViewChild("internalizationPanelComponent", {read: ElementRef})
  internalizationComponent: ElementRef;

  constructor(private router: Router, private translator: TranslateService) { }

  ngOnInit() {

  }

  navigate(path: string) {
    this.router.navigate(['miraculous/'+ path]);
  }

  hideOtherComponents(){

    this.darkeningOverflow.nativeElement.style = "display: block";
    this.navigationPanel.nativeElement.style = "opacity: 0.5; transition: 2s";
    this.basicComponentContainer.nativeElement.style = "pointer-events: none";
  }

  showOtherComponents(){

    this.darkeningOverflow.nativeElement.style = "display: none";
    this.navigationPanel.nativeElement.style = "opacity: 1";
    this.basicComponentContainer.nativeElement.style = "pointer-events: all";
  }

  showLoginComponent(){


    this.loginComponent.nativeElement.style = "z-index: 1000000 ;opacity: 1; pointer-events: all; transition: 1s; position: fixed; width: 930px; height: 510px; top:25%";
    this.hideOtherComponents();

  }
  closeLoginComponent() {


    this.loginComponent.nativeElement.style = "z-index: -1000000; opacity:0; position: absolute; width: 0; height: 0; pointer-events: none; top: 0%";
    this.showOtherComponents();
  }

  showRegistrationComponent(){

     this.registrationComponent.nativeElement.style = "z-index: 1000000; opacity: 1; transition: 1s; pointer-events: all; position: fixed; width: 930px; height: 510px; top: 25%";
     this.hideOtherComponents();

  }

  closeRegistrationComponent(){


    this.registrationComponent.nativeElement.style = "z-index: -1000000;opacity:0; position: absolute; width: 0; height: 0; pointer-events: none; top: 0%";
    this.showOtherComponents();
  }

  switchToLoginForm(){

    this.closeRegistrationComponent();
    this.showLoginComponent();
  }


  switchToRegistrationForm() {
    this.showRegistrationComponent();
    this.closeLoginComponent();
  }

  showInternalizationPanel() {
    this.hideOtherComponents();
    this.internalizationComponent.nativeElement.style = "display: block;transition: 2s; opacity: 1; width: 500px; height: 500px; position: fixed; pointer-events: all; z-index: 100000";
  }

  closeInternalizationPanel() {
    this.showOtherComponents();
    this.internalizationComponent.nativeElement.style= "display: none ;opacity:0; position: absolute; width: 0; height: 0; pointer-events: none;";
  }

  showOrHidePlayer() {

    if (this.isPlayerHidden == true){
      document.querySelector(".player").setAttribute("style", "height: 75px");
      document.querySelector(".queue-panel-block").setAttribute("style","display: flex");
      document.querySelector(".toggle-player-block-button").setAttribute("style","background-image: url(../../../assets/images/chevron-down.png);");
      this.isPlayerHidden = false;
    } else {
      document.querySelector(".player").setAttribute("style", "height: 0.1px");
      document.querySelector(".queue-panel-block").setAttribute("style","display: none");
      document.querySelector(".toggle-player-block-button").setAttribute("style","background-image: url(../../../assets/images/chevron-up.png);");
      this.isPlayerHidden = true;
    }

  }
}
