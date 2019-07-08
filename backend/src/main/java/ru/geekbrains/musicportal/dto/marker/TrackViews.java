package ru.geekbrains.musicportal.dto.marker;

public interface TrackViews {

    interface List extends PlaylistViews.List {}
    interface Single extends List, PlaylistViews.Single {}
    interface All extends List, Single {}
}
