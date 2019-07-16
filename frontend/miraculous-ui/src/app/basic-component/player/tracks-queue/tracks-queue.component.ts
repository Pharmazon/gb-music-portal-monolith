import {Component, ComponentRef, EventEmitter, OnInit, Output} from '@angular/core';
import {PlayerServiceService} from "../../../../services/player-service/player-service.service";
import {Track} from "../../../../model/track/track";

@Component({
  selector: 'app-tracks-queue',
  templateUrl: './tracks-queue.component.html',
  styleUrls: ['./tracks-queue.component.css']
})
export class TracksQueueComponent implements OnInit {

  @Output() onClosedTracksQueuePanel = new EventEmitter<any>();

  @Output() onPlayTrack = new EventEmitter<any>();

  tracks: Track[] = this.playerService.tracks;

  constructor(private playerService: PlayerServiceService) {

  }

  ngOnInit() {

  }

  closeTracksQueuePanel() {
      this.onClosedTracksQueuePanel.emit();
  }

  deleteTrackQueueItem() {
    (<Element>event.target).parentElement.remove();
  }

  playTrack() {
    this.onPlayTrack.emit();
  }
}
