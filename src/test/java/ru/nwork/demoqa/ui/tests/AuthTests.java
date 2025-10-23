package ru.nwork.demoqa.ui.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nwork.demoqa.ui.data.User;
import ru.nwork.demoqa.ui.pages.LoginPage;
import ru.nwork.demoqa.ui.pages.ProfilePage;
import ru.nwork.demoqa.ui.util.UsersHelper;

import static com.codeborne.selenide.Condition.clickable;
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
                .clickRecaptcha()
                .clickRegister();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String alertText = switchTo().alert().getText();
        switchTo().alert().accept();

        Assertions.assertEquals("User Register Successfully.", alertText);
    }

    @Test
    public void loginSuccessfulTest() {
        User user = UsersHelper.registeredUser;

        ProfilePage profilePage = LoginPage
                .openLoginPage()
                .loginUsernamePassword(user.username(), user.password());
        String loggedUsername = profilePage.getUsernameLogged().text();
        String logoutButtonText = profilePage.getLogoutButton().text();

        Assertions.assertEquals(user.username(), loggedUsername);
        Assertions.assertEquals("Log out", logoutButtonText);
    }

    @Test
    public void logoutSuccessfulTest() {
        User user = UsersHelper.registeredUser;
        LoginPage.openLoginPage()
                .login(user)
                .getLogoutButton().shouldBe(clickable)
                .click();
        LoginPage loginPage = new LoginPage();

        Assertions.assertEquals("Login", loginPage.submitButton.text());
        Assertions.assertEquals("Login", $("h1.text-center").text());
        Assertions.assertEquals("Login in Book Store", $("form h5").text());

    }
}

