package LoginAsCM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class newLogin {
	WebDriver driver;
	String driverLocation = "C:\\SummitechTest\\Jumia\\driver\\chromedriver.exe";
	
	@BeforeTest
	public void openBrowser() {
		System.out.println("Runs before  every test");
		System.setProperty("webdriver.chrome.driver", driverLocation);
		driver = new  ChromeDriver();
		driver.get("https://staging-loopmi.firebaseapp.com/account/login");
		driver.manage().window().maximize();
		String pageTitle = driver.getTitle();
		System.out.println("the page title is :" + pageTitle);
	}
	@Test(priority =1)
	public void invalidlogin() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("olugbenga@summitech.ng");
		driver.findElement(By.id("password")).sendKeys("password11");
		driver.findElement(By.xpath("/html/body/app-root/app-account/div/div/div/main/div/app-login/div/div[2]/form/button")).click();
		Thread.sleep(6000);
		String expectedErrorMsg = "Cannot log in, check your email and/or password.";
		String actualErrorMsg = "Cannot log in, check your email and/or password.";
		String errorMsg;
		errorMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals(expectedErrorMsg, errorMsg);
			 System.out.println("the error message is " + errorMsg);
		System.out.println("invaLogin successfuly");
	}
	@Test(priority =2)
	public void validLogin() throws InterruptedException {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("olugbenga.oje@summitech.ng");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("summitech1");
		driver.findElement(By.xpath("/html/body/app-root/app-account/div/div/div/main/div/app-login/div/div[2]/form/button")).click();
		Thread.sleep(8000);
		
		
		System.out.println("valid login successful");
	}
	@Test(priority =3)
	public void createBulletin() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/app-base/div/div/div/div/app-dashboard/div[3]/div[2]/a/div/div/h5")).click();
		String bulletinText = null;
				if(driver.getPageSource().contains(bulletinText)) {
					System.out.println("the bulletin text is " + bulletinText);
					}else{
					System.out.println("tezt not found");
		}
		driver.findElement(By.name("query")).sendKeys("zee world");
		Thread.sleep(3000);
		System.out.println("create bulletin successful"); 
	}
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		driver.navigate().back();
		Thread.sleep(3000);
		driver.close();
		System.out.println("runs at the end of all  test");
	}
}
