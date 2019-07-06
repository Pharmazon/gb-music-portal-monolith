import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'last-user-liked-tracks',
  templateUrl: './last-user-liked-tracks.component.html',
  styleUrls: ['./last-user-liked-tracks.component.css']
})
export class LastUserLikedTracksComponent implements OnInit {

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
