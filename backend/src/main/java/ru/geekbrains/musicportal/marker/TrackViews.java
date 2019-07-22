package ru.geekbrains.musicportal.marker;

public interface TrackViews {

    interface List extends PlaylistViews.List, AlbumViews.List {}
    interface Single extends List, PlaylistViews.Single, AlbumViews.Single {}
    interface All extends List, Single {}
}
