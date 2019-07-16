import { Injectable } from '@angular/core';
import {Album} from "../../model/album/album";
import {Track} from "../../model/track/track";

@Injectable({
  providedIn: 'root'
})
export class CartServiceService {

  listOfAlbums: Album[];
  listOfTracks: Track[];

  constructor() { }
}
