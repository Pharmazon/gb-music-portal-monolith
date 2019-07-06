import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'last-user-liked-albums',
  templateUrl: './last-user-liked-albums.component.html',
  styleUrls: ['./last-user-liked-albums.component.css']
})
export class LastUserLikedAlbumsComponent implements OnInit {

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
