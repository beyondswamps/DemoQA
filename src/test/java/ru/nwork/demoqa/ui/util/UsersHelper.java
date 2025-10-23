package ru.nwork.demoqa.ui.util;

import com.github.javafaker.Faker;
import ru.nwork.demoqa.ui.data.User;

public class UsersHelper {
    public static Faker userFaker = new Faker();

    public static final User registeredUser = new User("Gil", "Schaden", "drewmcclure", "F4n!s2IH9==");

    public static User createUser() {
        return new User(
                userFaker.name().firstName(),
                userFaker.name().lastName(),
                userFaker.name().username().toLowerCase().replace(".", ""),
                userFaker.internet().password(8,9,true, true, true) + "P@5s"
        );
    }
}
