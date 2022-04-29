import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class first {


    public static void main(String args[])
    {
        System.setProperty("webdriver.chrome.driver","/home/palakb/Downloads/chromedriver_linux64/chromedriver");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        driver.findElement(By.cssSelector("a.a-link-normal.as-title-block-right.see-more.truncate-1line")).click();
        driver.findElement(By.xpath("//img[@src='https://m.media-amazon.com/images/I/41y-wyZZ6hL.jpg']")).click();
        Select s = new Select(driver.findElement(By.cssSelector("span.a-button a-button-dropdown a-button-small")));
        s.selectByIndex(0);
        driver.findElement(By.cssSelector("input.a-button-input.attach-dss-atc"));
        driver.findElement(By.cssSelector("a#nav-cart")).click();

        driver.close();

    }

}
