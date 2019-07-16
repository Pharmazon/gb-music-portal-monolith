import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {PlayerServiceService} from "../../../services/player-service/player-service.service";


@Component({
  selector: 'player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  @Output() onToggledPlayerBlock = new EventEmitter<any>();

  @ViewChild('tracksQueue', {read: ElementRef})
  tracksQueue: ElementRef;

  @ViewChild("audio", {read: ElementRef})
  audio: ElementRef;

  isPlaying: boolean = false;
  trackVolume: number = 0;
  onRepeat: boolean = false;
  isPlayerHidden: boolean = false;

  constructor(private playerService: PlayerServiceService) { }

  ngOnInit() {
  }

  showTracksQueue() {
    this.tracksQueue.nativeElement.style = "display: flex";
  }

  hideTracksQueue() {
    this.tracksQueue.nativeElement.style = "display: none";
  }

  changeCurrentTrackToPrevious() {
    if (this.playerService.currentTrackIndex == 0){
      if (this.playerService.tracks.length != 0){
        if (this.isPlaying == true){
          this.playerService.currentTrackIndex = this.playerService.tracks.length - 1;
          (<HTMLAudioElement>this.audio.nativeElement).autoplay = true;

        }

      }
    }else {
      if (this.isPlaying == true){
        this.playerService.currentTrackIndex --;
        (<HTMLAudioElement>this.audio.nativeElement).autoplay = true;
      }


    }
  }

  changeCurrentTrackToNext() {
    if (this.playerService.tracks.length != 0){
      if (this.playerService.currentTrackIndex != this.playerService.tracks.length-1){
        if (this.isPlaying == true){
          this.playerService.currentTrackIndex ++;
          (<HTMLAudioElement>this.audio.nativeElement).autoplay = true;
        }
      } else {
        if (this.isPlaying == true){
          this.playerService.currentTrackIndex = 0;
          (<HTMLAudioElement>this.audio.nativeElement).autoplay = true;
        }


      }
    }
  }

  playTrack() {
    (<HTMLAudioElement>this.audio.nativeElement).autoplay = true;
    (<HTMLAudioElement>this.audio.nativeElement).play();
    this.isPlaying = true;


  }

  pauseTrack(){

    (<HTMLAudioElement>this.audio.nativeElement).pause();
    this.isPlaying = false;
    (<HTMLAudioElement>this.audio.nativeElement).autoplay = false;
  }

  startNextTrack() {

    if (this.onRepeat == true){
      this.playTrack();
    } else {
      if (this.playerService.tracks.length != this.playerService.currentTrackIndex -1){
        this.playerService.currentTrackIndex ++;
        (<HTMLAudioElement>this.audio.nativeElement).autoplay = true;
      }else {
        this.isPlaying = false;
      }
    }

  }

  updateProgressBar() {
    let filling = document.querySelector(".progress-fill");
    let trackHandle = document.querySelector(".track-handle");
    let position = (<HTMLAudioElement>this.audio.nativeElement).currentTime/(<HTMLAudioElement>this.audio.nativeElement).duration;
    filling.setAttribute("style", "width: +"+position*100+"%");
    trackHandle.setAttribute("style", "left: +"+position*100+"%");
    console.log(position);
  }

  toggleOnRepeat(){
    if (this.onRepeat == false){
      this.onRepeat = true;
    } else {
      this.onRepeat = false;
    }
  }

  hideOrShowPlayerOnClick(){
    if (this.isPlayerHidden == false){

    } else {

    }
  }

  togglePlayerBlock() {
    this.onToggledPlayerBlock.emit();
  }
}
