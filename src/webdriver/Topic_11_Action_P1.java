package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Action_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// update

	}

	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void TC_01() {
//		Hàm Action dùng để giả lập các thao tác của người dùng
		driver.get(osName);
//		Các Hàm không truyền tham số thì dùng cho Browser
//		Các hàm có tham số truyền vào dùng cho Element

//		Hàm dùng để click vào 1 Element
		action.click(null);

//		Hàm dùng để click và hold vào 1 Element. Phải kết hợp cùng hàm Release() để thực hiện thả chuột
		action.clickAndHold(null);
		action.release(null);

//		Hàm dùng để kéo thả 
		action.dragAndDrop(null, null);

//		Hàm dùng để nhấn và thả các phím trên bàn phím
		action.keyDown(osName);
		action.keyUp(osName);

//		Hàm dùng để di chuyển đến 1 Element
		action.moveToElement(null);

//		hàm bắt buộc phải nếu muốn sử dụng các hàm trên
		action.perform();

//		Hàm dùng để truyền dữ liệu
		action.sendKeys(null, null);
		
//		Javascript dùng để scroll đến element
		jsExecutor.executeScript(arguments[0].scrollIntoView(true), element);
	}

	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
//	Câu lệnh để setup thời gian bật mod debugger trong tab console: setTimeout(() => {debugger;}, 3000);
//		Khi sử dụng hàm liên quan đến sự kiện chuột và bàn phím thì tuyệt đối không sử dụng trong quá trình run 
//		nếu không sẽ bị xung đột vì mỗi máy chỉ có 1 chuột và bàn phím
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}