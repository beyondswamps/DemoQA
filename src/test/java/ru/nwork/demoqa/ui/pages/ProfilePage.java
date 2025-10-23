package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.codeborne.selenide.Selenide.$;


@NoArgsConstructor
@Getter
public class ProfilePage {
    private final SelenideElement usernameLogged = $("#userName-value");
    private final SelenideElement logoutButton = $("button#submit");
}
