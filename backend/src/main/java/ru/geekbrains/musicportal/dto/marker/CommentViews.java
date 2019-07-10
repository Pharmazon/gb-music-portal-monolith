package ru.geekbrains.musicportal.dto.marker;

public interface CommentViews {

    interface List extends CommonViews.General {}
    interface Single extends List, CommonViews.All {}
    interface All extends Single, List {}
}
