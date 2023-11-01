package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Exercise_handle_dropdownlist {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, email, password, date, month, year, country, personRoleName, testFrameWorkName;
	int numberOfDay, numberOfMonth, numberOfYear; 
	Random rand = new Random();
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
		firstName = "Pan";
		lastName = "Dora";
		email = "pandora"+String.valueOf(rand.nextInt(99))+"@gmail.com";
		password = "P@ndora123";
		date = "1";
		month = "May";
		year = "1980";
		country = "Vietnam";
		personRoleName = "QA Manager / Director";
		testFrameWorkName = "Selenium";
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
		driver.get("https://demo.nopcommerce.com/register");
		sleepTimeSecond(1);

		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

//		numberOfDay = String.value;
//		Select totalDate = new Select(driver.findElement(By.name("DateOfBirthDay")));
//		List<WebElement> totalDay = totalDate.getOptions();
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(date);
		numberOfDay = new Select(driver.findElement(By.name("DateOfBirthDay"))).getOptions().size();
		System.out.println("Total day :" + numberOfDay);
		
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		numberOfMonth = new Select(driver.findElement(By.name("DateOfBirthMonth"))).getOptions().size();
		System.out.println("Total month :" + numberOfMonth);
		
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		numberOfYear = new Select(driver.findElement(By.name("DateOfBirthYear"))).getOptions().size();
		System.out.println("Total year :" + numberOfYear);

		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		sleepTimeSecond(1);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("div.buttons a")).click();
		sleepTimeSecond(1);
		
		driver.findElement(By.xpath("//div[@class='header-links-wrapper']//a[text()='Log in']")).click();
		sleepTimeSecond(1);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//div[@class='buttons']//button[text()='Log in']")).click();
		sleepTimeSecond(1);
		
		driver.findElement(By.xpath("//div[@class='header-links-wrapper']//a[text()='My account']")).click();
		sleepTimeSecond(1);
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), date);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		
	}

//	@Test
	public void TC_02() {
		driver.get("https://rode.com/en/support/where-to-buy");
		Select selectCountry = new Select(driver.findElement(By.id("country")));
		
		Assert.assertFalse(selectCountry.isMultiple());
		selectCountry.selectByVisibleText(country);
		Assert.assertEquals(selectCountry.getFirstSelectedOption().getText(), country);
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		sleepTimeSecond(2);
		
		List<WebElement> dealerName = driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div//h4"));

        // Lặp qua danh sách và in ra tên của từng phần tử h4
        for (WebElement printDealerName : dealerName) {
            System.out.println(printDealerName.getText());
        }
	}

	@Test
	public void TC_03() {
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		sleepTimeSecond(2);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		Select personRole = new Select(driver.findElement(By.xpath("//div[@class='mktoFieldWrap mktoRequiredField']//select[@id='Person_Role__c']")));
		personRole.selectByVisibleText(personRoleName);
		Assert.assertEquals(personRole.getFirstSelectedOption().getText(), personRoleName);
		
		driver.findElement(By.id("Company")).sendKeys("IOT");
		
		Select testFrameWork = new Select(driver.findElement(By.id("Test_Framework__c")));
		testFrameWork.selectByVisibleText(testFrameWorkName);
		Assert.assertEquals(testFrameWork.getFirstSelectedOption().getText(), testFrameWorkName);
		
		Select selectCountry = new Select(driver.findElement(By.id("Self_Report_Country__c")));
		selectCountry.selectByVisibleText(country);
		Assert.assertEquals(selectCountry.getFirstSelectedOption().getText(), country);
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}