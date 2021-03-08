package com.AutomationScript.sample;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;
public class Automate extends BaseUI {
	int itemCount = 0;
	String searchPageId, itemPageId;

@Parameters("browser")
	@Test(priority=1)
	// Search for hospital with specified location
	public void searchForHospital(String browser) {

		openBrowserAndNavigateToPracto(browser);
		getElement("searchLocation_xpath").click();
		clickElement("clearSearchLocationButton_xpath");
		enterText("searchLocation_xpath", "Bangalore");
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(prop.getProperty("bangaloreLocationButton_xpath"))));
		clickElement("bangaloreLocationButton_xpath");
		enterText("searchBox_xpath", "Hospital");
		new WebDriverWait(driver, 30).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("hospitalButton_xpath"))));
		clickElement("hospitalButton_xpath");

		clickElement("open24X7CB_xpath");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickElement("allFilters_xpath");
		clickElement("hasParkingCB_xpath");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Displaying the search results where rating greater than 3.5
		int i = 0;
		float[] ratingsFloat = new float[10];
		List<WebElement> ratings = getElements("ratings_xpath");
		Iterator<WebElement> rItr = ratings.iterator();
		while (rItr.hasNext()) {
			WebElement ob = rItr.next();
			ratingsFloat[i] = Float.parseFloat(ob.getText());
			i++;
		}

		List<WebElement> hospitalNames = getElements("hospitalNames_xpath");
		Iterator<WebElement> hnItr = hospitalNames.iterator();
		i=0;
		while (hnItr.hasNext()) {
			WebElement ob = hnItr.next();
				if (ratingsFloat[i] > 3.5) {
					System.out.println(ob.getText());
				}
				i++;
		}
		
		closeBrowser();
	}

//Displaying Topcities names on diagnostics page

@Parameters("browser")
@Test(priority=2)

public void TopCities(String browser)
{
openBrowserAndNavigateToPracto(browser);
clickElement("diagnostic_xpath");

List<WebElement> topCity = (List<WebElement>) getElements("city_xpath");
for(WebElement city:topCity)
{
	System.out.println(city.getText());
}
driver.navigate().back();
closeBrowser(); 
}

//Filling the invalid details on corporate wellness and capturing the alert message 
@Parameters("browser")
	@Test(priority=3)
	public void CaptureAlert(String browser) throws IOException
	
	{
		openBrowserAndNavigateToPracto(browser);
		
		clickElement("Provider_xpath");
		
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.visibilityOfElementLocated(By.partialLinkText(prop.getProperty("co_partialLinkText"))));
		clickElement("co_partialLinkText");
		
		Set <String> windowIDs =driver.getWindowHandles();
		Iterator <String> itr = windowIDs.iterator();
		String mainpage=itr.next();
		String nextpage=itr.next();
		driver.switchTo().window(nextpage);
		
		enterText("name_id", "uytr4r566");
		enterText("org_id", "ere3332");
		enterText("offemail_id","wwwweeee2222");
		enterText("offphn_id","rree2222666");
		clickElement("submit_id");
		
		 /* WebDriverWait capturess = new WebDriverWait(driver, 50);

			capturess.until(ExpectedConditions.alertIsPresent());*/
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    Alert alt = driver.switchTo().alert();	   
	System.out.println(alt.getText());

	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
		
		  
		alt.accept();
		//driver.navigate().to("www.practo.com");
		//driver.switchTo().window(mainpage);
		closeBrowser();
	}
	
}