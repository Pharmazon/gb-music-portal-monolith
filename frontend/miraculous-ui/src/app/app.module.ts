import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {Route, RouterModule, Routes} from "@angular/router";
import { AppComponent } from './app.component';
import { AlbumsComponent } from './basic-component/albums/albums.component';
import {DiscoverComponent} from "./discover/discover.component";
import { ArtistsComponent } from './basic-component/artists/artists.component';
import { GenresComponent } from './basic-component/genres/genres.component';
import { TopTracksComponent } from './common-components/top-tracks/top-tracks.component';
import { FreeMusicComponent } from './basic-component/free-music/free-music.component';
import { BannerComponent } from './discover/banner/banner.component';
import { FeaturedAlbumsComponent } from './common-components/featured-albums/featured-albums.component';
import { SearchComponent } from './basic-component/search/search.component';
import { DownloadAppLinkComponent } from './discover/download-app-link/download-app-link.component';
import { LoginComponent } from './basic-component/login/login.component';
import { UserProfileComponent } from './basic-component/user-profile/user-profile.component';
import { ArtistProfileComponent } from './basic-component/artist-profile/artist-profile.component';
import { FeaturedArtistsComponent } from './common-components/featured-artists/featured-artists.component';
import { NotFoundComponent } from './not-found/not-found.component'
import {Ng2CarouselamosModule} from "ng2-carouselamos";
import { AlbumPageComponent } from './album-page/album-page.component';
import { TrackPageComponent } from './basic-component/track-page/track-page.component';
import {RadioComponent} from "./basic-component/radio/radio.component";
import { SimilarAlbumsComponent } from './common-components/similar-albums/similar-albums.component';
import { SimilarTracksComponent } from './common-components/similar-tracks/similar-tracks.component';
import { AudiotrackComponent } from './basic-component/upload-track/audiotrack/audiotrack.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { BasicComponentComponent } from './basic-component/basic-component.component';
import { PurchasedMusicComponent } from './basic-component/purchased-music/purchased-music.component';
import { FavouritesComponent } from './basic-component/favourites/favourites.component';
import { HistoryComponent } from './basic-component/history/history.component';
import { CreatePlaylistComponent } from './basic-component/create-playlist/create-playlist.component';
import {RegistrationComponent} from "./basic-component/registration/registration.component";
import { UploadAlbumComponent } from './basic-component/upload-album/upload-album.component';
import { UploadTrackComponent } from './basic-component/upload-track/upload-track.component';
import { InternalizationPanelComponent } from './basic-component/internalization-panel/internalization-panel.component';
import {TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { TagComponent } from './basic-component/upload-album/tag/tag.component';
import {CommonModule} from "@angular/common";
import { AudiotrackWithDetailsComponent } from './basic-component/upload-album/audiotrack-with-details/audiotrack-with-details.component';
import {FormsModule} from "@angular/forms";
import { FileInputDirective } from './basic-component/upload-album/file-input.directive';
import { LastUserLikedTracksComponent } from './basic-component/user-profile/last-user-liked-tracks/last-user-liked-tracks.component';
import { LastUserLikedAlbumsComponent } from './basic-component/user-profile/last-user-liked-albums/last-user-liked-albums.component';
import { EditUserProfileComponent } from './basic-component/edit-user-profile/edit-user-profile.component';
import { EditArtistProfileComponent } from './basic-component/edit-artist-profile/edit-artist-profile.component';
import { AccountDeletionConfirmationComponent } from './basic-component/edit-user-profile/account-deletion-confirmation/account-deletion-confirmation.component';
import { ArtistEventComponent } from './basic-component/artist-profile/artist-event/artist-event.component';
import { ArtistTrackComponent } from './basic-component/artist-profile/artist-track/artist-track.component';
import { ArtistAlbumComponent } from './basic-component/artist-profile/artist-album/artist-album.component';
import { ArtistCommentComponent } from './basic-component/artist-profile/artist-comment/artist-comment.component';
import { PlayerComponent } from './basic-component/player/player.component';
import { TracksQueueComponent } from './basic-component/player/tracks-queue/tracks-queue.component';
import { TracksQueueItemComponent } from './basic-component/player/tracks-queue/tracks-queue-item/tracks-queue-item.component';


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
      {path:'artist/:id',component: ArtistProfileComponent},
      {path:'upload-album', component: UploadAlbumComponent},
      {path:'upload-track', component: UploadTrackComponent},
      {path:'user-profile/:id', component: UserProfileComponent},
      {path:'artist-profile/:id', component: ArtistProfileComponent},
      {path:'edit-user-profile/:id',component: EditUserProfileComponent},
      {path:'edit-artist-profile/:id', component: EditArtistProfileComponent}
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
    AdminPageComponent,
    BasicComponentComponent,
    PurchasedMusicComponent,
    FavouritesComponent,
    HistoryComponent,
    CreatePlaylistComponent,
    RegistrationComponent,
    UploadAlbumComponent,
    UploadTrackComponent,
    InternalizationPanelComponent,
    TagComponent,
    AudiotrackWithDetailsComponent,
    FileInputDirective,
    LastUserLikedTracksComponent,
    LastUserLikedAlbumsComponent,
    EditUserProfileComponent,
    EditArtistProfileComponent,
    AccountDeletionConfirmationComponent,
    ArtistEventComponent,
    ArtistTrackComponent,
    ArtistAlbumComponent,
    ArtistCommentComponent,
    PlayerComponent,
    TracksQueueComponent,
    TracksQueueItemComponent,

  ],
  entryComponents: [TagComponent, AudiotrackComponent, AudiotrackWithDetailsComponent],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    Ng2CarouselamosModule,
    TranslateModule.forRoot(),
    CommonModule,
    FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
