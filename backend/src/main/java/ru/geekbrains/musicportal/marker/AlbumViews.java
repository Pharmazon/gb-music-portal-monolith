package ru.geekbrains.musicportal.marker;

public interface AlbumViews {

    interface List extends ArtistViews.List, CommonViews.General {}
    interface Single extends List, ArtistViews.Single, CommonViews.All {}
    interface All extends List, Single {}
}
