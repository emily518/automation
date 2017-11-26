package util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @author emily.wang 向元素输入文本事件
 */
public class Input {
	/**
	 * 通过id找到元素并对其输入文本
	 * 
	 * @param driver
	 * @param id
	 * @param key
	 */
	private static final SelLogger logger=SelLogger.getLogger(Input.class);
	public static void id(WebDriver driver, String id, String key) {
		
		try{
			logger.log("Input keyword - "+key+" by id:"+id);
			WebElement we=driver.findElement(By.id(id));
			we.sendKeys(key);
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+id);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to input keyword: "+key);
		}
	}

	/**
	 * 通过xpath找到元素并对其输入文本
	 * 
	 * @param driver
	 * @param xpath
	 * @param info
	 */
	public static void xpath(WebDriver driver, String xpath, String info) {
		try{
			logger.log("Input keyword - "+info+" by xpath:"+xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(info);
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+xpath);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to input keyword: "+info);
		}
	}
	/**
	 * 通过name找到元素并对其输入文本
	 * 
	 * @param driver
	 * @param name
	 * @param key
	 */
	public static void name(WebDriver driver, String name, String key,int count) {
		try{
			logger.log("Input keyword - "+key+" by name:"+name);
		List<WebElement> elem=driver.findElements(By.name(name));
		elem.get(count-1).sendKeys(key);
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+name);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to input keyword: "+key);
		}
	}
	
	/**清除元素的内容并输入新的内容（通过id获取元素）
	 * @param driver
	 * @param id
	 */
	public static void clearById(WebDriver driver,String id,String keys) {
		try{
			logger.log("Input keyword - "+keys+" by id:"+id);
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(keys);
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+id);
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to input keyword: "+keys);
		}
	}
	
	/**清除元素的内容并输入新的内容（通过xpath获取元素）
	 * @param driver
	 * @param id
	 */
	public static void clearByXpath(WebDriver driver,String xpath,String keys) {
		try{
			logger.log("Input keyword - "+keys+" by xpath:"+xpath);
		driver.findElement(By.xpath(xpath)).clear();
		driver.findElement(By.xpath(xpath)).sendKeys(keys);
		}
		catch(ElementNotVisibleException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Can't see the element:"+xpath);
		}
		catch(NoSuchElementException e)
		{
			logger.error(driver, "Fail to input keyword: "+keys);
		}
	}
}
