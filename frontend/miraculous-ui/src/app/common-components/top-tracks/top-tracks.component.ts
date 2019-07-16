import { Component, OnInit } from '@angular/core';
import {CartServiceService} from "../../../services/cart-service/cart-service.service";

@Component({
  selector: 'app-top-tracks',
  templateUrl: './top-tracks.component.html',
  styleUrls: ['./top-tracks.component.css']
})
export class TopTracksComponent implements OnInit {

  constructor(private cartService: CartServiceService) {

  }

  ngOnInit() {
  }

}
