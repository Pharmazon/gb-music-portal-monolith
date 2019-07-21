package ru.geekbrains.musicportal.marker;

public interface UserViews {

    interface List extends CommonViews.General {}
    interface Single extends List, CommonViews.All {}
    interface All extends Single {}
}
