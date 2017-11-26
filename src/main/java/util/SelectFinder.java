package util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class SelectFinder {
	private static final SelLogger logger=SelLogger.getLogger(Input.class);
  public static Select getSelectById(WebDriver driver, String id) {
	  Select select = null;
	  try{
		  select =new Select(driver.findElement(By.id(id)));
	  }
	  catch(NoSuchElementException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get select by id:"+id);
	  }
    return select;
  }

  public static Select getSelectByName(WebDriver driver, String name) {
    Select select = null;
	  try{
		  select =new Select(driver.findElement(By.name(name)));
	  }
	  catch(NoSuchElementException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get select by name:"+name);
	  }
  return select;
  }

  public static WebElement getSelectOption(WebDriver driver, String id, int index) {
    WebElement we = null;
	  try{
		  logger.log("Get the select option by id:"+id+" and index:"+index+"");
		  we =getSelectById(driver, id).getOptions().get(index);
	  }
	  catch(NoSuchElementException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Fail to get select option by id:"+id+" - and index :"+index);
	  }
return we;
  }
  /**
 * select by visible text
 */
public static void selectByText(WebDriver driver,By by,String text)
  {
	 try{
		  logger.log("Select option by text:"+text);
		  new Select (driver.findElement(by)).selectByVisibleText(text);
	 }
	   catch(NoSuchElementException e)
		  {
			  logger.getScreenshotRecord(driver, "Error");
			  logger.error(driver, "Fail to get select option by text:"+text);
		  }
  }

/**
* select by option
*/
public static void selectByOption(WebDriver driver,By by,int option)
{
	  
	   try{
			  logger.log("Select option by option:"+option);
			  new Select (driver.findElement(by)).selectByIndex(option);
		 }
		   catch(NoSuchElementException e)
			  {
				  logger.getScreenshotRecord(driver, "Error");
				  logger.error(driver, "Fail to get select option by option:"+option);
			  }
}

  public static WebElement getSelectOption(Select select, String value) {
    List<WebElement> options = select.getOptions();
    for (WebElement e : options) {
      if (value.trim().equals(e.getText())) {
        return e;
      } else if (value.trim().equals(e.getAttribute("value"))) {
        return e;
      } else if (value.trim().equals(e.getAttribute("title"))) {
        return e;
      } else if (value.trim().equals(e.getAttribute("name"))) { 
    	  return e; }
    }
    return null;
  }

  /*
  public static WebElement getSelectOptionbyName(WebDriver driver, String name, String value) {
    List<WebElement> options = getSelectById(driver, name).getOptions();
    for (WebElement e : options) {
      if (value.equals(e.getText())) {
        return e;
      } else if (value.equals(e.getAttribute("value"))) {
        return e;
      } else if (value.equals(e.getAttribute("title"))) {
        return e;
      } else if (value.equals(e.getAttribute("name"))) { return e; }

    }
    return null;
  }
  */

public static String getSelectText(WebDriver driver, String id, int index) {
	String text=null;
	try{
		  logger.log("Get Select option text by id:"+id);
		  text=getSelectById(driver, id).getOptions().get(index).getText();
	 }
	catch(NoSuchElementException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
		  logger.error(driver, "Fail to get select text by id:"+id);
	  }
    return text;
  }
}
