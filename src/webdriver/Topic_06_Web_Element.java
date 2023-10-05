package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
		driver.get("http://test.map4d.vn");
		WebElement element = driver.findElement(By.xpath(""));
//		Các hàm sử dụng cùng với webElement
//		Hàm dùng để xóa dữ liệu của field: textbox/ textArea/ dropdown (editable)
		element.clear();
		
//		Hàm dùng để truyền dữ liệu vào field: textbox/ textArea/ dropdown (editable)
		element.sendKeys("");
		
//		Hàm dùng để click vào field: checkbox/ radio button/ link/ button/ ...
		element.click();
		
//		Hàm dùng để get attribute: Dùng để lấy giá trị của attribute
		element.getAttribute("");
		
//		Hàm dùng để lấy giá trị trong Css:
		element.getCssValue("");
		
//		Test GUI
//		Get vị trí location so với web (Bên ngoài)
		element.getLocation();
		
//		Get kích thước của browser
		element.getSize();
		
//		Get location + size
		element.getRect();
		
//		Chụp hình khi testcase bị fail
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
//		Get tên thẻ của html sau đó truyền vào cho 1 locator khác
		element.getTagName();
		String tagname = driver.findElement(By.id("email")).getTagName();
		driver.findElement(By.xpath("//"+ tagname +"[@id='email']"));
		
//		Lấy text
		element.getText();
		
//		Khi nào dùng getText khi nào dùng Attribute
//		Khi muốn lấy text nằm bên ngoài thẻ thì dùng getText
//		Khi muốn lấy text nằm bên trong thẻ thì dùng Attribute
		
//		Dùng để verify element đã hiển thị hoặc không
//		Phạm vi là dùng cho tất cả element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
//		Dùng để verify xem element cho phép thao tác hay không
//		Phạm vi là dùng cho tất cả element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
//		Dùng để verify xem element đã được chọn hay chưa
//		Phạm vi: Checkbox/ Radio button
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
//		Dùng để submit form
//		Tương ứng với hành vi người dùng là nhấn phím "Enter"
		element.submit();
		
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