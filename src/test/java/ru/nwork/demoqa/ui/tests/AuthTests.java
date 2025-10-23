package ru.nwork.demoqa.ui.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nwork.demoqa.ui.data.User;
import ru.nwork.demoqa.ui.pages.LoginPage;
import ru.nwork.demoqa.ui.pages.ProfilePage;
import ru.nwork.demoqa.ui.pages.RegisterPage;
import ru.nwork.demoqa.ui.util.UsersHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static ru.nwork.demoqa.ui.pages.RegisterPage.openRegisterPage;

public class AuthTests extends BaseTest{


    @Test
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
    public void loginSuccessfulTest() {
        User user = UsersHelper.registeredUser;

        ProfilePage profilePage = LoginPage
                .openLoginPage()
                .loginRegisteredUser(user);

        Assertions.assertEquals(user.username(), profilePage.getUsernameLogged());
        Assertions.assertEquals("Log out", profilePage.getLogoutButtonText());
    }

    @Test
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

