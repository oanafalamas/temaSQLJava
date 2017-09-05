package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Class.forName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class MainTest extends BaseTest {

    @Test
    public void registerTest() {

        RegisterPage registerPage = homePage.registerPageMet();
        String firstName = readFromProperties("firstName", "src/main/test/register.properties");
        String lastName = readFromProperties("lastName", "src/main/test/register.properties");
        String email = generateRandomString(firstName) + "@gmail.com";
        String phone = readFromProperties("phone", "src/main/test/register.properties");
        String password = readFromProperties("password", "src/main/test/register.properties");
        String confirmPassword = readFromProperties("confirmPassword", "src/main/test/register.properties");
        String subscribe = readFromProperties("subscribe", "src/main/test/register.properties");
        registerPage.firstNameMet(firstName);
        registerPage.lastNameMet(lastName);
        registerPage.emailMet(email);
        registerPage.phoneMet(phone);
        registerPage.passwordMet(password);
        registerPage.confirmPasswordMet(confirmPassword);
        registerPage.policyMet();
        registerPage.nextButtonMet();


        MyAccountPage myAccountPage = registerPage.myAccountPageMet();
        assertThat(firstName, is(equalTo(myAccountPage.getFirstName())));
        assertThat(lastName, is(equalTo(myAccountPage.getLastName())));
        assertThat(email, is(equalTo(myAccountPage.getEmail())));
        assertThat(phone, is(equalTo(myAccountPage.getPhone())));

        myAccountPage.signOutMet();
        myAccountPage.continueButtonMet();
    }

    @Test
    public void loginTest() {

        LoginPage loginPage = homePage.loginPage();
        String email = readFromProperties("email", "src/main/test/login.properties");
        String password = readFromProperties("password", "src/main/test/login.properties");
        loginPage.loginMet(email, password);
        loginPage.loginButtonMet();

        assertTrue(loginPage.logoutButton.isDisplayed());

        loginPage.logoutMet();
    }

    @Test
    public void databaseTest() throws SQLException {
        String sqlAdress_1 = readFromProperties("Adress_1", "src/test/resources/database.properties");
        String sqlAdress_2 = readFromProperties("Adress_2", "src/test/resources/database.properties");

        String loginEmail = readFromProperties("email", "src/test/resources/login.properties");
        String sqlEmail = readFromProperties("email", "src/test/resources/sqllogin.properties");
        String loginPassword = readFromProperties("password", "src/test/resources/login.properties");
//Connect to database

        Database database = new Database();
        Connection connection = database.connectToDatabase();
        Statement stmt = connection.createStatement();
        database.update(stmt);

//Add a new entry for your account in the oc_address ( dummy address)

        LoginPage loginPage = homePage.loginPage();
        loginPage.loginMet(loginEmail, loginPassword);
        loginPage.loginButtonMet();

        AddressBook addressBook = loginPage.AddressPage();
        assertThat(sqlAdress_1, is(equalTo(addressBook.getAddressBook()[2])));
        assertThat(sqlAdress_2, is(equalTo(addressBook.getAddressBook()[3])));
    }

    @Test
    public void product() throws SQLException {
        String loginEmail = readFromProperties("email", "src/test/resources/login.properties");
        String loginPassword = readFromProperties("password", "src/test/resources/login.properties");
        LoginPage loginPage = homePage.loginPage();
        loginPage.loginMet(loginEmail, loginPassword);
        loginPage.loginButtonMet();
        loginPage.goToHome();

        ProductPage productPage = homePage.selectProduct();
        productPage.quantityMet("3");
        productPage.cartButtonMet();

        Database database = new Database();
        Connection connection = database.connectToDatabase();
        Statement stmt = connection.createStatement();
        database.updateQuantity(stmt);


        productPage.shoppingCart();
        productPage.quantityCart();

        assertThat("4", is(equalTo(productPage.getQuantityCart())));
    }
}

