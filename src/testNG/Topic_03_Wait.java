package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Wait {
	WebDriver driver;
	WebDriverWait explicitWait; // explicitWait là Wait tường minh
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
//		Không khởi tạo explicitWait trước driver bởi vì khi khởi tạo cần phải có Driver
//		Nếu khởi tạo trước Driver thì không thể sử dụng
//		explicitWait = new 
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //ImplicitWait là Wait ngầm định
		explicitWait = new WebDriverWait(driver, 30); // Mặc định là giây s
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
		driver.get("https://test.map4d.vn");
//		presenceOfAllElementsLocatedBy áp dụng cho toàn bộ Element trong phạm vi toàn bộ DOM (Nghĩa là load toàn bộ)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("")));
		
//		presenceOfElementLocated Chỉ áp dụng cho 1 Element
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
		
//		visibilityOfAllElements áp dụng cho toàn bộ Element trong phạm vi nhìn thấy được
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(null));
	}

	@Test
	public void TC_02() {
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}