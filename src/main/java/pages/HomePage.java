package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class HomePage {


    @FindBy(xpath =".//div[@id='top-links']//a[@title='My Account']")
    private WebElement myAccount;

    @FindBy(xpath =".//ul[@class='dropdown-menu dropdown-menu-right']/li[1]/a")
     private WebElement register;

    @FindBy(xpath =".//*[@id='column-right']/div/a[1]")
    private WebElement reg;

    @FindBy(xpath =".//ul[@class='dropdown-menu dropdown-menu-right']/li[2]/a")
    private WebElement login;

    @FindBy(xpath =".//*[@id='content']/div[2]/div[1]/div/div[2]/h4/a")
    private WebElement selectProduct;

    public WebDriver webDriver;

    public HomePage(WebDriver driver) {
        this.webDriver = driver;
    }

        public RegisterPage registerPageMet() {
            myAccount.click();
            WebDriverWait wait =  new WebDriverWait(webDriver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(register));
            register.click();

            RegisterPage registerPage = PageFactory.initElements(webDriver, RegisterPage.class);
            return registerPage;
        }

    public LoginPage loginPage() {
        myAccount.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(login));
        login.click();

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        return loginPage;
    }

        public ProductPage selectProduct(){
        selectProduct.click();
        ProductPage macBook= PageFactory.initElements(webDriver, ProductPage.class);
        return macBook;
    }

}
