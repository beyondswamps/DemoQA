package ru.nwork.demoqa.api.util;

import com.github.javafaker.Faker;
import ru.nwork.demoqa.api.models.UserForRegister;
import ru.nwork.demoqa.api.models.UserFull;

public class UsersHelper {
    public static Faker userFaker = new Faker();

    public static final UserFull registeredUser = UserFull.builder()
            .userId("c57996c0-8a77-4562-8d3f-6b9adeab78ec")
            .userName("jeaniewiegand")
            .password("")
            .build();

    public static UserForRegister createUserReg() {
        return new UserForRegister(
                userFaker.name().username().toLowerCase().replace(".", ""),
                userFaker.internet().password(8,9,true, true, true) + "P@5s"
        );
    }
}
