package util;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @author emily.wang 点击元素事件
 */
public class Click {

	/**
	 * 通过id获取元素并点击
	 * 
	 * @param driver
	 * @param id
	 */
	private static final SelLogger logger=SelLogger.getLogger(Click.class);
	public static void id(WebDriver driver, String id) {
		try{
			logger.log("Click element by id:"+id+" "+driver.findElement(By.id(id)).getText());
			driver.findElement(By.id(id)).click();
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+id);
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to click element by id:"+id);
		}
	}
	/**
	 * 通过xpath获取元素并点击
	 * 
	 * @param driver
	 * @param xpath
	 */
	public static void xpath(WebDriver driver, String xpath) {
		logger.log("Click element by xpath:"+xpath+" "+driver.findElement(By.xpath(xpath)).getText());
		try{
		driver.findElement(By.xpath(xpath)).click();
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+xpath);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to click element by xpath:"+xpath+" "+driver.findElement(By.xpath(xpath)).getText());
		}
	}
	/**
	 * 通过name获取元素并点击
	 * 
	 * @param driver
	 * @param xpath
	 */
	public static void name(WebDriver driver, String name,int count) {
		logger.log("Click element by name:"+name+" "+driver.findElement(By.name(name)).getText());
		try{
			driver.findElements(By.name(name)).get(count-1).click();
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+name);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to click element by name:"+name+" "+driver.findElement(By.name(name)).getText());
		}
	}
	/**
	 * 通过attribute定位元素并点击
	 * 
	 * @param driver
	 * @param id
	 */
	public static void attribute(WebDriver driver, String attribute,String value) {
		logger.log("Click element by attribute:"+attribute+" "+driver.findElement(By.xpath(".//*[@"+attribute+"='"+value+"']")).getText());
		try{
			driver.findElement(By.xpath(".//*[@"+attribute+"='"+value+"']")).click();
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+attribute);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to click element by attribute:"+attribute);
		}
	}
	/**
	 * 通过两个attribute定位元素并点击
	 * @param driver
	 * @param attribute
	 * @param value
	 */
	public static void attributes(WebDriver driver, String attribute1,String value1,String attribute2,String value2) {
		logger.log("Click element by attribute:"+attribute1+" and "+attribute2);
		try{
			driver.findElement(By.xpath(".//*[@"+attribute1+"='"+value1+"' and @"+attribute2+"='"+value2+"']")).click();
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+".//*[@"+attribute1+"='"+value1+"' and @"+attribute2+"='"+value2+"']");
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to click element by nattributes:"+attribute1+" and "+attribute2);
		}
	}
	/**
	 * 通过文本链接点击元素
	 * @param driver
	 * @param text
	 */
	public static void text(WebDriver driver, String text)
	{
		logger.log("Click element by text:"+text);
		try{
			driver.findElement(By.linkText(text)).click();
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+text);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to click element by text:"+text);
		}
	}
	
	/**
	 * @param e点击元素
	 */
	public static void element(WebDriver driver,WebElement ele)
	{
	   
		try{
			 ele.click();
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to click element.");
		}
	}
}
