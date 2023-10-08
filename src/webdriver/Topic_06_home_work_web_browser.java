package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_home_work_web_browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	
//	@Test
	public void TC_01_verify_url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTimeSecond(1);		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		sleepTimeSecond(1);		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

//	@Test
	public void TC_02_verify_title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		driver.navigate().back();
		sleepTimeSecond(1);		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.navigate().forward();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

//	@Test
	public void TC_03_navigation_function() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTimeSecond(1);
		driver.navigate().back();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/");
		driver.navigate().forward();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.getTitle(), "Customer Login");		
	}
	@Test
	public void TC_04_get_pageSourceCode() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTimeSecond(1);
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		sleepTimeSecond(1);
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}