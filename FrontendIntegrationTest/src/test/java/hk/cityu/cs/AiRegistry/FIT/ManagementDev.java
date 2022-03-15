package hk.cityu.cs.AiRegistry.FIT;

import static hk.cityu.cs.AiRegistry.FIT.Utils.clearAndEnterString;
import static hk.cityu.cs.AiRegistry.FIT.Utils.genTempFile;
import static hk.cityu.cs.AiRegistry.FIT.Utils.scrollToElement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.seljup.Options;

public class ManagementDev extends BaseTest {

    @Options
    ChromeOptions options = Utils.ChromeOptions();

    @Test
    public void devLoginTest(ChromeDriver driver) {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        var welcomeText = """
                Welcome, dev""";

        var actualWelcomeText = driver.findElement(By.xpath("""
                //app-index/div/h1
                    """)).getText();
        assertThat(actualWelcomeText).isEqualTo(welcomeText);
    }

    @Test
    public void devChangeUserTest(ChromeDriver driver) {
        String username = "dev";
        String password = "123";

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
    public void devChangePasswordTest(ChromeDriver driver) {
        String username = "dev";
        String password = "123";

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
                Welcome, dev""";

        var actualWelcomeText = driver.findElement(By.xpath("""
                //app-index/div/h1
                    """)).getText();
        assertThat(actualWelcomeText).isEqualTo(welcomeText);

    }

    @Test
    public void updateProjectNameTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        String engName = "FIT test eng";
        String tcName = "FIT test tc";
        String scName = "FIT test sc";
        var engNameInput = driver.findElement(By.xpath("//input[@formcontrolname='engName']"));
        engNameInput.clear();
        engNameInput.sendKeys(engName);

        var tcNameInput = driver.findElement(By.xpath("//input[@formcontrolname='TCName']"));
        tcNameInput.clear();
        tcNameInput.sendKeys(tcName);

        var scNameInput = driver.findElement(By.xpath("//input[@formcontrolname='SCName']"));
        scNameInput.clear();
        scNameInput.sendKeys(scName);

        driver.findElement(By.id("projectNameUpdateBtn")).click();

        Thread.sleep(1000);
        driver.navigate().refresh();

        // check text

        Thread.sleep(1000);
        engNameInput = driver.findElement(By.xpath("//input[@formcontrolname='engName']"));
        tcNameInput = driver.findElement(By.xpath("//input[@formcontrolname='TCName']"));
        scNameInput = driver.findElement(By.xpath("//input[@formcontrolname='SCName']"));

        var actualEngName = engNameInput.getAttribute("value");
        var actualTcName = tcNameInput.getAttribute("value");
        var actualScName = scNameInput.getAttribute("value");

        assertSoftly(s -> {
            s.assertThat(actualEngName).isEqualTo(engName);
            s.assertThat(actualTcName).isEqualTo(tcName);
            s.assertThat(actualScName).isEqualTo(scName);
        });

    }

    @Test
    public void setDescriptionTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        String engDesc = "FIT Desc Test ENG";
        String TCDesc = "FIT Desc Test TC";
        String SCDesc = "FIT Desc Test SC";

        // change english description
        driver.findElement(By.xpath("//a[@data-desc-lang='eng']")).click();
        var textAreaWebEl = driver.findElement(By.id("desc-textarea-ENG"));
        Thread.sleep(1000);
        textAreaWebEl.clear();
        textAreaWebEl.sendKeys(engDesc);
        WebElement updateDescEngBtn = driver.findElement(By.id("update-desc-ENG"));
        scrollToElement(driver, updateDescEngBtn);
        updateDescEngBtn.click();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        WebElement engTag = driver.findElement(By.xpath("//a[@data-desc-lang='eng']"));
        scrollToElement(driver, engTag);
        engTag.click();
        textAreaWebEl = driver.findElement(By.id("desc-textarea-ENG"));
        var actualEngDesc = textAreaWebEl.getAttribute("value");

        // Change tc desc
        driver.findElement(By.xpath("//a[@data-desc-lang='tc']")).click();
        textAreaWebEl = driver.findElement(By.id("desc-textarea-TC"));
        textAreaWebEl.clear();
        textAreaWebEl.sendKeys(TCDesc);
        // Click Apply button
        WebElement updateDescTCBtn = driver.findElement(By.id("update-desc-TC"));
        scrollToElement(driver, updateDescTCBtn);
        updateDescTCBtn.click();

        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        WebElement tcTab = driver.findElement(By.xpath("//a[@data-desc-lang='tc']"));
        scrollToElement(driver, tcTab);
        tcTab.click();
        textAreaWebEl = driver.findElement(By.id("desc-textarea-TC"));
        var actualTCDesc = textAreaWebEl.getAttribute("value");

        // Change sc desc
        driver.findElement(By.xpath("//a[@data-desc-lang='sc']")).click();
        textAreaWebEl = driver.findElement(By.id("desc-textarea-SC"));
        textAreaWebEl.clear();
        textAreaWebEl.sendKeys(SCDesc);

        WebElement updateDescSCBtn = driver.findElement(By.id("update-desc-SC"));
        scrollToElement(driver, updateDescSCBtn);
        updateDescSCBtn.click();

        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        WebElement scTab = driver.findElement(By.xpath("//a[@data-desc-lang='sc']"));
        scrollToElement(driver, scTab);
        scTab.click();
        textAreaWebEl = driver.findElement(By.id("desc-textarea-SC"));
        var actualSCDesc = textAreaWebEl.getAttribute("value");

        assertSoftly(s -> {
            s.assertThat(actualEngDesc).isEqualTo(engDesc);
            s.assertThat(actualTCDesc).isEqualTo(TCDesc);
            s.assertThat(actualSCDesc).isEqualTo(SCDesc);
        });
    }

    @Test
    public void updateContactTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        var contactTitle = driver.findElement(By.id("contact-title"));
        scrollToElement(driver, contactTitle);

        String email = "fit@example.com";
        var emailInput = driver.findElement(By.xpath("//input[@formcontrolname='email']"));
        emailInput.clear();
        emailInput.sendKeys(email);

        String department = "FIT test department eng";
        var departmentInput = driver.findElement(By.xpath("//input[@formcontrolname='department']"));
        departmentInput.clear();
        departmentInput.sendKeys(department);

        String departmentTC = "FIT test department tc";
        var departmentTCInput = driver.findElement(By.xpath("//input[@formcontrolname='department_TC']"));
        departmentTCInput.clear();
        departmentTCInput.sendKeys(departmentTC);

        String departmentSC = "FIT test department sc";
        var departmentSCInput = driver.findElement(By.xpath("//input[@formcontrolname='department_SC']"));
        departmentSCInput.clear();
        departmentSCInput.sendKeys(departmentSC);

        String phonenumber = "98765432";
        var phoneNumberInput = driver.findElement(By.xpath("//input[@formcontrolname='phoneNumber']"));
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phonenumber);

        String url = "http://example.com";
        var urlInput = driver.findElement(By.xpath("//input[@formcontrolname='url']"));
        urlInput.clear();
        urlInput.sendKeys(url);

        WebElement updateBtn = driver.findElement(By.id("update-contact-btn"));
        scrollToElement(driver, updateBtn);
        updateBtn.click();
        Thread.sleep(1000);

        driver.navigate().refresh();
        contactTitle = driver.findElement(By.id("contact-title"));
        scrollToElement(driver, contactTitle);

        var actualEmail = driver.findElement(By.xpath("//input[@formcontrolname='email']")).getAttribute("value");
        var actualDepartment = driver.findElement(By.xpath("//input[@formcontrolname='department']"))
                .getAttribute("value");
        var actualDepartmentTC = driver.findElement(By.xpath("//input[@formcontrolname='department_TC']"))
                .getAttribute("value");
        var actualDepartmentSC = driver.findElement(By.xpath("//input[@formcontrolname='department_SC']"))
                .getAttribute("value");
        var actualPhoneNumber = driver.findElement(By.xpath("//input[@formcontrolname='phoneNumber']"))
                .getAttribute("value");
        var actualUrl = driver.findElement(By.xpath("//input[@formcontrolname='url']")).getAttribute("value");

        assertSoftly(s -> {
            s.assertThat(actualEmail).isEqualTo(email);
            s.assertThat(actualDepartment).isEqualTo(department);
            s.assertThat(actualDepartmentTC).isEqualTo(departmentTC);
            s.assertThat(actualDepartmentSC).isEqualTo(departmentSC);
            s.assertThat(actualPhoneNumber).isEqualTo(phonenumber);
            s.assertThat(actualUrl).isEqualTo(url);
        });

    }

    @Test
    public void addTagTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        // Scroll to add tag
        Thread.sleep(1000);
        var addTagBtn = driver.findElement(By.xpath("//button[contains(text(),'Add Tag')]"));
        scrollToElement(driver, addTagBtn);
        addTagBtn.click();

        var tag = "FIT Test";
        driver.findElement(By.xpath("//input[@formcontrolname='tag']")).sendKeys(tag);

        var tagTC = "FIT test TC";
        driver.findElement(By.xpath("//input[@formcontrolname='tagTC']")).sendKeys(tagTC);

        var tagSC = "FIT test SC";
        driver.findElement(By.xpath("//input[@formcontrolname='tagSC']")).sendKeys(tagSC);

        driver.findElement(By.cssSelector("div.modal-footer > button")).click();

        var actualTag = driver.findElement(By.xpath("//td[contains(text(),'" + tag + "')]")).getText();
        var actualTagTC = driver.findElement(By.xpath("//td[contains(text(),'" + tagTC + "')]")).getText();
        var actualTagSC = driver.findElement(By.xpath("//td[contains(text(),'" + tagSC + "')]")).getText();

        assertSoftly(s -> {
            s.assertThat(actualTag).isEqualTo(tag);
            s.assertThat(actualTagTC).isEqualTo(tagTC);
            s.assertThat(actualTagSC).isEqualTo(tagSC);
        });
    }

    @Test
    public void deleteTagTest(ChromeDriver driver) throws InterruptedException {

        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        // Scroll to delete tag
        Thread.sleep(1000);
        var delTagBtn = driver.findElement(By.xpath("//td/button[contains(text(),'Delete')]"));
        scrollToElement(driver, delTagBtn);
        delTagBtn.click();

        Thread.sleep(1000);

        var els = driver.findElements(By.xpath("//td"));
        assertThat(els).isEmpty();
    }

    @Test
    public void addDetailTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        // Scroll to add detail btn
        Thread.sleep(1000);
        var addDetailBtn = driver.findElement(By.xpath("//*[contains(text(),'Add Detail')]"));
        scrollToElement(driver, addDetailBtn);
        addDetailBtn.click();

        Thread.sleep(1000);

        String detailName = "FIT Test detail";

        driver.findElement(By.id("detail-name-input")).sendKeys(detailName);

        driver.findElement(By.id("add-detail-modal-btn")).click();

        Thread.sleep(1000);

        var details = driver.findElements(By.xpath("//button[contains(text(),'" + detailName + "')]"));
        assertThat(details).isNotEmpty();

    }

    @Test
    public void updateDetailTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        Thread.sleep(1000);
        var testDetail = driver.findElement(By.xpath("//button[contains(text(),'Test Detail')]"));
        scrollToElement(driver, testDetail);
        testDetail.click();

        String detailName = "FIT Test detail name eng";
        String detailNameTC = "FIT Test detail name tc";
        String detailNameSC = "FIT Test detail name SC";
        String detailDesc = "FIT Test detail desc eng";
        String detailDescTC = "FIT Test detail desc tc";
        String detailDescSC = "FIT Test detail desc SC";

        var detailNameInput = driver.findElement(By.id("detailName"));
        clearAndEnterString(detailNameInput, detailName);

        var detailNameTCInput = driver.findElement(By.id("detailName_TC"));
        clearAndEnterString(detailNameTCInput, detailNameTC);

        var detailNameSCInput = driver.findElement(By.id("detailName_SC"));
        clearAndEnterString(detailNameSCInput, detailNameSC);

        var detailDescInput = driver.findElement(By.id("detailDesc"));
        scrollToElement(driver, detailDescInput);
        clearAndEnterString(detailDescInput, detailDesc);

        var detailDescTCInput = driver.findElement(By.id("detailDesc_TC"));
        scrollToElement(driver, detailDescTCInput);
        clearAndEnterString(detailDescTCInput, detailDescTC);

        var detailDescSCInput = driver.findElement(By.id("detailDesc_SC"));
        scrollToElement(driver, detailDescSCInput);
        clearAndEnterString(detailDescSCInput, detailDescSC);

        // click update button
        driver.findElement(By.id("update-detail-btn")).click();

        Thread.sleep(1000);
        testDetail = driver.findElement(By.xpath("//button[contains(text(),'" + detailName + "')]"));
        scrollToElement(driver, testDetail);
        testDetail.click();

        detailNameInput = driver.findElement(By.id("detailName"));
        var actualDetailName = detailNameInput.getAttribute("value");

        detailNameTCInput = driver.findElement(By.id("detailName_TC"));
        var actualDetailTCName = detailNameTCInput.getAttribute("value");

        detailNameSCInput = driver.findElement(By.id("detailName_SC"));
        var actualDetailSCName = detailNameSCInput.getAttribute("value");

        detailDescInput = driver.findElement(By.id("detailDesc"));
        var actualDesc = detailDescInput.getAttribute("value");

        detailDescTCInput = driver.findElement(By.id("detailDesc_TC"));
        var actualDescTC = detailDescTCInput.getAttribute("value");

        detailDescSCInput = driver.findElement(By.id("detailDesc_SC"));
        var actualDescSC = detailDescSCInput.getAttribute("value");

        assertSoftly(s -> {
            s.assertThat(actualDetailName).isEqualTo(detailName);
            s.assertThat(actualDetailTCName).isEqualTo(detailNameTC);
            s.assertThat(actualDetailSCName).isEqualTo(detailNameSC);
            s.assertThat(actualDesc).isEqualTo(detailDesc);
            s.assertThat(actualDescTC).isEqualTo(detailDescTC);
            s.assertThat(actualDescSC).isEqualTo(detailDescSC);
        });

    }

    @Test
    public void deleteDetailTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        Thread.sleep(1000);
        var testDetail = driver.findElement(By.xpath("//button[contains(text(),'Test Detail')]"));
        scrollToElement(driver, testDetail);
        testDetail.click();

        Thread.sleep(1000);
        var deleteBtn = driver.findElement(By.id("delete-detail-btn"));
        scrollToElement(driver, deleteBtn);
        deleteBtn.click();

        var details = driver.findElements(By.xpath("//a[contains(text(),'測試測試')]"));

        assertThat(details).isEmpty();

    }

    @Test
    public void deleteDetailAttachmentTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        // open detail
        Thread.sleep(1000);
        var testDetail = driver.findElement(By.xpath("//button[contains(text(),'Test Detail')]"));
        scrollToElement(driver, testDetail);
        testDetail.click();
        Thread.sleep(1000);

        var atchDelBtn = driver.findElement(By.xpath("//a[text() = 'q2.PNG']/ancestor::tr//button"));
        scrollToElement(driver, atchDelBtn);
        atchDelBtn.click();

        Thread.sleep(1000);
        var attachments = driver.findElements(By.xpath("//a[text() = 'q2.PNG']"));
        assertThat(attachments).isEmpty();
    }

    @Test
    public void deletePhotoTest(ChromeDriver driver) throws InterruptedException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Delete Photo Above')]")).click();

        Thread.sleep(1000);

        var photoDelBtns = driver.findElements(By.xpath("//button[contains(text(),'Delete Photo Above')]"));

        assertThat(photoDelBtns).isEmpty();

    }

    @Test
    public void addPhotoTest(ChromeDriver driver) throws InterruptedException, IOException {
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);

        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[contains(text(),'Add Photo')]")).click();
        Thread.sleep(1000);

        var prefix = "fit";
        var suffix = ".png";
        var data = "1234567890";
        var uploadFile = genTempFile(prefix, suffix, data);

        var uploadFilePath = uploadFile.getAbsolutePath();

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath);

        driver.findElement(By.id("add-photo-modal-btn")).click();

        Thread.sleep(1000);

        var photoDelBtns = driver.findElements(By.xpath("//button[contains(text(),'Delete Photo Above')]"));

        assertThat(photoDelBtns).hasSize(2);
    }

    @Test
    public void enableDisableProjectTest(ChromeDriver driver) throws InterruptedException{
        String username = "dev";
        String password = "123";

        Utils.login(username, password, driver);
        
        // Enter page
        driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("project-status")).click();
        Thread.sleep(1000);

        driver.navigate().refresh();
        Thread.sleep(1000);

        var status = driver.findElement(By.id("project-status")).isSelected();
        assertThat(status).isFalse();

        driver.findElement(By.id("project-status")).click();
        Thread.sleep(1000);

        driver.navigate().refresh();
        Thread.sleep(1000);

        status = driver.findElement(By.id("project-status")).isSelected();
        assertThat(status).isTrue();
        

    }

    @Nested
    class AddAttachmentTest {

        @Options
        ChromeOptions options;

        Path tempFolder;

        @BeforeEach
        public void initOption() throws IOException {
            options = Utils.ChromeOptions();
            tempFolder = Files.createTempDirectory("fit");
            var preferences = new HashMap<String, String>();
            preferences.put("download.default_directory", tempFolder.toString());
            options.setExperimentalOption("prefs", preferences);
        }

        @Test
        public void addDetailAttachment(ChromeDriver driver) throws InterruptedException, IOException {
            String username = "dev";
            String password = "123";

            Utils.login(username, password, driver);

            // Enter page
            driver.findElement(By.xpath("//a[contains(text(),'測試測試')]")).click();

            // open detail
            Thread.sleep(1000);
            var testDetail = driver.findElement(By.xpath("//button[contains(text(),'Test Detail')]"));
            scrollToElement(driver, testDetail);
            testDetail.click();
            Thread.sleep(1000);

            var addAtchBtn = driver.findElement(By.xpath("//button[text() = 'Add Attachment']"));
            scrollToElement(driver, addAtchBtn);
            addAtchBtn.click();

            Thread.sleep(1000);
            var prefix = "fit";
            var suffix = ".test";
            var data = "1234567890";
            var uploadFile = genTempFile(prefix, suffix, data);

            var uploadFilePath = uploadFile.getAbsolutePath();

            driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath);

            driver.findElement(By.id("add-atch-btn")).click();

            Thread.sleep(1000);

            WebElement attachmentLink = driver
                    .findElement(By.xpath("//a[contains(text(),'" + uploadFile.getName() + "')]"));
            scrollToElement(driver, attachmentLink);
            attachmentLink.click();

            Thread.sleep(5000);

            var downloadedAttachment = new File(tempFolder.toFile(), uploadFile.getName());

            assertThat(downloadedAttachment.isFile()).isTrue();

            var downloaded = Files.readAllBytes(downloadedAttachment.toPath());

            assertThat(downloaded).isEqualTo(data.getBytes());
        }
    }
}
