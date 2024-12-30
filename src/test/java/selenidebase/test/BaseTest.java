package selenidebase.test;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import selenidebase.config.ConfigReader;
import selenidebase.hook.DriverManager;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeMethod
    @Parameters("url")
    public void setup(String urlKey) {

        Configuration.browser = ConfigReader.getBrowser();

        Configuration.timeout = ConfigReader.getImplicitWait();

        open(ConfigReader.getUrl(urlKey));
        WebDriverRunner.getWebDriver().manage().window().maximize();

    }


}