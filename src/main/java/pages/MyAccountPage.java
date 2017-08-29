package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyAccountPage {

    @FindBy(id ="input-firstname")
    private WebElement firstName;

    @FindBy(id ="input-lastname")
    private WebElement lastName;

    @FindBy(id ="input-email")
    private WebElement email;

    @FindBy(id ="input-telephone")
    private WebElement phone;

    @FindBy(xpath=".//*[@id='column-right']/div/a[13]")
    private WebElement signOut;

    @FindBy(xpath=".//*[@id='content']/div/div/a")
    private WebElement continueButton;


    public String getFirstName(){
        return firstName.getAttribute("value");
    }

    public String getLastName(){
        return lastName.getAttribute("value");

    }

    public String getEmail(){
        return email.getAttribute("value");

    }
    public String getPhone(){
        return phone.getAttribute("value");
    }

    public void signOutMet(){
        signOut.click();
    }

    public void continueButtonMet(){
        continueButton.click();
    }
}
