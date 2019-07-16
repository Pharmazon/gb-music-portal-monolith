import { Component, OnInit } from '@angular/core';
import {PlayerServiceService} from "../../../../../services/player-service/player-service.service";

@Component({
  selector: 'track-popup',
  templateUrl: './track-popup.component.html',
  styleUrls: ['./track-popup.component.css']
})
export class TrackPopupComponent implements OnInit {

  constructor(private playerService: PlayerServiceService) { }

  ngOnInit() {
  }

}
