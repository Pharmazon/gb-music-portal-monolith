import {
  AfterContentChecked,
  AfterViewInit,
  Component,
  ComponentFactoryResolver,
  ElementRef, OnChanges,
  OnInit, SimpleChanges,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {TagComponent} from "../upload-album/tag/tag.component";
import {AudiotrackComponent} from "./audiotrack/audiotrack.component";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-upload-track',
  templateUrl: './upload-track.component.html',
  styleUrls: ['./upload-track.component.css']
})
export class UploadTrackComponent implements OnInit, AfterViewInit, OnChanges, AfterContentChecked {

  trackDuration: number = 0;
  trackSize: number = 0;
  tagText: string;

  @ViewChild("albumCoverPicture", {read: ElementRef})
  fileName: ElementRef;

  @ViewChild("albumCover", {read: ElementRef})
  coverBlock: ElementRef;

  @ViewChild("addedTracksBlock", {read: ElementRef})
  addedTracksBlock: ElementRef;

  @ViewChild("selectAccessPolicy", {read: ElementRef})
  selectAccessPolicy: ElementRef;

  @ViewChild("albumPriceInput", {read: ElementRef})
  albumPriceInput: ElementRef;

  @ViewChild("albumPriceInputLabel", {read: ElementRef})
  albumPriceInputLabel: ElementRef;

  @ViewChild("selectedGenres", {read: ElementRef})
  selectedGenres: ElementRef;

  @ViewChild("selectedGenresVcf", {read: ViewContainerRef})
  selectedGenresVcf: ViewContainerRef;

  @ViewChild("uploadedTrackContainer", {read: ViewContainerRef})
  uploadedTrackContainer: ViewContainerRef;

  @ViewChild("selectAlbumGenres", {read: ElementRef})
  selectAlbumGenres: ElementRef;

  @ViewChild("singleTrackUploadInput", {read: ElementRef})
  singleTrackUploadInput: ElementRef;

  @ViewChild("singleTrackUploadInputLabel", {read: ElementRef})
  singleTrackUploadInputLabel: ElementRef

  @ViewChild("uploadedTrackTemplate", {read: ElementRef})
  uploadedTrackTemplate: ElementRef;

  constructor(private componentFactoryResolver: ComponentFactoryResolver, private domSanitizer: DomSanitizer) { }

  ngOnInit() {

  }

  uploadAndShowNewTrack(){
    let uploadedTrackPath = this.singleTrackUploadInput.nativeElement.files[0];
    let uploadedTrackSize = this.singleTrackUploadInput.nativeElement.files[0].size/(1024*1024);
    this.trackSize = Math.round(uploadedTrackSize);
    let audioTrackComponentFactory = this.componentFactoryResolver.resolveComponentFactory(AudiotrackComponent);
    let audioTrack = this.uploadedTrackContainer.createComponent(audioTrackComponentFactory);
    audioTrack.location.nativeElement.className = "uploaded-track";
    audioTrack.instance.trackSource = this.domSanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(uploadedTrackPath));
    this.singleTrackUploadInputLabel.nativeElement.textContent = this.singleTrackUploadInput.nativeElement.files[0].name;
    let audio = document.querySelector("audio");
    audio.onloadeddata = ()=> {
      this.trackDuration = Math.round(audio.duration/60);
    }

  }

  ngAfterViewInit(){

    this.singleTrackUploadInput.nativeElement.addEventListener("change", ()=> {

      if (this.uploadedTrackContainer.length > 0){
        this.uploadedTrackContainer.clear();
        this.uploadAndShowNewTrack();

      }else {
        this.uploadAndShowNewTrack();
      }
    });
  }

  showCoverPicture(){

    let filePath = URL.createObjectURL(this.fileName.nativeElement.files[0]);
    this.coverBlock.nativeElement.style = "background-image: url("+filePath+")";
    if (this.addedTracksBlock.nativeElement.children.length > 0) {
      for (let i = 0; i < this.addedTracksBlock.nativeElement.children.length; i++) {
        this.addedTracksBlock.nativeElement.children[i].querySelector(".added-track-cover").style.backgroundImage = "url("+filePath+")";
      }
    }
  }

  togglePriceBlock(){

    let isPaidPolicySelected = this.selectAccessPolicy.nativeElement.options.selectedIndex;
    if (isPaidPolicySelected == 1) {

      this.albumPriceInput.nativeElement.style = "opacity: 0";
      this.albumPriceInputLabel.nativeElement.style = "opacity: 0";

    }else {
      this.albumPriceInput.nativeElement.style = "opacity: 1";
      this.albumPriceInputLabel.nativeElement.style = "opacity: 1";
    }
  }

  addTagToSelectedGenres(){

    let tagsContainer = document.querySelector(".selected-genres");
    let selectedIndex = this.selectAlbumGenres.nativeElement.options.selectedIndex;
    let selectedGenre = this.selectAlbumGenres.nativeElement.options[selectedIndex].value;
    for(let i=0; i < tagsContainer.children.length; i++){

      if (this.selectedGenres.nativeElement.children.length == 0) {
        continue;
      }else {
        if (this.selectedGenres.nativeElement.children[i].textContent == selectedGenre) {
          return;
        }
      }
    }
    let tagFactory = this.componentFactoryResolver.resolveComponentFactory(TagComponent);
    let componentRef = this.selectedGenresVcf.createComponent(tagFactory);
    componentRef.instance.tagText = selectedGenre;
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(("Change took place"));
  }

  ngAfterContentChecked(): void {

  }
}
