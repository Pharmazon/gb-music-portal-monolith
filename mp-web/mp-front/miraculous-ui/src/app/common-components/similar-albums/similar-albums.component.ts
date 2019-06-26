import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-similar-albums',
  templateUrl: './similar-albums.component.html',
  styleUrls: ['./similar-albums.component.css']
})
export class SimilarAlbumsComponent implements OnInit {

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
