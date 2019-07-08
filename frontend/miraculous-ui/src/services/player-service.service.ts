import { Injectable } from '@angular/core';
import {Track} from "../model/track";

@Injectable({
  providedIn: 'root'
})
export class PlayerServiceService {

  tracks: Track [] = [
    new Track('Abnormal Bunx', 'Prodigy', 'assets/covers/prodigy.jpg','assets/music/AbnormalBunx.mp3'),
    new Track('Break And Enter', 'Prodigy','assets/covers/prodigy.jpg',
      'assets/music/BreakAndEnter.mp3'),
    new Track('Breathe', 'Prodigy','assets/covers/prodigy.jpg','assets/music/Breathe.mp3'),
    new Track("Colours","Prodigy",'assets/covers/prodigy.jpg',"assets/music/DieselPower.mp3"),
    new Track("Diesel Power", "Prodigy",'assets/covers/prodigy.jpg', "assets/music/DieselPower.mp3"),
    new Track("Firestarter", "Prodigy", 'assets/covers/prodigy.jpg',"assets/music/Firestarter.mp3"),
    new Track("Jericho", "Prodigy",'assets/covers/prodigy.jpg', "assets/music/Jericho.mp3"),
    new Track("Narayan","Prodigy",'assets/covers/prodigy.jpg',"assets/music/Narayan.mp3"),
    new Track("Omen", "Prodigy",'assets/covers/prodigy.jpg',"assets/music/Omen.mp3"),
    new Track("Spitfire", "Prodigy",'assets/covers/prodigy.jpg',"assets/music/Spitfire.mp3")];

  currentTrackIndex: number = 0;

  constructor() {
  }
}
