package app.project.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;

public class SearchAliExpress extends SetUpPage {
    private final String OUT_OF_STOCK = "0 pieces available";
    private WebDriverWait wait;
    private By search_box = By.id("search-key");
    private By submit_btn = By.xpath("//*[@id=\"form-searchbar\"]/div[1]/input");
    private By second_page = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/button[2]");
    private By second_ad = By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[1]/a/span");
    private By stock = By.xpath("//span[contains(text(), 'pieces available')]");

    public SearchAliExpress(WebDriver driver){
        super(driver);
    }

    public void searchBoxItem(){
        String search_txt = "Iphone";
        wait = new WebDriverWait(getDriver(), 15);
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(search_box));

        if (isDisplay(box)){
            type(search_txt,search_box);
            click(submit_btn);
            System.out.println("The item "+ search_txt +" was successfully searched.\n");
        } else {
            throw new IllegalArgumentException("Search box was not found.");
        }
    }

    public void goToPage(){
        scrollPage(second_page);
        wait = new WebDriverWait(getDriver(), 20);
        WebElement sp = wait.until(ExpectedConditions.visibilityOfElementLocated(second_page));

        if(isDisplay(sp)){
            click(second_page);
            System.out.println("The second page was successfully accessed.\n");
        } else {
            throw new IllegalArgumentException("Search box was not found.\n");
        }
    }

    public boolean hasStock() throws InterruptedException, AWTException {
        boolean hasStock = false;
        wait = new WebDriverWait(getDriver(), 20);
        WebElement element_ad = wait.until(ExpectedConditions.elementToBeClickable(second_ad));

        if(isDisplay(element_ad)){
            selectProduct(element_ad);
            windowHandle();
            wait = new WebDriverWait(getDriver(), 20);
            WebElement num_stock = wait.until(ExpectedConditions.visibilityOfElementLocated(stock));
            String stk = findElement(stock).getText();
            if(stk.equals(OUT_OF_STOCK)){
                hasStock = true;
                System.out.println("The second ad has at least 1 item to be bought: "+ hasStock);
                System.out.println("Stock available: " + stk);
            }else{
                System.out.println("The second ad has not stock available.\n");
            }

        } else {
            throw new IllegalArgumentException("Second ad was not found.\n");
        }
        return hasStock;
    }

}




