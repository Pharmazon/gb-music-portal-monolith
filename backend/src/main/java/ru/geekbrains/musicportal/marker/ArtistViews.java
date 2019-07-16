package ru.geekbrains.musicportal.marker;

public interface ArtistViews {

    interface List extends CommonViews.General {}
    interface Single extends List, CommonViews.All {}
    interface All extends Single, List {}
}
