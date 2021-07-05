package app.project.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Set;

public class SetUpPage {

    private WebDriver driver;
    //setProperty for Chrome
    private String driverChrome = "webdriver.chrome.driver";
    private String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
    //setProperty for IE
    private String driverIE = "webdriver.ie.driver";
    private String iePath = System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe";
    //setProperty for Firefox
    private String driverfirefox = "webdriver.gecko.driver";
    private String firefoxPath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";

    public SetUpPage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver CreateMyDriver(DRIVERS myDriver) {
        switch (myDriver) {
            case CHROME:
                setProperty(driverChrome, chromePath);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                return driver;
            case FIREFOX:
                setProperty(driverfirefox, firefoxPath);
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                return driver;
            case IE:
                setProperty(driverIE, iePath);
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                return driver;
            default:
                throw new IllegalArgumentException("Driver provided is not implemented.");
        }
    }

    private void setProperty(String webDriver, String path){
        System.setProperty(webDriver, path);
    }

    public WebElement findElement(By locator){
        return getDriver().findElement(locator);
    }

    public boolean isDisplay(WebElement element){
        try {
            return element.isDisplayed();
        } catch(org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void click(By locator){
        getDriver().findElement(locator).click();
    }

    public void type(String inputText, By locator){
        getDriver().findElement(locator).sendKeys(inputText);
    }

    public void visitTo(String url){
        getDriver().get(url);
    }

    public void scrollPage(By locator){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,3500)","");
    }

    public boolean windowHandle(){
        String mainWindow = getWindowHandle();
        boolean isOtherPage = false;
        Set<String> windowHandles = getDriver().getWindowHandles();
        String otherWindow = null;
        for(String windowHandle: windowHandles){
            if(!windowHandle.equals(mainWindow))
                otherWindow = windowHandle;
                isOtherPage = true;
        }
        switchWindow(otherWindow);

        return isOtherPage;
    }

    public String getWindowHandle(){
        return getDriver().getWindowHandle();
    }

    public void switchWindow(String windowHandle){
        getDriver().switchTo().window(windowHandle);
    }

    public void selectProduct (WebElement element) throws AWTException, InterruptedException {
        Point p = element.getLocation();
        int x = p.getX();
        int y = p.getY();

        Robot robot = new Robot();

        robot.mouseMove(x,y);
        Thread.sleep(2000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(2000);
    }

    public String getTitlePage(){
        return getDriver().getTitle();
    }

    public void assertEquals(String actualResult, String expectedResult){
        Assert.assertEquals(actualResult, expectedResult);
    }

    public void assertTrue(boolean testResult){
        Assert.assertTrue(testResult);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
