package framework.library;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class locators {

        @FindBy(css = ".header_sign-in-link")
        private WebElement signInButton;
        @FindBy(id = "email")
        private WebElement emailInput;
        @FindBy(id = "password")
        private WebElement passwordInput;
        @FindBy(css = ".greenStyle")
        private WebElement signInSubmitButton;
        @FindBy(xpath = "//header//a[contains(text(), 'Eco news')]")
        private WebElement ecoNews;
        @FindBy(xpath = "(//div[@class='list-gallery'])[1]")
        private WebElement firstNews;
        @FindBy(xpath = "//img[@alt='close button']")
        private WebElement closeButton;
        @FindBy(css = ".comment-textarea-wrapper .comment-textarea")
        private WebElement commentField;
        @FindBy(xpath = "//button[@class='primary-global-button']")
        private WebElement commentButton;
        @FindBy(xpath = "(//div[@class=\"comment-details\"])[1]/span[@class=\"author-name\"]")
        private WebElement commentAuthor;
        @FindBy(xpath = "(//div[@class=\"comment-main-text\"])[1]")
        private WebElement commentText;
        @FindBy(xpath = "(//div[@class=\"profile-avatar comments-list default-avatar ng-star-inserted\"])[1]/p")
        private WebElement authorIconInitial;
        @FindBy(xpath = "//*[@id='header_user-wrp']/li")
        private WebElement userNameDropdown;
        @FindBy(xpath = "//li[@aria-label='sign-out']/a[contains(text(),'Sign out')]")
        private WebElement signOutButton;
        public WebElement getSignInButton() {
                return signInButton;
        }

        public WebElement getEmailInput() {
                return emailInput;
        }

        public WebElement getPasswordInput() {
                return passwordInput;
        }

        public WebElement getSignInSubmitButton() {
                return signInSubmitButton;
        }

        public WebElement getEcoNews() {
                return ecoNews;
        }

        public WebElement getFirstNews() {
                return firstNews;
        }

        public WebElement getCloseButton() {
                return closeButton;
        }

        public WebElement getCommentField() {
                return commentField;
        }

        public WebElement getCommentButton() {
                return commentButton;
        }

        public WebElement getCommentAuthor() {
                return commentAuthor;
        }

        public WebElement getCommentText() {
                return commentText;
        }

        public WebElement getAuthorIconInitial() {
                return authorIconInitial;
        }

        public WebElement getUserNameDropdown() {
                return userNameDropdown;
        }

        public WebElement getSignOutButton() {
                return signOutButton;
        }
}
