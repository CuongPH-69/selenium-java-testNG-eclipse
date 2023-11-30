package webdriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_User_Interaction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
//		Code turn off notification in Firefox
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(new FirefoxProfile());
//		options.addPreference("dom.webnotifications.enabled",false);
//		driver = new FirefoxDriver();

//		Code turn off notification in Chrome
		Map<String, Integer> prefs = new HashMap<String,Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs",prefs);
		driver = new ChromeDriver(options);
		
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void sleepTimeSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepTimeSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

//	@Test
	public void TC_02_Hover_And_Click() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']")))
				.perform();
		sleepTimeSecond(2);
		action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']")))
				.perform();
		sleepTimeSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
	}

//	@Test
	public void TC_03() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepTimeSecond(1);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']"))).perform();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//span")).getText(),"FOREIGN BOOKS");
		action.moveToElement(driver.findElement(By.xpath("//span[text()='FOREIGN BOOKS']"))).perform();
		sleepTimeSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//span")).getText(),"FOREIGN BOOKS");
	}
	
//	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> chooseNumber = driver.findElements(By.cssSelector("li.ui-selectee"));
		action.clickAndHold(chooseNumber.get(0)).moveToElement(chooseNumber.get(7)).release().perform();
		sleepTimeSecond(2);
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 8);
	}
	
//	@Test
	public void TC_05_Click_And_Choose() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> chooseNumber = driver.findElements(By.cssSelector("li.ui-selectee"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(chooseNumber.get(0))
		.click(chooseNumber.get(3))
		.click(chooseNumber.get(7))
		.perform();
		action.keyUp(Keys.CONTROL).perform();
		sleepTimeSecond(2);
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 3);		
	}
	
//	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertFalse(driver.findElement(By.id("demo")).isDisplayed());
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepTimeSecond(2);
		Assert.assertTrue(driver.findElement(By.id("demo")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");
		
	}
	
	@Test
	public void TC_07_Right_Click() {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-delete"))).perform();
		sleepTimeSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete.context-menu-hover.context-menu-visible")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}