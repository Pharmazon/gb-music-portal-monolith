package ru.geekbrains.musicportal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.musicportal.entity.user.Role;

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

    private static List<Role> listUserArtist = new ArrayList<>();
    private static List<Role> listUser = new ArrayList<>();
    private static List<Role> listUserAdmin = new ArrayList<>();
    private static List<Role> listUserSuperAdmin = new ArrayList<>();

    static {
        Role roleUser = new Role(USER);
        Role roleArtist = new Role(ARTIST);
        Role roleAdmin = new Role(ADMINISTRATOR);
        Role roleSuperAdmin = new Role(SUPER_ADMINISTRATOR);

        listUser.add(roleUser);
        listUserArtist.add(roleUser);
        listUserArtist.add(roleArtist);
        listUserAdmin.add(roleUser);
        listUserAdmin.add(roleAdmin);
        listUserSuperAdmin.add(roleUser);
        listUserSuperAdmin.add(roleSuperAdmin);
    }

    private String name;

    public static Collection<Role> getArtist() {
        return listUserArtist;
    }

    public static Collection<Role> getAdmin() {
        return listUserAdmin;
    }

    public static Collection<Role> getUser() {
        return listUser;
    }

    public static Collection<Role> getSuperAdmin() {
        return listUserSuperAdmin;
    }
}
