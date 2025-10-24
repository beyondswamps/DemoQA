package ru.nwork.demoqa.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.nwork.demoqa.ui.data.User;
import ru.nwork.demoqa.ui.pages.LoginPage;
import ru.nwork.demoqa.ui.pages.ProfilePage;
import ru.nwork.demoqa.ui.pages.RegisterPage;
import ru.nwork.demoqa.ui.util.UsersHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static ru.nwork.demoqa.ui.pages.RegisterPage.openRegisterPage;

@Feature("demoqa.com")
@Story("Регистрация пользователя, логин, удаление аккаунта")
@Tags({@Tag("Auth"), @Tag("User")})
public class AuthTests extends BaseTest{

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void registerUserSuccessfulTest() {
        User user = UsersHelper.createUser();
        System.out.println(user);      //may be logged
        openRegisterPage()
                .fillFieldsWithUser(user)
                .passRecaptcha()
                .submitRegister();

        String alertText = switchTo().alert().getText();
        switchTo().alert().accept();

        Assertions.assertEquals("User Register Successfully.", alertText);
    }

    @Test
    @DisplayName("Логин пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void loginSuccessfulTest() {
        User user = UsersHelper.registeredUser;

        ProfilePage profilePage = LoginPage
                .openLoginPage()
                .loginRegisteredUser(user);

        Assertions.assertEquals(user.username(), profilePage.getUsernameLogged());
        Assertions.assertEquals("Log out", profilePage.getLogoutButtonText());
    }

    @Test
    @DisplayName("Логин и логаут зарегистрированного пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void logoutSuccessfulTest() {
        User user = UsersHelper.registeredUser;
        LoginPage loginPage = LoginPage.openLoginPage()
                .loginRegisteredUser(user)
                .logout();

        Assertions.assertEquals("Login", loginPage.submitButton.text());
        Assertions.assertEquals("Login", $("h1.text-center").text());
        Assertions.assertEquals("Login in Book Store", $("form h5").text());

    }

    @Test
    @DisplayName("Регистрация и удаление нового пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void registerLoginDeleteUserSuccessfulTest() {
        User user = UsersHelper.createUser();

        RegisterPage
                .openRegisterPage()
                .fillFieldsWithUser(user)
                .passRecaptcha()
                .submitRegister();

        String wrongCredsError = LoginPage
                .openLoginPage()
                .loginRegisteredUser(user)
                .deleteAccount()
                .loginUnregisteredUser(user);

        Assertions.assertEquals("Invalid username or password!", wrongCredsError);
    }
}

