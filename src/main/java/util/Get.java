package util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * @author emily.wang 获取元素事件
 */
public class Get {
	private static final SelLogger logger=SelLogger.getLogger(Input.class);
	/**
	 * 通过id找元素
	 * 
	 * @param driver
	 * @param id
	 * @return
	 */
	public static WebElement id(WebDriver driver, String id) {
		WebElement we = null;
		try{
			we= driver.findElement(By.id(id));
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get element by id:"+id);
		}
		return we;
	}

	/**
	 * 通过xpath找元素
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement xpath(WebDriver driver, String xpath) {
		WebElement we = null;
		try{
			we=driver.findElement(By.xpath(xpath));
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get element by xpath:"+xpath);
		}
		return we;
	}
	/**
	 * 通过name找元素
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement name(WebDriver driver, String name,int num) {
		List<WebElement> elem = null;
		try{
			elem=driver.findElements(By.name(name));
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get element by name:"+name);
		}
		return elem.get(num-1);
	}
	/**
	 * 通过Attribute找元素
	 * 
	 * @param driver
	 * @param attribute 参数   value值
	 * @return
	 */
	public static WebElement attribute(WebDriver driver, String attribute,String value) {
		return driver.findElement(By.xpath(".//*[@"+attribute+"='"+value+"']"));
	}
	
	/**
	 * 通过id获取元素的文本
	 * 
	 * @param driver
	 * @param id
	 * @return
	 */
	public static String textById(WebDriver driver, String id) {
		String text = null;
		try{
			text=driver.findElement(By.id(id)).getText();
			}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get string by id:"+id);
		}
		return text;
	}

	/**
	 * 通过xpath获取元素的文本
	 * 
	 * @param driver
	 * @param xapth
	 * @return
	 */
	public static String textByXpath(WebDriver driver, String xpath) {
		String text = null;
		try{
			text= driver.findElement(By.xpath(xpath)).getText();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get string by xpath:"+xpath);
		}
		return text;
	}
	/**
	 * 通过Attribute获取元素的值
	 * 
	 * @param driver
	 * @param attribute 参数   value值
	 * @return
	 */
	public static String textByAttribute(WebDriver driver, String attribute,String value) {
		String text = null;
		try{
			text= driver.findElement(By.xpath(".//*[@"+attribute+"='"+value+"']")).getText();
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get string by xpath:"+".//*[@"+attribute+"='"+value+"']");
		}
		return text;
	}

	/**
	 * 通过id获取元素的值
	 * 
	 * @param driver
	 * @param id
	 * @return
	 */
	public static String attributeById(WebDriver driver,String attribute, String id) {
		String text = null;
		try{
			text= driver.findElement(By.id(id)).getAttribute(attribute);
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get attribute by id:"+id+".//*[@"+attribute+"='"+attribute+"']");
		}
		return text;
	}

	/**
	 * 通过xpath获取元素的值
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static String attributeByXpath(WebDriver driver, String attribute,String xpath) {
		String text = null;
		try{
			text= driver.findElement(By.xpath(xpath)).getAttribute(attribute);
		}
		catch(NoSuchElementException e)
		{
			logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get attribute by xpath:"+xpath+".//*[@"+attribute+"='"+attribute+"']");
		}
		return text;
	}
	
	
	
	/**通过xpath获取表格
	 * @param driver
	 * @param xpath 表的xpath
	 * @param row  表格所在的行
	 * @param column 表格所在的列
	 * @return
	 */
	public static WebElement getTableCellByXpath(WebDriver driver,String xpath,int row, int column)
	{
		WebElement cell = null;
		 try{
			 cell = driver.findElement(By.xpath(xpath+"/tbody/tr["+row+"]/td["+column+"]"));
		 }
			catch(NoSuchElementException e)
			{
				logger.getScreenshotRecord(driver, "Error");
				logger.error(driver, "Fail to get table by xpath"+xpath+"/tbody/tr["+row+"]/td["+column+"]");
			}
		    return cell;
	}
	
	/** 通过id获取表格
	 * @param driver
	 * @param id 表的id
	 * @param row  表格所在的行
	 * @param column 表格所在的列
	 * @return
	 */
	public static WebElement getTableCellById(WebDriver driver,String id,int row, int column)
	{
		WebElement cell = null;
		 try{
			 cell =  driver.findElement(By.xpath("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + column + "]"));
		 }
			catch(NoSuchElementException e)
			{
				logger.getScreenshotRecord(driver, "Error");
				logger.error(driver, "Fail to get table by id"+id);
			}
		    return cell;
	}
	
	/**
	 * @param driver
	 * @return
	 * 获取页面title
	 */
	public static String title(WebDriver driver)
	{
		return driver.getTitle();
	}
	public static String url(WebDriver driver)
	{
		return driver.getCurrentUrl();
	}
}
