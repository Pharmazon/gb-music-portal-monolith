import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Track} from "../../../../../model/track";
import {PlayerServiceService} from "../../../../../services/player-service.service";

@Component({
  selector: 'app-tracks-queue-item',
  templateUrl: './tracks-queue-item.component.html',
  styleUrls: ['./tracks-queue-item.component.css']
})
export class TracksQueueItemComponent implements OnInit {

  @Input() track: Track;

  @Output() onClickedDeleteTracksQueueItem = new EventEmitter<any>();
  @Output() onPlayTrack = new EventEmitter<any>();
  constructor(private playerService: PlayerServiceService) { }

  ngOnInit() {
  }

  deleteTracksQueueItem() {
    this.onClickedDeleteTracksQueueItem.emit();
  }

  setCurrentTrackAndPlay() {
    this.playerService.currentTrackIndex = this.playerService.tracks.indexOf(this.track);
    this.onPlayTrack.emit();
    console.log(this.playerService.currentTrackIndex);
  }
}
