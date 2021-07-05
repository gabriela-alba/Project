package app.project.test;

import app.project.classes.DRIVERS;
import app.project.classes.SearchAliExpress;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;

public class SearchTest {
        private WebDriver driver;
        private String expectedResult = null;
        private String actualResult = null;
        private String baseURL = "https://www.aliexpress.com/";
        private SearchAliExpress searchPage;

        @BeforeTest
        public void openAliExpress(){
                searchPage = new SearchAliExpress(driver);
                driver = searchPage.CreateMyDriver(DRIVERS.CHROME);
                searchPage.visitTo(baseURL);

                expectedResult = "AliExpress - Online Shopping for Popular Electronics, Fashion, Home & Garden, Toys & Sports, Automobiles and More products - AliExpress";
                actualResult = searchPage.getTitlePage();
                searchPage.assertEquals(actualResult, expectedResult);
        }

        @AfterTest
        public void tearDown(){
             driver.quit();
        }

        @Test(priority = 0)
        public void searchItem(){
                System.out.println("------------------TEST 1------------------\n");
                searchPage.searchBoxItem();

                expectedResult = "Iphone - Buy Iphone with free shipping on AliExpress";
                actualResult = searchPage.getTitlePage();
                searchPage.assertEquals(actualResult, expectedResult);
        }

        @Test(priority = 1)
        public void navigateToPage(){
                System.out.println("------------------TEST 2------------------\n");
                searchPage.goToPage();
        }

        @Test(priority = 2)
        public void stockProduct() throws InterruptedException, AWTException {
                System.out.println("------------------TEST 3------------------\n");
                searchPage.assertTrue(searchPage.hasStock());
        }



}
