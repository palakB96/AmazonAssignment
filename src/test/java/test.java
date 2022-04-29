import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class test {

        static WebDriver driver;
        Properties p;
        String selectedQty,valueInCart;
        WebElement e;
        boolean elementPresent = false;

        @Parameters({"Browser"})
        @Test(priority = 1)
        public void launchBrowser()
        {
            System.setProperty("webdriver.chrome.driver","/home/palakb/Downloads/chromedriver_linux64/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(p.getProperty("Url"));
        }

        @Test(priority = 2)
        public void todayDeal()
        {
            driver.findElement(By.cssSelector(p.getProperty("Amazon.todaydeal.cssselector"))).click();
        }

        @Test(priority = 3)
        public void selectThirdDeal()
        {
            System.out.println("thirddeal");
            driver.findElement(By.xpath(p.getProperty("ThirdDeal.xpath"))).click();
            driver.findElement(By.xpath(p.getProperty("Third"))).click();
        }
        @BeforeMethod(groups = {"Smoke"})
        public void property() throws IOException {
            p = new Properties();
            FileInputStream FI = new FileInputStream("/home/palakb/IdeaProjects/mavenproject/src/Data.properties");
            p.load(FI);

        }
//
//        @Test(priority = 4)
//        public void addToCart() throws NoSuchElementException
//        {
//            elementPresent = driver.findElement(By.id(p.getProperty("QuantityDropdown.Id"))).isDisplayed();
//            if(elementPresent == true) {
//                Select s = new Select(driver.findElement(By.id(p.getProperty("QuantityDropdown.Id"))));
//                s.selectByIndex(0);
//                selectedQty = s.getFirstSelectedOption().getText();
//            }
//            driver.findElement(By.id(p.getProperty("Add.To.Cart.Button.Id"))).click();
//        }
//
//        @Test(priority = 5)
//        public void navToCart()
//        {
//            driver.findElement(By.id(p.getProperty("nav.cart.id"))).click();
//
//            if(elementPresent == true)
//            {
//                String s = driver.findElement(By.id("a-autoid-0-announce")).getText();
//                String[] arr = s.split(":");
//                System.out.println(arr.length);
//                valueInCart = arr[1];
//                System.out.println(valueInCart);
//                Assert.assertEquals(selectedQty,valueInCart);
//
//            }}
//
//        @Test(priority = 6)
//        public void search()
//        {
//            driver.findElement(By.id(p.getProperty("valueInCart.id"))).sendKeys("Mobile");
//            driver.findElement(By.id(p.getProperty("valueInCart.id"))).sendKeys(Keys.ENTER);
//
//        }
//
//        @Test(priority = 7)
//        public void scrollPage() throws InterruptedException {
//            Actions act = new Actions(driver);
//            act.sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
//            System.out.println("Scroll down perfomed");
//            Thread.sleep(3000);
//        }
//        @AfterClass
//        public void closeBrowser()
//        {
//            driver.quit();
//        }

    }


