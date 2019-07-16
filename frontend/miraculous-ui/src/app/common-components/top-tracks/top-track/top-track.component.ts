import { Component, OnInit } from '@angular/core';
import {Track} from "../../../../model/track/track";
import {CartServiceService} from "../../../../services/cart-service/cart-service.service";

@Component({
  selector: 'top-track',
  templateUrl: './top-track.component.html',
  styleUrls: ['./top-track.component.css']
})
export class TopTrackComponent implements OnInit {


  topTrack: Track;

  constructor(private cartService: CartServiceService) { }

  ngOnInit() {
  }

  addTrackToQueue() {

  }

  addTrackToCart() {
    if (this.cartService.listOfTracks.includes(this.topTrack)){
      this.showPopupTrackAlreadyInCartMessage();
    }else {
      this.cartService.listOfTracks.push(this.topTrack);
      this.showPopupTrackAddedToCartMessage();
    }
  }

  showPopupTrackAlreadyInCartMessage(){

  }

  showPopupTrackAddedToCartMessage(){


  }
}
