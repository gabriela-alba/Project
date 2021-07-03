package app.project.test;

import app.project.classes.DRIVERS;
import app.project.classes.SearchAliExpress;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SearchTest {
        private WebDriver driver;
        String baseURL = "https://www.aliexpress.com/";
        SearchAliExpress searchPage;

        @BeforeTest
        public void openAliExpress(){
                searchPage = new SearchAliExpress(driver);
                driver = searchPage.CreateMyDriver(DRIVERS.FIREFOX);
                searchPage.visitTo(baseURL);
        }

        @AfterTest
        public void tearDown(){
             driver.quit();
        }

}
