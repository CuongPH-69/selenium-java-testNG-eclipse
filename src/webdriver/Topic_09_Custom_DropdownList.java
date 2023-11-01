package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_DropdownList {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
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
	
	public void selectspeed(String dropdown, String locatorSpeed, String textOption) {
		driver.findElement(By.xpath(dropdown)).click();
//		Đợi loading toàn bộ option trong dropdownlist (Bao gồm cả những option không nằm trong phạm vi nhìn thấy được)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorSpeed)));
//		Khai báo 1 biến speedDropdownList thuộc kiểu List<WebElement> dùng để chứa toàn bộ option
		List<WebElement> speedDropdownList = driver.findElements(By.xpath(locatorSpeed));
		
		for (WebElement speedOption : speedDropdownList) {
			String speedName = speedOption.getText();
			System.out.println(speedName);
			if (speedName.trim().equals(textOption)) {
				speedOption.click();
				break;
			}
		}
	}
	
	public void inputKeywordAndSelectOption (String parent, String locatorCountry, String countryName ) {
		driver.findElement(By.xpath(parent)).clear();
		driver.findElement(By.xpath(parent)).sendKeys(countryName);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(parent)));
		List<WebElement> country = driver.findElements(By.xpath(locatorCountry));
		for (WebElement webElement : country) {
			String selectCountry = String.valueOf(webElement.getText());
			if (selectCountry.trim().equals(countryName)) {
				webElement.click();
				break;
			}
		}
	}
	
//	@Test
	public void TC_01_Jquery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectspeed("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Faster");
		Assert.assertEquals(driver.findElement(By.id("speed-button")).getText(), "Faster");
		
		selectspeed("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Slower");
		Assert.assertEquals(driver.findElement(By.id("speed-button")).getText(), "Slower");
	}

//	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectspeed("//div[@role='listbox']", "//div[@role='option']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='listbox']")).getText(), "Matt");
		sleepTimeSecond(2);
		selectspeed("//div[@role='listbox']", "//div[@role='option']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='listbox']")).getText(), "Christian");
	}

//	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectspeed("//div[@class='btn-group']/li", "//ul[@class='dropdown-menu']/li", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "Second Option");
		sleepTimeSecond(2);
		selectspeed("//div[@class='btn-group']/li", "//ul[@class='dropdown-menu']/li", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "First Option");
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		inputKeywordAndSelectOption("//div[@role='combobox']/input[@class='search']","//div[@role='listbox']/div[@role='option']","Angola");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='combobox']/div[@role='alert']")).getText(), "Angola");
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}