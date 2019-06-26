import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {Route, RouterModule, Routes} from "@angular/router";
import { AppComponent } from './app.component';
import { AlbumsComponent } from './albums/albums.component';
import {DiscoverComponent} from "./discover/discover.component";
import { ArtistsComponent } from './artists/artists.component';
import { GenresComponent } from './genres/genres.component';
import { TopTracksComponent } from './common-components/top-tracks/top-tracks.component';
import { FreeMusicComponent } from './free-music/free-music.component';
import { BannerComponent } from './discover/banner/banner.component';
import { FeaturedAlbumsComponent } from './common-components/featured-albums/featured-albums.component';
import { SearchComponent } from './search/search.component';
import { DownloadAppLinkComponent } from './discover/download-app-link/download-app-link.component';
import { LoginComponent } from './login/login.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ArtistProfileComponent } from './artist-profile/artist-profile.component';
import { FeaturedArtistsComponent } from './common-components/featured-artists/featured-artists.component';
import { NotFoundComponent } from './not-found/not-found.component'
import {Ng2CarouselamosModule} from "ng2-carouselamos";
import { AlbumPageComponent } from './album-page/album-page.component';
import { TrackPageComponent } from './track-page/track-page.component';
import {RadioComponent} from "./radio/radio.component";
import { SimilarAlbumsComponent } from './common-components/similar-albums/similar-albums.component';
import { SimilarTracksComponent } from './common-components/similar-tracks/similar-tracks.component';
import { AudiotrackComponent } from './audiotrack/audiotrack.component';
import { UploadMusicComponent } from './upload-music/upload-music.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { BasicComponentComponent } from './basic-component/basic-component.component';
import { PurchasedMusicComponent } from './purchased-music/purchased-music.component';
import { FavouritesComponent } from './favourites/favourites.component';
import { HistoryComponent } from './history/history.component';
import { CreatePlaylistComponent } from './create-playlist/create-playlist.component';
import {RegistrationComponent} from "./registration/registration.component";


const appRoutes: Routes = [
  {path:'miraculous', component:BasicComponentComponent, children: [
      {path:'discover', component: DiscoverComponent},
      {path:'search', component:SearchComponent},
      {path:'albums', component: AlbumsComponent},
      {path:'artists', component: ArtistsComponent},
      {path:'genres',component: GenresComponent},
      {path:'top-tracks', component: TopTracksComponent},
      {path:'free-music', component: FreeMusicComponent},
      {path:'stations', component: RadioComponent},
      {path:'downloads', component: AlbumPageComponent},
      {path:'tracks/:id', component: TrackPageComponent},
      {path:'albums/:id', component: AlbumPageComponent},
      {path:'purchased-music', component: PurchasedMusicComponent},
      {path:'favourites', component: FavouritesComponent},
      {path:'history', component: HistoryComponent},
      {path:'user/:id', component: UserProfileComponent},
      {path:'artist/:id',component: ArtistProfileComponent}

    ]},
  {path:'admin', component: AdminPageComponent},
  {path:'**', component: NotFoundComponent},

];

@NgModule({
  declarations: [
    AppComponent,
    DiscoverComponent,
    AlbumsComponent,
    ArtistsComponent,
    GenresComponent,
    TopTracksComponent,
    FreeMusicComponent,
    BannerComponent,
    FeaturedAlbumsComponent,
    SearchComponent,
    DownloadAppLinkComponent,
    LoginComponent,
    UserProfileComponent,
    ArtistProfileComponent,
    FeaturedArtistsComponent,
    NotFoundComponent,
    AlbumPageComponent,
    TrackPageComponent,
    SimilarAlbumsComponent,
    SimilarTracksComponent,
    RadioComponent,
    AudiotrackComponent,
    UploadMusicComponent,
    AdminPageComponent,
    BasicComponentComponent,
    PurchasedMusicComponent,
    FavouritesComponent,
    HistoryComponent,
    CreatePlaylistComponent,
    RegistrationComponent

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    Ng2CarouselamosModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
