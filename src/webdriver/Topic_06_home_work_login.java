package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_home_work_login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By passwordTextBox = By.id("new_password");
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//update

	}
	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("send2")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.findElement(By.id("email")).sendKeys("2433@2343.21314");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-validate-email-email")).isDisplayed());
	}

	@Test
	public void TC_03_Login_With_Password_Less_Than_6_Characters() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).clear();;
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-validate-password-pass")).isDisplayed());		
	}
	
	@Test
	public void TC_04_Login_With_Incorrect_Email_And_Password() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).clear();;
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).isDisplayed());		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}