package framework.library;

import framework.data.UserData;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;

public class GuestFunctions {
    public static final Logger logger = LoggerFactory.getLogger(GuestFunctions.class);
    private WebDriver driver;
    private final WebDriverWait wait;
    private final locators locators;

    public GuestFunctions(WebDriver driver, locators locators) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.locators = locators;
    }

   public void signIn(UserData userData) {
       logger.debug("Start signIn with user = " + userData);
       wait.until(ExpectedConditions.visibilityOf(locators.getSignInButton()));
       locators.getSignInButton().click();
       locators.getEmailInput().sendKeys(userData.getEmail());
       locators.getPasswordInput().sendKeys(userData.getPassword());
       wait.until(ExpectedConditions.elementToBeClickable(locators.getSignInSubmitButton()));
       locators.getSignInSubmitButton().click();
       wait.until(ExpectedConditions.visibilityOf(locators.getUserNameDropdown()));
       wait.until(ExpectedConditions.elementToBeClickable(locators.getEcoNews()));
   }

    public void signOut () {
       WebElement userNameDropdown = wait.until(ExpectedConditions.elementToBeClickable(locators.getUserNameDropdown()));

        try {
            userNameDropdown.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userNameDropdown);
        }

        try {
            WebElement signOutBtn = wait.until(ExpectedConditions.elementToBeClickable(locators.getSignOutButton()));
            signOutBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", locators.getSignOutButton());
        }
    }

    public void openFirstNewsPage(UserData userData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        signIn(userData);
        locators.getEcoNews().click();
        wait.until(ExpectedConditions.elementToBeClickable(locators.getFirstNews()));
        locators.getFirstNews().click();
    }

    public void waitForCommentToAppear(WebDriverWait wait, WebElement element, String expectedText) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            assertThat(element.getText(), CoreMatchers.is(expectedText));
        } catch (TimeoutException e) {
            throw new AssertionError("Expected text not found within the specified timeout: " + expectedText);
        }
    }
}

