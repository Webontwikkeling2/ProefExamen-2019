package view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAddBike {
	
	private WebDriver driver;
	String url = "http://localhost:8080/";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Applications/Servers/chromedriver_83");
		driver = new ChromeDriver();

		driver.get(url+"bikeAdd.jsp");
	}

	@Test
	public void test_Add_Bike_Without_Price_below_zero(){
		WebElement bikeId = driver.findElement(By.id("itemId"));
		bikeId.clear();
		bikeId.sendKeys("582");

		WebElement bikeBrand = driver.findElement(By.id("brand"));
		bikeBrand.clear();
		bikeBrand.sendKeys("Cube");

		WebElement bikeCategory = driver.findElement(By.id("category"));
		bikeCategory.clear();
		bikeCategory.sendKeys("ROAD");

		WebElement bikeDescription = driver.findElement(By.id("description"));
		bikeDescription.clear();
		bikeDescription.sendKeys("Comfort staat garant door de carbon constructie, de dunne zadelpen van 27.2mm en zijn carbon voorvork. Daarnaast beschikt deze fiets over het zogenoemde Flex Stays principe, hij is dus volumieus waar stijfheid nodig is maar dan weer dunner en slanker waar comfort gegenereerd moet worden. ");

		WebElement bikePrice = driver.findElement(By.id("price"));
		bikePrice.clear();
		bikePrice.sendKeys("-1");

		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();

		Assert.assertEquals("No valid price", driver.findElement(By.className("error")).getText());
	}

	@Test
	public void test_Add_Bike(){
		WebElement bikeId = driver.findElement(By.id("itemId"));
		bikeId.clear();
		bikeId.sendKeys("584");

		WebElement bikeBrand = driver.findElement(By.id("brand"));
		bikeBrand.clear();
		bikeBrand.sendKeys("Cube");

		WebElement bikeCategory = driver.findElement(By.id("category"));
		bikeCategory.clear();
		bikeCategory.sendKeys("ROAD");

		WebElement bikeDescription = driver.findElement(By.id("description"));
		bikeDescription.clear();
		bikeDescription.sendKeys("Comfort staat garant door de carbon constructie, de dunne zadelpen van 27.2mm en zijn carbon voorvork. Daarnaast beschikt deze fiets over het zogenoemde Flex Stays principe, hij is dus volumieus waar stijfheid nodig is maar dan weer dunner en slanker waar comfort gegenereerd moet worden. ");

		WebElement bikePrice = driver.findElement(By.id("price"));
		bikePrice.clear();
		bikePrice.sendKeys("2099");

		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();

		assertEquals("Overview Bikes", driver.findElement(By.id("titel")).getText());
		assertNotNull(driver.findElement(By.id("584")));
	}
	
	@After
	public void clean() {
		driver.quit();
	}


}
