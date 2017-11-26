package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


/**
 * @author emily.wang 清除文本内容
 */
public class Clear {
	private static final SelLogger logger=SelLogger.getLogger(Input.class);
	/**
	 * 清除元素的内容（通过id获取元素）
	 * 
	 * @param driver
	 * @param id
	 */
	public static void id(WebDriver driver, String id) {
		try{
		driver.findElement(By.id(id)).clear();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to clear element by id:"+id);
		}
	}

	/**
	 * 清除元素的内容（通过xpath获取元素）
	 * 
	 * @param driver
	 * @param xpath
	 */
	public static void xpath(WebDriver driver, String xpath) {
		try{
		driver.findElement(By.xpath(xpath)).clear();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to clear element by xpath:"+xpath);
		}
	}
	/**
	 * 清除元素的内容（通过name获取元素）
	 * 
	 * @param driver
	 * @param xpath
	 */
	public static void name(WebDriver driver, String name,int count) {
		try{
		driver.findElements(By.name(name)).get(count-1).clear();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to clear element by name:"+name);
		}
	}
}
