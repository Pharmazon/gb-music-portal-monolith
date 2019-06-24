import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import * as $ from 'jquery';


@Component({
  selector: 'app-basic-component',
  templateUrl: './basic-component.component.html',
  styleUrls: ['./basic-component.component.css']
})
export class BasicComponentComponent implements OnInit {

  currentYear: Date = new Date();


  constructor(private router: Router) { }

  ngOnInit() {

  }

  showLoginComponent() {
    let loginComponent = document.getElementsByClassName("login-component")[0];
    let body = document.body;
    let darkeningOverflow = document.getElementsByClassName("darkening-overflow")[0];
    let navigationPanel = document.getElementsByClassName("navigation-panel")[0];
    navigationPanel.setAttribute("style", "opacity: 0.5");
    body.setAttribute("style", "pointer-events: none");
    loginComponent.setAttribute("style", "opacity:1; transition: 2s; z-index: 1000; position:fixed; pointer-events: all");
    darkeningOverflow.setAttribute("style", "display: block");

  }

  navigate(path: string) {
    this.router.navigate(['miraculous/'+ path]);
  }

  closeLoginComponent() {
    let loginComponent = document.getElementsByClassName("login-component")[0];
    let body = document.body;
    let darkeningOverflow = document.getElementsByClassName("darkening-overflow")[0];
    let navigationPanel = document.getElementsByClassName("navigation-panel")[0];
    navigationPanel.setAttribute("style", "opacity: 1");
    body.setAttribute("style", "pointer-events: all");
    loginComponent.setAttribute("style", "opacity: 0");
    darkeningOverflow.setAttribute("style","display: none");

  }
}
