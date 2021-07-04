package app.project.test;

import app.project.classes.DRIVERS;
import app.project.classes.SearchAliExpress;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchTest {
        private WebDriver driver;
        String baseURL = "https://www.aliexpress.com/";
        SearchAliExpress searchPage;

        @BeforeTest
        public void openAliExpress(){
                searchPage = new SearchAliExpress(driver);
                driver = searchPage.CreateMyDriver(DRIVERS.CHROME);
                searchPage.visitTo(baseURL);
        }

        @AfterTest
        public void tearDown(){
             driver.quit();
        }

        @Test(priority = 0)
        public void searchItem(){
                System.out.println("------------------TEST 1------------------\n");
                searchPage.searchBoxItem();
        }

        @Test(priority = 1)
        public void secondPage(){
                System.out.println("------------------TEST 2------------------\n");
                searchPage.goToSecondPage();
        }

        @Test(priority = 2)
        public void secondAd(){
                System.out.println("------------------TEST 3------------------\n");
                searchPage.hasStockSecondAd();
        }

}
