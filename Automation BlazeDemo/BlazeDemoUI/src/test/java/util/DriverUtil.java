package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DriverUtil {

    private WebDriver driver;

    public DriverUtil(WebDriver driver){
        this.driver = driver;
    }

    public void navigatePage(String url) {
        driver.get(url);
    }


}
