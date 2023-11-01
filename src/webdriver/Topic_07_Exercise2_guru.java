package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Exercise2_guru {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String username, password, customerName, dobMonth, dobDate, dobYear, address, city, state, pin, mobileNumber, email, gender, customerId;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// update
		username = "mngr533862";
		password = "Ypanube";
		customerName = "Tester";
		dobDate = "06";
		dobMonth = "09";
		dobYear = "1991";
		address = "Viet Name";
		city = "Viet Nam";
		state = "Alaska";
		pin = "123456";
		mobileNumber = "123456789";
		email = "tester@gmail.com";
	}

	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01() {
		driver.get("https://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		sleepTimeSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : mngr533862']")).getText().contains(username));

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepTimeSecond(2);
		driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.name("dob")).click();
		driver.findElement(By.name("dob")).sendKeys(dobDate+dobMonth+dobYear);
		driver.findElement(By.name("addr")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(mobileNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		gender = driver.findElement(By.xpath("//input[@name='rad1' and @value='m']")).getText();
		driver.findElement(By.name("sub")).click();
		sleepTimeSecond(2);
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[normalize-space()='Customer Registered Successfully!!!']")).getText(), "Customer Registered Successfully!!!");
		customerId = driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText();
		//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText(), username);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), username);
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText().contains(gender));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='//td[text()='Birthdate']//following-sibling::td']//following-sibling::td")).getText(), dobYear+"-"+dobMonth+"-"+dobDate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), email);
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		sleepTimeSecond(2);
		
		driver.findElement(By.name("cusid")).sendKeys(customerId);
		driver.findElement(By.name("AccSubmit")).click();
		sleepTimeSecond(2);
		
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), address);
	}

//	@Test
	public void TC_02() {

	}

//	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}