package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import pages.BasketPage;
import pages.CommonPage;
import pages.HomePage;
import pages.SearchPage;
import utils.ConfigReader;
import utils.ScreenShotUtils;
import java.time.Duration;

import static builders.DriverBuilds.*;

public class CommonTest {


    private static final int DEFAULT_WAIT = 10;

    public static CommonPage commonPage;
    public static HomePage homePage;
    public static BasketPage basketPage;
    public static SearchPage searchPage;

    @Rule
    public ScreenShotUtils failure = new ScreenShotUtils();


    @BeforeClass
    public static void setup() {
        commonPage = new CommonPage();
        homePage = new HomePage();
        basketPage = new BasketPage();
        searchPage = new SearchPage();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_WAIT));
        getDriver().get(ConfigReader.getProperty("baseURL"));
    }

    @AfterClass
    public static void tearDown() {
       getDriver().quit();
    }
}
