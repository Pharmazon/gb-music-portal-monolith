package ru.geekbrains.musicportal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    USER("user"),
    ARTIST("artist"),
    ADMINISTRATOR("admin");

    private static List<UserRoleEnum> listUserArtist = new ArrayList<>();
    private static List<UserRoleEnum> listUser = new ArrayList<>();
    private static List<UserRoleEnum> listUserAdmin = new ArrayList<>();

    static {
        listUser.add(USER);
        listUserArtist.add(USER);
        listUserArtist.add(ARTIST);
        listUserAdmin.add(USER);
        listUserAdmin.add(ADMINISTRATOR);
    }

    private String name;

    public static Collection<UserRoleEnum> getUserArtist() {
        return listUserArtist;
    }

    public static Collection<UserRoleEnum> getUserAdministrator() {
        return listUserAdmin;
    }

    public static Collection<UserRoleEnum> getUser() {
        return listUser;
    }
}
