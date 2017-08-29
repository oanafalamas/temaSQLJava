package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPage {

    @FindBy(id ="input-email")
    private WebElement email;

    @FindBy(id ="input-password")
    private WebElement password;

    @FindBy(xpath=".//*[@id='content']/div/div[2]/div/form/input")
    private WebElement loginButton;

    @FindBy(xpath=".//*[@id='column-right']/div/a[13]")
    public WebElement logoutButton;

   @FindBy(xpath=".//*[@id='column-right']/div/a[4]")
    private WebElement addressBookButton;

    @FindBy(xpath = ".//div[@id='logo']/h1/a")
    private WebElement homeButton;

    public WebDriver webDriver;

    public LoginPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void loginMet(String param, String param2) {
        email.clear();
        email.sendKeys(param);
        password.clear();
        password.sendKeys(param2);
    }

   public void loginButtonMet(){
      loginButton.click();
    }

    public void logoutMet() {
        assertTrue(logoutButton.isDisplayed());
        logoutButton.click();
    }

   public AddressBook AddressPage(){
        addressBookButton.click();
        AddressBook addressBook= PageFactory.initElements(webDriver,AddressBook.class);
        return addressBook;
    }

    public HomePage goToHome(){
        homeButton.click();
        HomePage homePage = PageFactory.initElements(webDriver, HomePage.class);
        return homePage;
    }


}
