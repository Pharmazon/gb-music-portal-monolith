package ru.geekbrains.musicportal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    USER("USER"),
    ARTIST("ARTIST"),
    ADMINISTRATOR("ADMIN"),
    SUPER_ADMINISTRATOR("SUPERADMIN");

    private static List<UserRoleEnum> listUserArtist = new ArrayList<>();
    private static List<UserRoleEnum> listUser = new ArrayList<>();
    private static List<UserRoleEnum> listUserAdmin = new ArrayList<>();
    private static List<UserRoleEnum> listUserSuperAdmin = new ArrayList<>();

    static {
        listUser.add(USER);
        listUserArtist.add(USER);
        listUserArtist.add(ARTIST);
        listUserAdmin.add(USER);
        listUserAdmin.add(ADMINISTRATOR);
        listUserSuperAdmin.add(USER);
        listUserSuperAdmin.add(SUPER_ADMINISTRATOR);
    }

    private String name;

    public static Collection<UserRoleEnum> getArtist() {
        return listUserArtist;
    }

    public static Collection<UserRoleEnum> getAdmin() {
        return listUserAdmin;
    }

    public static Collection<UserRoleEnum> getUser() {
        return listUser;
    }

    public static Collection<UserRoleEnum> getSuperAdmin() {
        return listUserSuperAdmin;
    }
}
