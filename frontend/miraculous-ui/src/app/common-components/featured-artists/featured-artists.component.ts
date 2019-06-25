import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-featured-artists',
  templateUrl: './featured-artists.component.html',
  styleUrls: ['./featured-artists.component.css']
})
export class FeaturedArtistsComponent implements OnInit {
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
