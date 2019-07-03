import {
  AfterContentChecked,
  AfterViewInit,
  Component,
  ComponentFactoryResolver, ComponentRef,
  ElementRef, Input,
  OnInit, QueryList,
  ViewChild, ViewChildren,
  ViewContainerRef
} from '@angular/core';

import {TagComponent} from "./tag/tag.component";
import {AudiotrackWithDetailsComponent} from "./audiotrack-with-details/audiotrack-with-details.component";
import {AddEventListenerOptions} from "rxjs/internal/observable/fromEvent";


@Component({
  selector: 'app-upload-album',
  templateUrl: './upload-album.component.html',
  styleUrls: ['./upload-album.component.css']
})


export class UploadAlbumComponent implements OnInit, AfterViewInit {

  albumDuration: number = 0;
  albumSize: number = 0;
  numberOfTracks: number = 0;

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

  @ViewChild("selectAlbumGenres", {read: ElementRef})
  selectAlbumGenres: ElementRef;

  @ViewChild("audiotrack", {read: ElementRef})
  audiotrack: ElementRef;

  @ViewChild("addedTracksContainer", {read: ViewContainerRef})
  addedTracksContainer: ViewContainerRef;



  constructor(private componentFactoryResolver: ComponentFactoryResolver ) {

  }

  ngOnInit() {

  }



  updateAlbumDuration(){

    let addedTracksBlock = document.querySelector(".added-tracks-block");
    this.numberOfTracks = addedTracksBlock.children.length;

  }

  updateAlbumSize(){


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


  createNewAlbumTrack(){

    let albumCoverInput = this.fileName.nativeElement.files[0];
    if (this.addedTracksBlock.nativeElement.children.length == 20) {
      return;
    }else{

      let trackFactory = this.componentFactoryResolver.resolveComponentFactory(AudiotrackWithDetailsComponent);
      let trackComponentRef = this.addedTracksContainer.createComponent(trackFactory);
      trackComponentRef.location.nativeElement.querySelector("input").setAttribute("data", (this.numberOfTracks).toString());
      trackComponentRef.location.nativeElement.querySelector("label").setAttribute("data", (this.numberOfTracks).toString());
      this.updateAlbumDuration();
      trackComponentRef.location.nativeElement.addEventListener("change", ()=> {

        console.log(<HTMLElement>event.target);
        let eventTargetElement = (<ElementRef>(<any>event.target));
        console.log((<HTMLElement>event.currentTarget).getAttribute("data"));
        for (let i=0; i < this.addedTracksBlock.nativeElement.children.length; i++){
          console.log(this.addedTracksBlock.nativeElement.children[i].querySelector("input").getAttribute("data"));
          console.log((<HTMLElement>(<any>eventTargetElement).getAttribute("data")));
          if (this.addedTracksBlock.nativeElement.children[i].querySelector("input").getAttribute("data") != (<HTMLElement>(<any>eventTargetElement).getAttribute("data"))) {
            console.log("Не равно");
          }else {
            console.log("Равно");
          }
        };
    });
    }
  }

  ngAfterViewInit(): void {

    this.selectedGenres.nativeElement.onclick = event =>{
      if (event.target.className == "genre-tag-remove-tag-button") {
        event.target.parentNode.remove();

      }
    }

    let fileinput = document.querySelectorAll(".added-track-file-input");
    for (let i = 0; i < fileinput.length; i++){

        fileinput[i].addEventListener("change", ()=> {

            console.log("added track");
        });
    }

    for (let i=0; i< this.addedTracksBlock.nativeElement.children.length; i++){
      console.log("loaded");
    }

    this.addedTracksContainer.element.nativeElement.addEventListener("DOMNodeInserted", ()=>{
      console.log("log inserted");
      let eventTargetElement = (<ElementRef>(<any>event.target));
      console.log(eventTargetElement);
      let fileInput = eventTargetElement.nativeElement;
      for (let i = 0; i < fileInput.length; i++) {
        fileInput[i].addEventListener("change",
          event =>{
            if (fileInput.files[0] != undefined) {
              if ((fileInput.files[0].size)/(1024*1024) > 20) {
                alert("You can't upload track of size more than 20 mbs");
                event.target.files[0] = null;
                eventTargetElement.nativeElement.previousSibling.textContent = "Select File";

              }else{
                eventTargetElement.nativeElement.previousSibling.textContent = event.target.files[0].name;

                console.log(event.target.files[0].duration);
                console.log(event.target.files[0].name);
              }
            }
          }, false);
      }
    }, false);

  }

  checkIfValid(e){
    if (!e.key.match(/[0-9\.]/i)) {
      e.preventDefault();
    }
  }


  getListOfTracks() {
    let multipleInput = <HTMLInputElement>document.querySelector(".multiple-input");
    let listOfLoadedTracks = document.querySelector(".list-of-loaded-tracks");
    listOfLoadedTracks.innerHTML = "";
    if (multipleInput.files.length > 20){
      alert("You cannot upload more than 20 tracks");
      return;
    }
    this.numberOfTracks = multipleInput.files.length;
    for (let i=0; i<multipleInput.files.length; i++){
      this.albumSize += Math.round(multipleInput.files[i].size/(1024*1024));
      let listItem = document.createElement("li");
      let price = document.createElement("input");
      let text = document.createElement("p");
      let audio = document.createElement("audio");
      audio.src = URL.createObjectURL(multipleInput.files[i]);
      text.setAttribute("style", "width: 400px; margin: 0; overflow: hidden; display: flex; height: 22px");
      price.setAttribute("type","number");
      price.setAttribute("step","0.01");
      price.setAttribute("min","0");
      price.setAttribute("placeholder", "track price in $");
      price.setAttribute("style", "width: 105px; border-radius: 7px; outline: none; padding-left: 10px");
      price.className= "track-price";
      text.innerText = (i+1)+". "+ multipleInput.files[i].name;
      listItem.className= "listItem";
      listItem.setAttribute("style","margin-bottom: 10px; display: flex;");
      listOfLoadedTracks.setAttribute("style", "position: relative");
      listOfLoadedTracks.appendChild(listItem);
      listItem.appendChild(text);
      listItem.appendChild(price);
      listItem.appendChild(audio);
      audio.onloadedmetadata = ()=> {
        this.albumDuration += Math.round(audio.duration/60);
      };
    }
  }
}

