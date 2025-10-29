package ru.nwork.demoqa.api.util;

import com.github.javafaker.Faker;
import ru.nwork.demoqa.api.models.UserForRegister;

public class UsersHelper {
    public static Faker userFaker = new Faker();

//    public static final User registeredUser = new User("Gil", "Schaden", "drewmcclure", "F4n!s2IH9==");

    public static UserForRegister createUserReg() {
        return new UserForRegister(
                userFaker.name().username().toLowerCase().replace(".", ""),
                userFaker.internet().password(8,9,true, true, true) + "P@5s"
        );
    }
}
