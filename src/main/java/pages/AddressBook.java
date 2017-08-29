package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressBook{

    @FindBy(xpath=".//div[@id='content']//tbody//td[@class='text-left']")
    private List<WebElement> addressBook;

    private WebDriver webDriver;

    public String[] getAddressBook() {
        int size = addressBook.size();
        return addressBook.get(size-1).getText().split("\\r?\\n");
    }

    public AddressBook(WebDriver webDriver){
        this.webDriver=webDriver;
    }

}
