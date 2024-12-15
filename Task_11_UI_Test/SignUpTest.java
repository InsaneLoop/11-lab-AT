
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

class SignUpPage {

    WebDriver driver;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "signUpButton")
    WebElement signUpButton;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignUp() {
        signUpButton.click();
    }
}

class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//h1[contains(text(),'Welcome')]")
    WebElement welcomeMessage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUserLoggedIn() {
        return welcomeMessage.isDisplayed();
    }
}

public class SignUpTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://your-test-page.com/signup");

            SignUpPage signUpPage = new SignUpPage(driver);
            HomePage homePage = new HomePage(driver);

            String email = "testuser@example.com";
            String username = "testuser";
            String password = "StrongPassword123";

            signUpPage.enterEmail(email);
            signUpPage.enterUsername(username);
            signUpPage.enterPassword(password);
            signUpPage.clickSignUp();

            String expectedURL = "http://your-test-page.com/home";
            if (driver.getCurrentUrl().equals(expectedURL) && homePage.isUserLoggedIn()) {
                System.out.println("Test Passed: User successfully signed up and logged in.");
            } else {
                System.out.println("Test Failed: User was not redirected to home page or logged in.");
            }
        } finally {
            driver.quit();
        }
    }
}
