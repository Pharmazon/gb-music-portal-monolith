package ru.geekbrains.musicportal.dto.marker;

public interface LikeViews {

    interface List extends CommonViews.General {}
    interface Single extends List, CommonViews.All {}
    interface All extends Single, List {}
}
