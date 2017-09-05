package test;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.mobile.NetworkConnection;
import pages.HomePage;

import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    static WebDriver webDriver;
    protected static HomePage homePage;

    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver_win32/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Before
    public void goToPage(){
        webDriver.get("http://192.168.164.15/");
        homePage = PageFactory.initElements(webDriver,HomePage.class);
    }

    @AfterClass
    public static void closeBrowser () {
        webDriver.close();
    }

    static String readFromProperties(String key, String fileName){
        try{
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            String value = properties.getProperty(key);
            return value;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateRandomString(String baseString){
        String finalString="";
        Random randomGenerator = new Random();
        int i= randomGenerator.nextInt(1000);
        finalString = baseString + i;
        i++;
        return finalString;
    }


}
