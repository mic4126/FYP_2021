package hk.cityu.cs.AiRegistry.FIT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.seljup.Options;

public class ManagementAdmin extends BaseTest {

    @Options
    ChromeOptions options = Utils.ChromeOptions();
    private String username = "admin";
    private String password = "123";

    @Test
    public void adminLoginTest(ChromeDriver driver) {
        Utils.login(username, password, driver);

        var welcomeText = driver.findElement(By.xpath("//*[contains(text(),'Welcome')]")).getText();

        assertThat(welcomeText).isNotBlank();
    }

    @Test
    public void adminChangeUserTest(ChromeDriver driver) {

        Utils.login(username, password, driver);

        driver.findElement(By.xpath("//nav/a[contains(text(),\"User Info\")]")).click();

        String firstName = "test";
        String lastName = "tset";
        // enter information
        var firstNameField = driver.findElement(By.id("firstname"));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        var lastNameField = driver.findElement(By.id("lastname"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        driver.findElement(By.id("password")).sendKeys(password);
        // click submit btn
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // comfirm
        firstNameField = driver.findElement(By.id("firstname"));
        lastNameField = driver.findElement(By.id("lastname"));
        var actualFirstName = firstNameField.getAttribute("value");
        var actualLastName = lastNameField.getAttribute("value");

        assertSoftly(s -> {
            s.assertThat(actualFirstName).isEqualTo(firstName);
            s.assertThat(actualLastName).isEqualTo(lastName);
        });

    }

    @Test
    public void adminChangePasswordTest(ChromeDriver driver) {
        Utils.login(username, password, driver);

        driver.findElement(By.xpath("//nav/a[contains(text(),\"Password\")]")).click();

        // Enter password
        String newPassword = 123456 + "";
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("newPassword")).sendKeys(newPassword);
        driver.findElement(By.id("newPasswordCheck")).sendKeys(newPassword);

        // click submit btn
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // wait login page show
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[contains(text(),\"AI Registry Admin Login\")]")));
        // login again check
        Utils.login(username, newPassword, driver);

        var welcomeText = """
                Welcome, admin""";

        var actualWelcomeText = driver.findElement(By.xpath("""
                //app-index/div/h1
                    """)).getText();
        assertThat(actualWelcomeText).isEqualTo(welcomeText);

    }

    @Test
    public void createUserTest(ChromeDriver driver) throws InterruptedException {

        Utils.login(username, password, driver);

        // Enter create user page
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Create User')]")).click();

        String username = "admin100";
        String firstname = "fit";
        String lastname = "test";
        String email = "test@example.com";

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("userType_Admin")).click();

        driver.findElement(By.xpath("//*[contains(text(),'Submit')]")).click();

        Thread.sleep(1000);

        var actualUsername = driver.findElement(By.id("username")).getAttribute("value");
        var actualFirstname = driver.findElement(By.id("firstname")).getAttribute("value");
        var actualLastname = driver.findElement(By.id("lastname")).getAttribute("value");
        var actualEmail = driver.findElement(By.id("email")).getAttribute("value");

        assertSoftly(s -> {
            s.assertThat(actualUsername).isNullOrEmpty();
            s.assertThat(actualFirstname).isNullOrEmpty();
            s.assertThat(actualLastname).isNullOrEmpty();
            s.assertThat(actualEmail).isNullOrEmpty();
        });
    }

    @Test
    public void deleteUserTest(ChromeDriver driver) throws InterruptedException {

        Utils.login(username, password, driver);

        // Enter create user page
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Delete User')]")).click();

        driver.findElement(By.xpath("//*[text()='Username: admin1']/following-sibling::button")).click();

        Thread.sleep(1000);
        var users = driver.findElements(By.xpath("//*[text()='Username: admin1']"));
        assertThat(users).isEmpty();

    }

    @Test
    public void createProjectTest(ChromeDriver driver) throws InterruptedException {
        Utils.login(username, password, driver);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Create Project')]")).click();

        Thread.sleep(1000);
        String projectName = "fit test";
        String[] devs = { "dev" };

        driver.findElement(By.id("projectName")).sendKeys(projectName);

        driver.findElement(By.className("ng-select-container")).click();

        for (String dev : devs) {
            driver.findElement(By.xpath("//div[normalize-space()='" + dev + "']/input")).click();
        }

        driver.findElement(By.xpath("//button[normalize-space() = 'Submit']")).click();

        Thread.sleep(1000);

        var actualProjectName = driver.findElement(By.id("projectName")).getAttribute("value");

        assertThat(actualProjectName).isNullOrEmpty();

    }

    @Test
    public void assignDevToProjectTest(ChromeDriver driver) throws InterruptedException {
        Utils.login(username, password, driver);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text() = 'Assign developers to project']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='projectName-select']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[normalize-space() = '測試測試']")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("projectDev-select")).click();
        driver.findElement(By.xpath("//*[normalize-space() = 'dev1']")).click();

        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='projectName-select']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[normalize-space() = '測試測試']")).click();


        var devs = driver.findElements(By.xpath(
                "//*[@id='projectDev-select']//*[contains(@class,'ng-value-container')]//*[contains(text(),'dev')]"));
        assertThat(devs).hasSize(2);
    }

}
