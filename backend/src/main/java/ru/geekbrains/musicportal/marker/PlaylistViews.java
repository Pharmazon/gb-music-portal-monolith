package ru.geekbrains.musicportal.marker;

public interface PlaylistViews {

    interface List extends ArtistViews.List {}
    interface Single extends List, ArtistViews.Single {}
    interface All extends List, Single {}
}
