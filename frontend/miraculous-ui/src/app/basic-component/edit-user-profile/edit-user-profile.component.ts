import {Component, ComponentFactoryResolver, ElementRef, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {TagComponent} from "../upload-album/tag/tag.component";

@Component({
  selector: 'edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  @ViewChild("userPhotoInput", {read: ElementRef})
  userPhotoInput: ElementRef;

  @ViewChild("userProfilePicture", {read: ElementRef})
  userProfilePicture: ElementRef;

  @ViewChild("accountDeletionConfirmationComponent", {read: ElementRef})
  accountDeletionConfirmationComponent: ElementRef;

  @ViewChild("selectFavouriteGenres", {read: ElementRef})
  selectFavouriteGenres: ElementRef;

  @ViewChild("favouriteGenres", {read: ElementRef})
  favouriteGenres: ElementRef;

  @ViewChild("favouriteGenresVsf", {read: ViewContainerRef})
  favouriteGenresVsf: ViewContainerRef;


  constructor(private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit() {

  }

  changeUserPicture() {

    let userPicturePath = URL.createObjectURL(this.userPhotoInput.nativeElement.files[0]);
    this.userProfilePicture.nativeElement.style = "background-repeat: no-repeat; background-size: cover; background-image: url(" + userPicturePath + ")";

  }

  showAccountDeletionBlock() {

    this.accountDeletionConfirmationComponent.nativeElement.style = "pointer-events: all; z-index: 10000;opacity: 1; height: 280px; width: 400px; left: 37%; top: 29%; transition: 2s; position:fixed;";
    this.hideOtherElements();
  }

  closeAccountDeletionBlock() {
    this.accountDeletionConfirmationComponent.nativeElement.style = "pointer:events: none; z-index: 0; opacity: 0; height: 0; width: 0; left: 37%; top: 29%; transition: 0; position:absolute;";
    this.showOtherElements();
  }

  hideOtherElements(){
    document.querySelector(".darkening-overflow").setAttribute("style","display: block; pointer-events: none;");
    document.querySelector(".navigation-panel").setAttribute("style", "opacity: 0.5; transition: 2s");
    document.querySelector("app-basic-component").setAttribute("style", "pointer-events: none");
  }

  showOtherElements(){
    document.querySelector(".darkening-overflow").setAttribute("style","display: none; pointer-events: all;");
    document.querySelector(".navigation-panel").setAttribute("style", "opacity: 1; transition: 2s");
    document.querySelector("app-basic-component").setAttribute("style", "pointer-events: all");
  }

  addTagToSelectedGenres() {

    let tagsContainer = document.querySelector(".favourite-genres-block-selected-genres");
    let selectedIndex = this.selectFavouriteGenres.nativeElement.options.selectedIndex;
    let selectedGenre = this.selectFavouriteGenres.nativeElement.options[selectedIndex].value;
    for (let i = 0; i < tagsContainer.children.length; i++) {

      if (this.favouriteGenres.nativeElement.children.length == 0) {
        continue;
      } else {
        if (this.favouriteGenres.nativeElement.children[i].textContent == selectedGenre) {
          return;
        }
      }
    }
    let tagFactory = this.componentFactoryResolver.resolveComponentFactory(TagComponent);
    let componentRef = this.favouriteGenresVsf.createComponent(tagFactory);
    componentRef.instance.tagText = selectedGenre;
    componentRef.instance.closedElement.subscribe(()=>{
       componentRef.destroy();
    });
  }
}
