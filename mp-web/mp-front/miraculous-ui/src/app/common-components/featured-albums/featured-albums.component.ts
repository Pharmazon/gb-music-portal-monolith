import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-featured-albums',
  templateUrl: './featured-albums.component.html',
  styleUrls: ['./featured-albums.component.css']
})
export class FeaturedAlbumsComponent implements OnInit {

  items: Array<any> = [];
  constructor() {

    this.items = [

      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
      {name: "assets/images/artist.jpg"},
    ]
  }

  ngOnInit() {
  }

}





