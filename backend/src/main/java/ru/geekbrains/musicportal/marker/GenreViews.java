package ru.geekbrains.musicportal.marker;

public interface GenreViews {

    interface List extends PlaylistViews.List, AlbumViews.List, ArtistViews.List, TrackViews.List {}
    interface Single extends AlbumViews.All, ArtistViews.Single, TrackViews.Single, PlaylistViews.Single {}
    interface All extends List, Single {}
}
