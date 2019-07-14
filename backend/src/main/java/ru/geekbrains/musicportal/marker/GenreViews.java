package ru.geekbrains.musicportal.marker;

public interface GenreViews {

    interface List extends PlaylistViews.List {}
    interface Single extends TrackViews.List, PlaylistViews.Single {}
    interface All extends TrackViews.List, TrackViews.Single {}
}
