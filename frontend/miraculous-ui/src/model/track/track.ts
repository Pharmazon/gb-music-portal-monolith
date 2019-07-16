export class Track {

  title: string;
  artistId: string;
  coverImageId: string;
  url: string;
  size: string;
  duration: string;


  constructor(title?: string, artistId?: string, coverImageId?: string, url?: string, size?: string, duration?: string) {
    this.title = title;
    this.artistId = artistId;
    this.coverImageId = coverImageId;
    this.url = url;
    this.size = size;
    this.duration = duration;
  }
}
