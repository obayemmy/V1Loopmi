package LoginAsCM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class CmmSanity {
	   String driverPath = "C:\\\\SummitechTest\\\\Jumia\\\\driver\\\\chromedriver.exe";
	     WebDriver driver ; 
	
	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver",  driverPath);
		driver = new ChromeDriver();
		driver.get("https://staging-loopmi.firebaseapp.com/account/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String actualUrl = driver.getCurrentUrl();
		System.out.println("the current url is :" + actualUrl);
		System.out.println("launch browser and open url");
	}
	
	@Test
	public void VerifyPageTitle() {
		String title = driver.getTitle();
		System.out.println("the page title is " + title);
		Assert.assertEquals(title, "LoopmiWeb");
		boolean emailField = driver.findElement(By.id("email")).isDisplayed();
		Assert.assertTrue(emailField);
		driver.findElement(By.id("email")).sendKeys("olugbenga@summitech.ng");
		
	}
		
	@Test
	public void login_As_Super_Admin () throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("olugbenga@summitech.ng");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("/html/body/app-root/app-account/div/div/div/main/div/app-login/div/div[2]/form/button")).click();
		Thread.sleep(7000);
		String expectedErrorMsg = "Cannot log in, check your email and/or password.";
		String actualErrorMsg ;
		actualErrorMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals(expectedErrorMsg, actualErrorMsg);
			 System.out.println("the error message is " + actualErrorMsg);
		
	
	   
		
		//Assert.assertEquals("Cannot log in, check your email and/or password.", actualMsg.contains(expectMsg));
	   
	    System.out.println("login as Admin executed successfully");	
	}
	
	@Test(priority=3)
	public void login_As_Admin() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("qasummitech@gmail.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("/html/body/app-root/app-account/div/div/div/main/div/app-login/div/div[2]/form/button")).click();
		Thread.sleep(4000);
		System.out.println("login as super admin executed successfully");
		
	}
	
	@Test(priority=2)
	public void login_As_Community_Manager() {
		driver.findElement(By.id("email")).sendKeys("olugbenga.oje@summitech.ng");
		driver.findElement(By.id("password")).sendKeys("summitech1");
		driver.findElement(By.xpath("/html/body/app-root/app-account/div/div/div/main/div/app-login/div/div[2]/form/button")).click();
		System.out.println("login as community manager");
	}
	@Test(priority=1)
	public void verifyLogo() {
		boolean logo = driver.findElement(By.xpath(" //img[@class='header__logo']")).isDisplayed();
		Assert.assertTrue(logo);
		
	}
	@AfterMethod
	public void closeBrowser () {
		driver.close();
		System.out.println("close browser");
	}
}
