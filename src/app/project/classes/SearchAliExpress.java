package app.project.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchAliExpress extends SetUpPage {
    private final String OUT_OF_STOCK = "0 pieces available";
    private WebDriverWait wait;
    By search_box = By.id("search-key");
    By submit_btn = By.xpath("//*[@id=\"form-searchbar\"]/div[1]/input");

    public SearchAliExpress(WebDriver driver){
        super(driver);
    }

    public void searchBoxItem(){
        String search_txt = "Iphone";
        wait = new WebDriverWait(getDriver(), 15);
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(search_box));
        if (isDisplay(search_box)){
            type(search_txt,search_box);
            click(submit_btn);
            System.out.println("The item "+ search_txt +" was successfully searched.\n");
        } else {
            throw new IllegalArgumentException("Search box was not found.");
        }
    }


}




