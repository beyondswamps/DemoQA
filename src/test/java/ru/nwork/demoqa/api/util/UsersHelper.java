package ru.nwork.demoqa.api.util;

import com.github.javafaker.Faker;
import ru.nwork.demoqa.api.models.UserCreds;
import ru.nwork.demoqa.api.models.UserFull;

public class UsersHelper {
    public static Faker userFaker = new Faker();

    public static final UserFull registeredUser = UserFull.builder()
            .userId("c57996c0-8a77-4562-8d3f-6b9adeab78ec")
            .userName("jeaniewiegand")
            .password("")
            .build();

    public static UserCreds createUserReg() {
        return new UserCreds(
                userFaker.name().username().toLowerCase().replace(".", ""),
                "123456Qq!"
        );
    }
}
