package ru.geekbrains.musicportal.marker;

public interface PlaylistViews {

    interface List extends UserViews.List {}
    interface Single extends List, UserViews.Single {}
    interface All extends List, Single {}
}
