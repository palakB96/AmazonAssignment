import org.openqa.selenium.*;
import  org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Amazon {
   static WebDriver driver;
    Properties p;
    String selectedQty,valueInCart;
    WebElement e;
    boolean elementPresent = false;

    @Parameters({"Browser"})
    @Test(priority = 0)
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
        driver.findElement(By.linkText("Today's Deals")).click();
    }

    @Test(priority = 3)
    public void selectThirdDeal() throws NoSuchElementException
    {
        driver.findElement(By.xpath(p.getProperty("ThirdDeal.xpath"))).click();
        driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[1]/span/div/div[1]/a/div")).click();

    }
    @BeforeMethod
    public void property() throws IOException {
        p = new Properties();
        FileInputStream FI = new FileInputStream("/home/palakb/IdeaProjects/mavenproject/src/Data.properties");
        p.load(FI);

    }

    @Test(priority = 4)
    public void addToCart() throws NoSuchElementException
    {
//        elementPresent = driver.findElements(By.id(p.getProperty("QuantityDropdown.Id"))).size() > 0;
//        System.out.println("element prresent or not " +elementPresent);
//        if(elementPresent == true) {
//            Select s = new Select(driver.findElement(By.id(p.getProperty("QuantityDropdown.Id"))));
//            s.selectByIndex(0);
//            selectedQty = s.getFirstSelectedOption().getText();
//        }
        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

    }

    @Test(priority = 5)
    public void navToCart()
    {
        driver.findElement(By.id(p.getProperty("nav.cart.id"))).click();

        if(elementPresent == true)
        {
            String s = driver.findElement(By.id("a-autoid-0-announce")).getText();
            String[] arr = s.split(":");
            int arrLength = arr.length;
                valueInCart = arr[1];
                System.out.println(valueInCart);
                Assert.assertEquals(selectedQty, valueInCart);

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
        actions.keyUp(Keys.CONTROL).sendKeys(Keys.UP).perform();
        actions.keyUp(Keys.CONTROL).release().perform();
    }

    @Test(priority = 8)
    public void navigationBar() throws InterruptedException {
        Actions act =  new Actions(driver);
        act.moveToElement(driver.findElement(By.id(p.getProperty("NavigationBar.Id")))).click().perform();
        driver.navigate().back();
    }

    @Test(priority = 1)
    public void signInDetails() throws InterruptedException
    {
        driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).click();
        WebElement e1 =  driver.findElement(By.cssSelector("#ap_email"));
        e1.clear();
        e1.sendKeys("palak.bhansal@zemosolabs.com");
        driver.findElement(By.cssSelector("#continue")).click();
        WebElement e2 = driver.findElement(By.id("ap_password"));
        e2.clear();
        e2.sendKeys("Pass@123");
        driver.findElement(By.id("auth-signin-button")).click();

    }

    @Test(priority = 9)
    public void ordersDetails()
    {
        driver.findElement(By.id("nav-orders")).click();
        driver.findElement(By.linkText("View orders in 2022")).click();
    }

    @Test(priority = 10)
    public void payment()
    {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Payment Option");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("img[alt*=\"Payment settings on Amazon\"]")).click();

    }
    @Test(priority = 11)
    public void addCardDetails() throws InterruptedException {
        Actions act =  new Actions(driver);
        WebElement addCreditCardDetails = driver.findElement(By.xpath("//span[text()='Add a credit or debit card']"));
        String addCredit = addCreditCardDetails.getText();
        System.out.println(addCredit);
        Assert.assertEquals(addCredit,"Add a credit or debit card");
    }

    @Test(priority = 12)
    public void addressDetails()
    {
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.cssSelector("img[alt*=\"Your Addresses\"]")).click();
        driver.findElement(By.id("ya-myab-plus-address-icon")).click();
    }

    @Test(priority = 13)
    public void adressFormDetails()
    {
        driver.findElement(By.id("address-ui-widgets-enterAddressFullName")).sendKeys("Palak");
        driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber")).sendKeys("1234567890");
        driver.findElement(By.id("address-ui-widgets-enterAddressLine1")).sendKeys("#31");
        driver.findElement(By.id("address-ui-widgets-enterAddressLine2")).sendKeys("Ludhiana");
        driver.findElement(By.id("address-ui-widgets-use-as-my-default")).click();
//        Select s = new Select(driver.findElement(By.name("address-ui-widgets-addr-details-address-type-and-business-hours")));
//        s.selectByValue("RES");
//        driver.findElement(By.name("address-ui-widgets-addr-details-address-type-and-business-hours")).sendKeys(Keys.ENTER);

        WebElement addAddressBtn = driver.findElement(By.id("address-ui-widgets-form-submit-button-announce"));
        Actions actions = new Actions(driver);
        actions.moveToElement(addAddressBtn).click().build().perform();

    }
}
