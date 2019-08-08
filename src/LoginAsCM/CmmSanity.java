package LoginAsCM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class CmmSanity {
	    String driverPath = "C:\\SummitechTest\\Konga_Project\\driver\\chromedriver.exe";
	     WebDriver driver ; 
	
	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver",  driverPath);
		driver = new ChromeDriver();
		driver.get("https://staging-loopmi.firebaseapp.com/account/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
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
		Thread.sleep(5000);
		/* String expectMsg = "Cannot log in, check your email and/or password.";
		String actualMsg = driver.findElement(By.cssSelector("div.container-fluid.main-container div.row.justify-content-md-center div.col.col-lg-4.content main.main-section div.section-form div.card.section-form__box div.card-body.section-form__box-body form.ng-dirty.ng-touched.ng-valid p:nth-child(1) ngb-alert:nth-child(1) > div.alert.alert-danger")).getText();
	    Assert.assertEquals("Cannot log in, check your email and/or password.", actualMsg.contains(expectMsg));*/
	   
	    System.out.println("login as Admin executed successfully");	
	}
	
	@Test(priority=3)
	public void login_As_Admin() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("qasummitech@gmail.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("/html/body/app-root/app-account/div/div/div/main/div/app-login/div/div[2]/form/button")).click();
		Thread.sleep(3000);
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
