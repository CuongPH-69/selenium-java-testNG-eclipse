package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Exersice {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By byFirstName = By.xpath("//input[@name='firstName']");
	By byLastName = By.xpath("//input[@name='lastName']");
	Random rand = new Random();
	String employeeId;
	String firstName = "Pham";
	String lastName = "Cuong";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		employeeId = String.valueOf(rand.nextInt(999999));

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
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepTimeSecond(3);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepTimeSecond(2);
		
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepTimeSecond(2);
		
		driver.findElement(byFirstName).sendKeys(firstName);
		driver.findElement(byLastName).sendKeys(lastName);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeId);
		driver.findElement(By.xpath("//p[text()='Create Login Details']//following-sibling::div/label")).click();
		driver.findElement(By.xpath("//label[text()='Username']//parent::div//following-sibling::div/input")).sendKeys("Cuong"+employeeId);
		driver.findElement(By.xpath("//label[text()='Password']//parent::div//following-sibling::div/input")).sendKeys("Cuong@12345");
		driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div//following-sibling::div/input")).sendKeys("Cuong@12345");
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepTimeSecond(7);
		
//		driver.findElement(By.name("firstName")).getAttribute("Value");
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div/input")).getAttribute("value"), employeeId);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepTimeSecond(2);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).sendKeys("1234-5678-9012");
		driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).sendKeys("abc\ndef\nxyz");
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepTimeSecond(3);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepTimeSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).getAttribute("value"), "1234-5678-9012");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).getAttribute("value"), "abc\ndef\nxyz");
		
		driver.findElement(By.cssSelector("i.oxd-userdropdown-icon")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepTimeSecond(3);
		driver.findElement(By.name("username")).sendKeys("Cuong"+employeeId);
		driver.findElement(By.name("password")).sendKeys("Cuong@12345");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepTimeSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).getText(), firstName + " " + lastName);
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepTimeSecond(2);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div/input")).getAttribute("value"), employeeId);
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepTimeSecond(3);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepTimeSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).getAttribute("value"), "1234-5678-9012");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).getAttribute("value"), "abc\ndef\nxyz");
		
	}

	@Test
	public void TC_02() {
		
	}

//	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}