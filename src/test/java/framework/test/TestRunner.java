package framework.test;

import framework.library.GuestFunctions;
import framework.library.locators;
import framework.tools.LocalStorageJS;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@ExtendWith(RunnerExtension.class)

public abstract class TestRunner {

    private static final String BASE_URL = "https://www.greencity.social/#/greenCity";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long IMPLICITLY_WAIT_ONE_SECONDS = 1L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    protected static boolean isTestSuccessful = false;
    protected static WebDriver driver;
    protected static GuestFunctions guestFunctions;
    protected static locators myLocators;
    protected static LocalStorageJS localStorageJS;


    private static void takeScreenShot() {
        //String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        //
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    private void takePageSource() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }


    protected static void closePopup() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_ONE_SECONDS)); // 0 by default
        List<WebElement> loginFormCloseButton = driver.findElements(By.cssSelector("img[alt='close button']"));
        System.out.println("loginFormCloseButton.size() = " + loginFormCloseButton.size());
        if (loginFormCloseButton.size() > 0) {
            WebElement closeButton = loginFormCloseButton.get(0);
            loginFormCloseButton.get(0).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // 0 by default
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
            wait.until(ExpectedConditions.stalenessOf(closeButton));
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default

    }


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        driver.manage().window().setSize(new Dimension(1600, 1100));
        myLocators = new locators();
        guestFunctions = new GuestFunctions(driver, myLocators);
        localStorageJS = new LocalStorageJS(driver);

    }

    @AfterAll
    public static void tearDown () {
        driver.quit();
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, myLocators);
        driver.get(BASE_URL);
        closePopup();
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        if (!isTestSuccessful) {
            // Log.error
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");

            takeScreenShot();
            takePageSource();
       }

        driver.manage().deleteAllCookies();

        localStorageJS.removeItemFromLocalStorage("accessToken");
        localStorageJS.removeItemFromLocalStorage("refreshToken");

        driver.navigate().refresh();
    }
}
