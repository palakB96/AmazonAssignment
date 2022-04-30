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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Amazon {
   static WebDriver driver;
    Properties p;
    String selectedQty,valueInCart;
    WebElement e;
    boolean elementPresent = false;

    @BeforeMethod
    public void property() throws IOException {
        p = new Properties();
        FileInputStream FI = new FileInputStream("/home/palakb/IdeaProjects/mavenproject/src/Data.properties");
        p.load(FI);

    }
    @Parameters({"Browser"})
    @Test(priority = 0)
    public void launchBrowser()
    {
        System.setProperty("webdriver.chrome.driver","/home/palakb/Downloads/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(p.getProperty("Url"));
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
    @Test(priority = 2)
    public void todayDeal()
    {
        driver.findElement(By.linkText("Today's Deals")).click();
    }

    @Test(priority = 3)
    public void selectDeal() throws NoSuchElementException
    {
        driver.findElement(By.xpath(p.getProperty("ThirdDeal.xpath"))).click();
        boolean elementPresent = driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[1]/span/div/div[1]/a/div")).isDisplayed();
        if(elementPresent == true)
        driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[1]/span/div/div[1]/a/div")).click();

    }
    @Test(priority = 4)
    public void addToCart() throws NoSuchElementException
    {
            Select s = new Select(driver.findElement(By.cssSelector("#selectQuantity #quantity")));
            s.selectByIndex(0);
            selectedQty = s.getFirstSelectedOption().getText();
            System.out.println(selectedQty);
            driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
    }

    @Test(priority = 5)
    public void navToCart()
    {
        driver.findElement(By.id(p.getProperty("nav.cart.id"))).click();
        //boolean elementPresent = driver.findElement(By.id("a-autoid-0-announce")).isDisplayed();
        if(elementPresent == true)
        {
            String s = driver.findElement(By.id("a-autoid-0-announce")).getText();
            String[] arr = s.split(":");
            System.out.println(Arrays.toString(arr));
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



    @Test(priority = 9)
    public void ordersDetails()
    {
        driver.findElement(By.id("nav-orders")).click();
        driver.findElement(By.linkText("View orders in 2022")).click();
    }

    @Test(priority = 10)
    public void payment()
    {
        driver.findElement(By.id(p.getProperty("SearchBar.ID"))).sendKeys("Payment Option");
        driver.findElement(By.id(p.getProperty("SearchBar.ID"))).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(p.getProperty("PaymentOptions.CSS"))).click();

    }
    @Test(priority = 11)
    public void addCardDetails() throws InterruptedException {
        Actions act =  new Actions(driver);
        WebElement addCreditCardDetails = driver.findElement(By.xpath(p.getProperty("addCreditCardDetailsBtn")));
        String addCredit = addCreditCardDetails.getText();
        System.out.println(addCredit);
        Assert.assertEquals(addCredit,p.getProperty("TextOnAddCardDetailsBtn"));
    }

    @Test(priority = 12)
    public void navToAddressPage()
    {
        driver.findElement(By.id(p.getProperty("NavToAccountListLink"))).click();
        driver.findElement(By.cssSelector("img[alt*=\"Your Addresses\"]")).click();
        driver.findElement(By.id(p.getProperty("PlusIconInAddressPage"))).click();
    }

    @Test(dependsOnMethods = {"navToAddressPage"})
    public void addressFormDetails()
    {
        driver.findElement(By.id(p.getProperty("FullName.ID"))).sendKeys("Palak");
        driver.findElement(By.id(p.getProperty("MobileNumber.ID"))).sendKeys("1234567890");
        WebElement pinCode = driver.findElement(By.id(p.getProperty("PinCode.ID")));
        pinCode.clear();
        pinCode.sendKeys("160019");
        driver.findElement(By.id(p.getProperty("Address1.ID"))).sendKeys("#3142,Sector 27 D");
        driver.findElement(By.id(p.getProperty("Address2.ID"))).sendKeys("Ludhiana");
        driver.findElement(By.id(p.getProperty("MakeDefaultAddressCheckbox"))).click();
        WebElement addAddressBtn = driver.findElement(By.id(p.getProperty("AddAddressBtn.ID")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addAddressBtn).click().build().perform();

    }
    @Test(dependsOnMethods = {"addressFormDetails"})
    public void verifyAddress()
    {
        String actualAddress = driver.findElement(By.id(p.getProperty("VerifyAddress.ID"))).getText();
        System.out.println(actualAddress);
        String expectedAddress = "Default:  \n" +
                "palak\n" +
                "#3142,Sector 27 D\n" +
                "Ludhiana\n" +
                "CHANDIGARH, CHANDIGARH 160019\n" +
                "India\n" +
                "Phone number: \u202A1234567890\u202C\n" +
                "Add delivery instructions";
        Assert.assertEquals(actualAddress,expectedAddress);
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}
