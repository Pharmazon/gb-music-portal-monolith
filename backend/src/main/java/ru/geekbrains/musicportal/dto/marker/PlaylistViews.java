package ru.geekbrains.musicportal.dto.marker;

public interface PlaylistViews {

    interface List extends BandViews.List {}
    interface Single extends List, BandViews.Single {}
    interface All extends List, Single {}
}
