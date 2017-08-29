package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver webDriver;

    //Constructor
    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

   @FindBy(id="input-quantity")
    private WebElement quantity;

    @FindBy(id="button-cart")
    private WebElement cartButton;

    @FindBy(xpath=".//div[@id='top-links']//a[@title='Shopping Cart']/span")
    private WebElement shoppingCart;

    @FindBy(xpath=".//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input")
    private WebElement quantityCart;

    @FindBy(xpath =".//div[@id='top-links']//a[@title='My Account']")
    private WebElement myAccount;

    @FindBy(xpath =".//*[@id='top-links']/ul/li[2]/ul/li[5]/a")
    private WebElement logOut;

    public void quantityMet(String param){
        quantity.clear();
        quantity.sendKeys(param);
    }

    public  void cartButtonMet(){
        cartButton.click();
    }

    public void shoppingCart(){
        shoppingCart.click();
    }

    public void quantityCart(){
        quantityCart.click();
    }

    public String getQuantityCart() {
        return quantityCart.getAttribute("value");
    }

    public ProductPage logOut() {
        myAccount.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(logOut));
        logOut.click();
        ProductPage productPage = PageFactory.initElements(webDriver,ProductPage.class);
        return productPage;
    }

}
