package ru.geekbrains.musicportal.dto.marker;

public interface BandViews {

    interface List extends CommonViews.General{}
    interface Single extends List, CommonViews.All {}
}
