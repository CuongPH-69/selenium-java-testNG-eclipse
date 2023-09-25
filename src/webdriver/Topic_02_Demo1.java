package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Demo1 {
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
	public void TC_01_Open_Login_Page() {
//		Mở trang web
		driver.get("https://test.map4d.vn/map?camera=16.068500,108.221500,15.00,0.0,0.0,r");
		
//		Truy cập trang đăng nhập
		driver.findElement(By.xpath("//span[contains(text(),'Đăng nhập')]")).click();
	}

	@Test
	public void TC_02_Open_Register_Page() {
		driver.findElement(By.xpath("//div[@id='kc-registration']//a")).click();
	}

	@Test
	public void TC_03_Input_FirstName_And_LastName() {
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Cuong");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Pham");
	}
	
	@Test
	public void TC_04_Back_Login_Page() {
		driver.findElement(By.xpath("//div[@id='kc-form-options']//a")).click();
	}
	
	@Test
	public void TC_05_Login_Fail() {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("cuongph@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='kc-login']")).click();
	}
	
	@Test
	public void TC_06_Login_Pass() {
		driver.findElement(By.xpath("//input[@name='username']")).clear();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cuongph@iotlink.com.vn");
		
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Cuong@91");
		
		driver.findElement(By.xpath("//input[@id='kc-login']")).click();
		try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}