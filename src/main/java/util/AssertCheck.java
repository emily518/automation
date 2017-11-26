package util;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class AssertCheck {
	private static final SelLogger logger=SelLogger.getLogger(Input.class);
	public static void assertAlert(WebDriver driver,String expectedText){
		String alertText=null;
		try
		{
			alertText=SwitchTo.switchToAlert(driver).getText();
		}
		catch(NoAlertPresentException  e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get the alert");
		}
		Assert.assertEquals(alertText, expectedText,"The alert message is not the expected one!");
	}

	public static void assertSelect(WebDriver driver, String id, int index,String expectedText){
		String selectText=null;
		try
		{
			selectText=SelectFinder.getSelectOption(driver, id, index).getText();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get the select text");
		}
		Assert.assertEquals(selectText, expectedText,"The select option is not the expected one!");
	}
	public static void assertTitle(WebDriver driver,String expectedText){
		String title=null;
		try
		{
		title=driver.getTitle();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get the title");
		}
		Assert.assertEquals(title, expectedText,"The title is not the expected one!");
	}
	public static void string(String actual,String expect)
	{
		Assert.assertEquals(actual,expect,"Assert the string fail!");
	}
	public static void assertErrorMessage(WebDriver driver,String xpath,String errorMsg)
	{
		String text=null;
		try
		{
			text=Get.textByXpath(driver,xpath);
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get the text");
		}
		Assert.assertEquals(text, errorMsg,"Assert the text got by xpath-"+xpath+" fail!");
	}
	
	
}
