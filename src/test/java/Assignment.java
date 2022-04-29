import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.Set;

public class Assignment {

    public static void main(String args[]) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/palakb/Downloads/chromedriver_linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Payment Option");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("img[alt*=\"Payment settings on Amazon\"]")).click();
        driver.findElement(By.id("ap_email")).sendKeys("palak.bhansal@zemosolabs.com");
        driver.findElement(By.cssSelector("#continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("Pass@123");
        driver.findElement(By.id("auth-signin-button")).click();
        Thread.sleep(3000);
        Actions act =  new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//span[text()='Add a credit or debit card']"))).click().perform();
        Thread.sleep(8000);
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                WebElement text = driver.findElement(By.xpath("*//input[@id='pp-BYJvCo-15']"));
                text.sendKeys("5555555555554444");
                System.out.println("Heading of child window is " + text.getText());
            }
    }

}}
