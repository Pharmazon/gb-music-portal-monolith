import { Component, OnInit } from '@angular/core';
// import {StatisticsServiceService} from "../../../services/statistics-service/statistics-service.service";

@Component({
  selector: 'statistics-component',
  templateUrl: './statistics-component.component.html',
  styleUrls: ['./statistics-component.component.css']
})
export class StatisticsComponentComponent implements OnInit {

  numberOfUsersRegistered: number = 0;
  numberOfArtistsRegistered: number = 0;
  numberOfTUploadedTracks: number = 0;
  numberOfUploadedAlbums: number = 0;

  // constructor(private statisticsService: StatisticsServiceService) { }

  ngOnInit() {
  }

}
