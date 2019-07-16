import { Injectable } from '@angular/core';
import {Track} from "../../model/track/track";

@Injectable({
  providedIn: 'root'
})
export class UploadTrackServiceService {


  uploadTrackUrl: string = "http://localhost:8080/miraculous/api/upload-music/upload-track";
  constructor() { }

  sendTrackToServer(trackInfo: any, trackCoverImage: any, tracks: any[]){

    let formData = new FormData();
    formData.append("trackInfo", trackInfo);
    formData.append("trackCoverImage", trackCoverImage);
    tracks.forEach(file => formData.append("tracks", file));

  }

}
