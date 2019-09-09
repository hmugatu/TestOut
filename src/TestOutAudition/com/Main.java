package TestOutAudition.com;

import com.sun.source.tree.WhileLoopTree;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //For windows
        //Driver path is required for selenium webdriver versions >3
        System.setProperty("webdriver.chrome.driver", "C:\\Java\\TestOut\\chromedriver.exe");

        // default options
        ChromeOptions options = new ChromeOptions();

        //initialize chrome driver instance
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //start a new chrome browser
        driver.get("http://testoutlivecontent.blob.core.windows.net/netpro2018v5-en-us/en-us/sims/typescriptv1/netpro2018v5/simstartup_webpack.html?package=netpro2018v5windowspackage&sim=ipademail_np5&dev=true&automation=true");

        Thread.sleep(3000); // want to avoid these, but wait untils are not working as expected

        // Select Settings and Click it
        WebElement settingsButton = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.id("wpDesktop.DesktopIcon15")));
        settingsButton.click();
        Thread.sleep(500);

        // Select Mail, Contacts, Calendars
        WebElement mailContactsCalendars = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(By.id("siMailContactsCalendars")));
        mailContactsCalendars.click();
        Thread.sleep(500);

        // Select Maggie Brown using helper function ByText
        WebElement maggieBrownButton = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(ByText("Maggie Brown")));
        maggieBrownButton.click();
        Thread.sleep(500);

        // Select mbrown@gmail.com
        WebElement email = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(ByText("mbrown@gmail.com")));
        email.click();
        Thread.sleep(500);

        // Select Advanced Button
        WebElement advancedButton = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.id("siAdvanced")));
        advancedButton.click();
        Thread.sleep(500);

        // Drag the Thumb for Use SSL
        WebElement useSslThumb = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.id("siUseSSL.Grid.tbOnOff.Grid.SwitchRoot.Canvas.SwitchThumb")));
        Actions act = new Actions(driver);
        act.moveToElement(useSslThumb, 3,3) // go to thumb
                .clickAndHold() // hold down mouse button
                .moveByOffset(15,0) // move it 15 pixels right
                .release() // release the mouse
                .sendKeys(Keys.TAB) // bring port into view
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .perform(); // do it.
        Thread.sleep(500);

        // find the port and assert it is 993
        WebElement serverPort = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.id("tbServerPort")));
        assert serverPort.getText() == "993";

        // Select Account
        WebElement account = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnAccount")));
        account.click();
        Thread.sleep(500);

        // Select Done
        WebElement done = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnDone")));
        done.click();
        Thread.sleep(500);

        // Click Wifi
        WebElement wifi = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#siWiFi")));
        wifi.click();
        Thread.sleep(1000);

        // Click CorpNet
        WebElement corpNet = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.id("SettingsItemNetwork")));
        corpNet.click();
        Thread.sleep(1000);

        // enter the password need double click for some reason
        WebElement password = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#tbPassword")));
        Actions act2 = new Actions(driver);
        act.doubleClick(password) // double click
            .sendKeys("@CorpNetWeRSecure!&") // type Password
            .perform(); // do it.

        // Click Join
        WebElement join = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnJoin")));
        join.click();
        Thread.sleep(1000);

        // Click Done Button
        WebElement doneButton = (new WebDriverWait(driver,5))
                .until(ExpectedConditions.elementToBeClickable(By.id("bDone")));
        doneButton.click();
        Thread.sleep(2000);

        // assert we have a score of 100%
        assert driver.findElement(ByContainsText("100%")).getText() == "Your Score: 1 of 1 (100%)";

        // done
        driver.quit();
    }

    // By Text return a selector for an element that has the exact same text.
    private static <string> By ByText(string text) {
        return By.xpath("//*[text() = '" + text + "']");
    }

    // By Contains Text returns a selector for an element that has some text we are looking for.
    private static <string> By ByContainsText(string text) {
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }
}
