package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Practise_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//update

	}

	@Test
	public void TC_01_Open_Login_Page() {
		driver.get("https://test.map4d.vn/map?camera=16.068500,108.221500,15.00,0.0,0.0,r");
		driver.findElement(By.className("MuiButton-label")).click();
	}

	@Test
	public void TC_02_Login_Fail_With_Empty_Data() {
		driver.findElement(By.id("kc-login")).click();
		Assert.assertEquals(driver.findElement(By.className("kc-feedback-text")).getText(), "Invalid username or password.");
	}

	@Test
	public void TC_03_Login_Fail_With_Invalid_Email() {
		driver.findElement(By.id("username")).sendKeys("123@abc.com");
		driver.findElement(By.id("password")).sendKeys("Cuong@91");
		driver.findElement(By.id("kc-login")).click();
		Assert.assertEquals(driver.findElement(By.className("kc-feedback-text")).getText(), "Invalid username or password.");
	}
	
	@Test
	public void TC_04_Login_Fail_With_Invalid_Password() {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("cuongph@iotlink.com.vn");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Cuong@19");
		driver.findElement(By.id("kc-login")).click();
		Assert.assertEquals(driver.findElement(By.className("kc-feedback-text")).getText(), "Invalid username or password.");
	}
	
	@Test
	public void TC_05_Login_Success() {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("cuongph@iotlink.com.vn");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Cuong@91");
		driver.findElement(By.id("kc-login")).click();
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}