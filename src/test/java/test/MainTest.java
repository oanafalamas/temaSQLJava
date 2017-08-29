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
        String firstName = readFromProperties("firstName", "register.properties");
        String lastName = readFromProperties("lastName", "register.properties");
        String email = generateRandomString(firstName) + "@gmail.com";
        String phone = readFromProperties("phone", "register.properties");
        String password = readFromProperties("password", "register.properties");
        String confirmPassword = readFromProperties("confirmPassword", "register.properties");
        String subscribe = readFromProperties("subscribe", "register.properties");
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
        String email = readFromProperties("email", "login.properties");
        String password = readFromProperties("password", "login.properties");
        loginPage.loginMet(email, password);
        loginPage.loginButtonMet();

        assertTrue(loginPage.logoutButton.isDisplayed());

        loginPage.logoutMet();
    }

    @Test
    public void databaseTest() throws SQLException {
        String sqlFirstName = readFromProperties("FirstName", "database.properties");
        String sqlLastName = readFromProperties("LastName", "database.properties");
        String sqlCompany = readFromProperties("Company", "database.properties");
        String sqlAdress_1 = readFromProperties("Adress_1", "database.properties");
        String sqlAdress_2 = readFromProperties("Adress_2", "database.properties");
        String sqlCity = readFromProperties("City", "database.properties");
        String sqlPostcode = readFromProperties("Postcode", "database.properties");
        String sqlCountryID = readFromProperties("Country_id", "database.properties");
        String sqlZoneID = readFromProperties("Zone_id", "database.properties");
        String sqlCustom_field = readFromProperties("Custom_field", "database.properties");
        // String sqlDummy = readFromProperties("Dummy","database.properties");

        String loginEmail = readFromProperties("email", "login.properties");
        String sqlEmail = readFromProperties("email", "sqllogin.properties");
        String loginPassword = readFromProperties("password", "login.properties");
//Connect to database
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();

//Add a new entry for your account in the oc_address ( dummy address)
        statement.executeUpdate("insert into oc_address (customer_id, firstname, lastname, company, address_1, address_2, city, postcode, country_id, zone_id, custom_field) values ((select customer_id from oc_customer where email=\"" + sqlEmail + "\"), \"" + sqlFirstName + "\", \"" + sqlLastName + "\", \"" + sqlCompany + "\", \"" + sqlAdress_1 + "\", \"" + sqlAdress_2 + "\", \"" + sqlCity + "\", \"" + sqlPostcode + "\", \"" + sqlCountryID + "\", \"" + sqlZoneID + "\",\"" + sqlCustom_field + "\");");
        statement.close();
        connection.close();

        LoginPage loginPage = homePage.loginPage();
        loginPage.loginMet(loginEmail, loginPassword);
        loginPage.loginButtonMet();

        AddressBook addressBook = loginPage.AddressPage();
        assertThat(sqlAdress_1, is(equalTo(addressBook.getAddressBook()[2])));
        assertThat(sqlAdress_2, is(equalTo(addressBook.getAddressBook()[3])));

    }

    @Test
    public void product() throws SQLException {
        String loginEmail = readFromProperties("email", "login.properties");
        String loginPassword = readFromProperties("password", "login.properties");
        LoginPage loginPage = homePage.loginPage();
        loginPage.loginMet(loginEmail, loginPassword);
        loginPage.loginButtonMet();
        loginPage.goToHome();

        ProductPage productPage = homePage.selectProduct();
        productPage.quantityMet("3");
        productPage.cartButtonMet();

        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update oc_cart set quantity=quantity+1 where customer_id=(select customer_id from oc_customer where email=\"Oana253@gmail.com\"); ");
        stmt.close();
        connection.close();

        productPage.shoppingCart();
        productPage.quantityCart();

        assertThat("4", is(equalTo(productPage.getQuantityCart())));






    }
}

