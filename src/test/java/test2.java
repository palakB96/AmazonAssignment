import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class test2 {
    boolean elementPresent;

    WebDriver driver = new ChromeDriver();
    String selectedQty,valueInCart;
    Properties p;
    @BeforeMethod
    public void property() throws IOException {
        p = new Properties();
        FileInputStream FI = new FileInputStream("/home/palakb/IdeaProjects/mavenproject/src/Data.properties");
        p.load(FI);

    }

    @Test()
    public void addToCart() throws NoSuchElementException
    {
        elementPresent = driver.findElement(By.id(p.getProperty("QuantityDropdown.Id"))).isDisplayed();
        if(elementPresent == true) {
            Select s = new Select(driver.findElement(By.id(p.getProperty("QuantityDropdown.Id"))));
            s.selectByIndex(0);
            selectedQty = s.getFirstSelectedOption().getText();
        }
        driver.findElement(By.id(p.getProperty("Add.To.Cart.Button.Id"))).click();
    }

    @Test(priority = 5)
    public void navToCart()
    {
        driver.findElement(By.id(p.getProperty("nav.cart.id"))).click();

        if(elementPresent == true)
        {
            String s = driver.findElement(By.id("a-autoid-0-announce")).getText();
            String[] arr = s.split(":");
            System.out.println(arr.length);
            valueInCart = arr[1];
            System.out.println(valueInCart);
            Assert.assertEquals(selectedQty,valueInCart);

        }}

    @Test(priority = 6)
    public void search()
    {
        driver.findElement(By.id(p.getProperty("valueInCart.id"))).sendKeys("Mobile");
        driver.findElement(By.id(p.getProperty("valueInCart.id"))).sendKeys(Keys.ENTER);

    }

    @Test(priority = 7)
    public void scrollPage() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        actions.keyDown(Keys.CONTROL).release().perform();
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}
