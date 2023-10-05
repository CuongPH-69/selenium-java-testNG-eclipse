package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
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
	public void TC_01() {
//		Nếu đang mở tab >= 2 thì sẽ đóng tab/window đang được focus vào
//		Nếu chỉ mở duy nhất 1 tab thì sẽ đóng browser ~ tương tự như quit
		driver.close();
		
//		Dùng để đóng browser
		driver.quit();
		
//		Dùng để tìm 1 element
		driver.findElement(By.id("username"));
		
//		Dùng để tìm nhiều elements
//		Hàm findElements sẽ trả về là kiểu webElement nên phải khai báo biến webElements để chứa dữ liệu trả về
		List<WebElement> abc = driver.findElements(By.id("Username"));
		
//		Mở 1 trang web
		driver.get("");
		
//		Trả về URL của page hiện tại
		driver.getCurrentUrl();
		
//		Trả về source code của page hiện tại
		driver.getPageSource();
		
//		Verify tương đối
		Assert.assertTrue(driver.getPageSource().contains(""));
		
//		Trả về title của page hiện tạị
		driver.getTitle();
		
//		Lấy ra ID của tab/ window đang được focus
		driver.getWindowHandle();
		
//		Lấy ra tất cả ID của tab/ window đang được mở trong browser
		Set<String> windowID = driver.getWindowHandles();

//		Cookie/ cache
		Options otp = driver.manage();
		
//		Login thành công -> Lưu lại
		otp.getCookies();
		
//		Testcase khác -> set cookie vào lại -> Không câng login
		otp.logs();
		
//		Set timeout
		Timeouts time = otp.timeouts();
		
//		Chờ element xuất hiện trong bao nhiêu đơn vị thời gian
		time.implicitlyWait(5, TimeUnit.SECONDS); //chờ xuất hiện trong 5s = 5000ms = 5000000 micro seconds
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
//		Chờ page load trong bao nhiêu đơn vị thời gian
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
//		Chờ thực thi script trong bao nhiêu đơn vị thời gian
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
//		Window
		Window xyz = otp.window();
		
//		Mở full màn hình (Ẩn các header and fo
		xyz.fullscreen();
		
//		Mở maximum màn hình 
		xyz.maximize();
		
//		Set vị trí hiển thị của trình duyệt
		xyz.setPosition(null);
		
//		Set kích thước của browser
		xyz.setSize(null);
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		
//		Hàm to sẽ tương tự như hàm get nhưng driver.navigate.to sẽ hỗ trợ tốt cho các navigation hơn driver.get
		nav.to(osName);
		
		TargetLocator tar = driver.switchTo();
		tar.frame("");
		tar.window("");
		tar.alert();
		
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