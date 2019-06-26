import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'similar-tracks',
  templateUrl: './similar-tracks.component.html',
  styleUrls: ['./similar-tracks.component.css']
})
export class SimilarTracksComponent implements OnInit {

  items: Array<any> = [];
  constructor() {
    this.items = [

      {name: "assets/images/track-cover.jpg"},
      {name: "assets/images/track-cover.jpg"},
      {name: "assets/images/track-cover.jpg"},
      {name: "assets/images/track-cover.jpg"},
      {name: "assets/images/track-cover.jpg"},
      {name: "assets/images/track-cover.jpg"},

    ]
  }

  ngOnInit() {
  }

}
