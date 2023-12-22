package framework.test;

import framework.data.UserData;
import framework.data.UserRepository;
import framework.library.locators;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import framework.library.GuestFunctions;
import framework.library.locators;

public class AddCommentTest extends TestRunner {

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of(UserRepository.getValidUser()),
                Arguments.of(UserRepository.getValidUser2())
        );
    }


        @ParameterizedTest(name = "{index} => user={0}")
        @MethodSource("provideData")

        public void checkCommentText (UserData userData){
           guestFunctions.openFirstNewsPage(userData);
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            wait.until(ExpectedConditions.visibilityOf(myLocators.getCommentField()));
            myLocators.getCommentField().sendKeys(userData.getComment());
            myLocators.getCommentButton().click();

            guestFunctions.waitForCommentToAppear(wait, myLocators.getCommentAuthor(), userData.getUsername());

            assertThat(myLocators.getCommentAuthor().getText(), CoreMatchers.is(userData.getUsername()));
            assertThat(myLocators.getCommentText().getText(), is(userData.getComment()));

            wait.until(ExpectedConditions.elementToBeClickable(myLocators.getUserNameDropdown()));

        }

    @ParameterizedTest(name = "{index} => user={0}")
    @MethodSource("provideData")

    public void checkAuthorIcon (UserData userData){
        guestFunctions.openFirstNewsPage(userData);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOf(myLocators.getCommentField()));
        myLocators.getCommentField().sendKeys(userData.getComment());
        myLocators.getCommentButton().click();

        guestFunctions.waitForCommentToAppear(wait, myLocators.getCommentAuthor(), userData.getUsername());

        assertThat(myLocators.getAuthorIconInitial().getText(), is(userData.getInitial()));

        wait.until(ExpectedConditions.elementToBeClickable(myLocators.getUserNameDropdown()));

    }

}
