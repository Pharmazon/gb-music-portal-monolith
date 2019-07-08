export class Track {

  title: string;
  artist: string;
  coverUrl: string;
  url: string;


  constructor(title: string, artist: string, coverUrl: string, url: string) {
    this.title = title;
    this.artist = artist;
    this.coverUrl = coverUrl;
    this.url = url;
  }
}
