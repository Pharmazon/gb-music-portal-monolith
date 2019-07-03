import {AfterViewInit, Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'audiotrack-with-details',
  templateUrl: './audiotrack-with-details.component.html',
  styleUrls: ['./audiotrack-with-details.component.css']
})
export class AudiotrackWithDetailsComponent implements OnInit, AfterViewInit {

  trackName: string;
  trackSize: number;
  trackDuration: number;



  @Output() onFileChanged = new EventEmitter<any>();

  @ViewChild("fileInput", {read: ElementRef})
  fileInput : ElementRef;

  @ViewChild("addedTrackInputLabel", {read: ElementRef})
  addedTrackInputLabel: ElementRef;

  @ViewChild("audio", {read: ElementRef})
  audio: ElementRef;

  constructor(private domSanitizer: DomSanitizer) { }

  ngOnInit() {

    this.fileInput.nativeElement.addEventListener("change", ()=>{

    });
  }

  ngAfterViewInit(): void {

    this.fileInput.nativeElement.addEventListener("change", ()=>{

    });
  }

  // addInfoOfTrack() {
  //
  //   let uploadedTrackPath = this.fileInput.nativeElement.files[0];
  //   let audio = document.querySelector("audio");
  //   this.trackSize = this.fileInput.nativeElement.files[0].size;
  //   this.audio.nativeElement.src = URL.createObjectURL(uploadedTrackPath);
  //   this.audio.nativeElement.onloadeddata = ()=> {
  //     this.trackDuration = Math.round(audio.duration/60);
  //     console.log(this.trackDuration);
  //   }
  //   this.onFileChanged.emit(this.trackDuration);
  // }

}
