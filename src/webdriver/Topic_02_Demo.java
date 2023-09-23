package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Demo {
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

	@Test
	public void TC_01_Id() {
//		Mở trang web
		driver.get("https://test.map4d.vn/map?camera=16.068500,108.221500,15.00,0.0,0.0,r");
		
//		Truy cập trang đăng nhập
		driver.findElement(By.xpath("//span[contains(text(),'Đăng nhập')]")).click();
		
//		Click vào trang đăng ký
		driver.findElement(By.xpath("//a[@tabindex='6']")).click();
		
//		Thao tác với field FirtName
		driver.findElement(By.id("firstName")).sendKeys("Cuong");
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("kc-dropdown")).click();
		
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("lastName")).sendKeys("Cuong");
	}
	
	@Test
	public void TC_04_TagName() {
		driver.findElements(By.tagName("input")).size();
	}
	
	@Test
	public void TC_05_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();
	}
	
	@Test
	public void TC_06_LinkText() {
		driver.findElement(By.linkText("REGISTER")).click();
	}
	
	@Test
	public void TC_07_Css() {
		driver.findElement(By.cssSelector("input#email")).sendKeys("cuongph@iotlink.com.vn");
		driver.findElement(By.cssSelector("input[name='email']")).clear();
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("cuongph@gmail.com");
	}
	
	@Test
	public void TC_08_XPath() {
		driver.findElement(By.xpath("//input[@id='user.attributes.phone']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@name='user.attributes.phone']")).clear();
		driver.findElement(By.xpath("//input[@name='user.attributes.phone']")).sendKeys("555555");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}