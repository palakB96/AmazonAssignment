import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AmazonAssignment {
    static WebDriver driver;
    static String selectedQty;
    static String valueInCart;
    WebElement e;
    boolean elementPresent = false;

    public static void main(String args[]) throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/home/palakb/Downloads/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Properties p = new Properties();
        FileInputStream FI = new FileInputStream("/home/palakb/IdeaProjects/mavenproject/src/Data.properties");
        p.load(FI);
        driver.get(p.getProperty("Url"));
        driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).click();
        driver.findElement(By.id("ap_email")).sendKeys("palak.bhansal@zemosolabs.com");
        driver.findElement(By.cssSelector("#continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("Pass@123");
        driver.findElement(By.id("auth-signin-button")).click();
        driver.findElement(By.linkText("Today's Deals")).click();
        driver.findElement(By.xpath(p.getProperty("ThirdDeal.xpath"))).click();
        driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[1]/span/div/div[1]/a/div")).click();
        //driver.findElement(By.xpath("//*[@id=\"slot-15\"]/div/div/div[2]/div[3]/div/div[1]/div")).click();
       // driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[1]/span/div/div[1]/a/div")).click();

        boolean elementPresent = driver.findElements(By.id(p.getProperty("QuantityDropdown.Id"))).size() > 0;
        System.out.println("element prresent or not " + elementPresent);
//        if (elementPresent == true) {
//            Select s = new Select(driver.findElement(By.id(p.getProperty("QuantityDropdown.Id"))));
//            s.selectByIndex(0);
//            selectedQty = s.getFirstSelectedOption().getText();
//       }
        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
        driver.findElement(By.id("nav-cart")).click();

            driver.findElement(By.id(p.getProperty("valueInCart.id"))).sendKeys("Mobile");
            driver.findElement(By.id(p.getProperty("valueInCart.id"))).sendKeys(Keys.ENTER);
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
            //actions.keyDown(Keys.CONTROL).release().perform();
            driver.findElement(By.id(p.getProperty("NavigationBar.Id"))).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"hmenu-customer-name\"]/b")).click();
            driver.findElement(By.id("nav-orders")).click();
            driver.switchTo().window("https://www.amazon.in/gp/css/order-history?ref_=nav_orders_first");
            driver.findElement(By.linkText("View orders in 2022")).click();

    }
}
