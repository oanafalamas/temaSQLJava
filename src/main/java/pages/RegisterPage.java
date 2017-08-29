package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegisterPage {

        @FindBy(id ="input-firstname")
        private WebElement firstName;

        @FindBy(id ="input-lastname")
         private WebElement lastName;

         @FindBy(id ="input-email")
         private WebElement email;

        @FindBy(id ="input-telephone")
        private WebElement phone;

        @FindBy(id ="input-password")
        private WebElement password;

         @FindBy(id ="input-confirm")
         private WebElement confirmPassword;

        @FindBy(xpath=".//div[@id='content']//div[@class='form-group']//label[@class='radio-inline']/input")
        private List<WebElement> subscribeOption;

         @FindBy(xpath=".//*[@id='content']/form/div/div/input[1]")
         private WebElement policy;

        @FindBy(xpath=".//*[@id='content']/form/div/div/input[2]")
        private WebElement nextButton;

    @FindBy(xpath=".//div[@class='list-group']/a[2]")
    private WebElement editAccount;

        public WebDriver webDriver;

        public RegisterPage(WebDriver driver) {
            this.webDriver = driver;
        }

        public void firstNameMet(String param){
            firstName.clear();
            firstName.sendKeys(param);
        }

        public void lastNameMet(String param) {
            lastName.clear();
            lastName.sendKeys(param);
        }

    public void emailMet(String param) {
        email.clear();
        email.sendKeys(param);
    }

    public void phoneMet(String param){
            phone.clear();
            phone.sendKeys(param);
    }

    public void passwordMet(String param) {
        password.clear();
        password.sendKeys(param);
    }

    public void confirmPasswordMet(String param){
        confirmPassword.clear();
        confirmPassword.sendKeys(param);
    }


    public void subscribeMet(String param){
        if(param.toLowerCase().equals("yes"));
        subscribeOption.get(0).click();
        if (param.toLowerCase().equals("no"));
        subscribeOption.get(1).click();
    }

    public void policyMet(){
        policy.click();
    }

    public void nextButtonMet(){
        nextButton.click();
    }

    public MyAccountPage myAccountPageMet() {
        editAccount.click();
        MyAccountPage myAccountPage = PageFactory.initElements(webDriver, MyAccountPage.class);
        return myAccountPage;
    }

}
