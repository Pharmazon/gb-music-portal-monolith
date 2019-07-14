package ru.geekbrains.musicportal.marker;

public interface TrackViews {

    interface List extends PlaylistViews.List {}
    interface Single extends List, PlaylistViews.Single {}
    interface All extends List, Single {}
}
