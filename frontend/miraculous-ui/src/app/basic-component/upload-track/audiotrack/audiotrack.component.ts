import {AfterContentChecked, Component, ElementRef, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'audiotrack',
  templateUrl: './audiotrack.component.html',
  styleUrls: ['./audiotrack.component.css']
})
export class AudiotrackComponent implements OnInit, AfterContentChecked, OnChanges {


  trackSource: SafeResourceUrl;
  trackDuration: number;

  constructor() { }

  ngOnInit() {

  }

  ngAfterContentChecked(): void {

  }

  ngOnChanges(changes: SimpleChanges): void {

  }

}
