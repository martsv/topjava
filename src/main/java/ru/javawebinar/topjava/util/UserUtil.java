package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mart on 12.06.16.
 */
public class UserUtil {
    public static final List<User> USER_LIST = Arrays.asList(
            new User(1, "admin", "admin@mail.ru", "admin", Role.ROLE_ADMIN),
            new User(2, "user", "user@mail.ru", "user", Role.ROLE_USER)
    );
}
