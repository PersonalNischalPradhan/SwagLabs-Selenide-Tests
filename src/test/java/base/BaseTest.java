package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.KeywordManager;
import utils.TestUtils;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.*;


public class BaseTest {
    private final String BASE_URL = TestUtils.getProperty("base.url");
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.headless = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1366,768");
        options.addArguments("--password-store=basic");
        options.setExperimentalOption("prefs",
                Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                )
        );
        Configuration.browserCapabilities = options;
        open(BASE_URL);
        Selenide.sleep(3000);
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}
