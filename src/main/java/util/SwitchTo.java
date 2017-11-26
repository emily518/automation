/**
 * 
 */
package util;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;


/**
 * @company CampusCruiser
 * @author Emily_Wang
 * @date 2012-5-25
 */
public class SwitchTo {
	private static final SelLogger logger=SelLogger.getLogger(Input.class);
  public static WebDriver switchToIframe(WebDriver driver, String id) {
    driver.switchTo().frame(driver.findElement(By.id(id)));
    return driver;
  }
  public static WebDriver switchToIframe(WebDriver driver, By by) {
	  try{
		  driver.switchTo().frame(driver.findElement(by));
	  }
	  catch(NoSuchFrameException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to frame fail!");
	  }
	   
	    return driver;
	  }

  /**
 * @param driver
 * @return
 */
public static WebDriver getWindowHandle(WebDriver driver) {
	 try{
    for (String handle : driver.getWindowHandles()) {
      driver.switchTo().window(handle);
    }
	 }
	 catch(NoSuchWindowException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to window fail - cannot find a new window!");
	  }
    return driver;
  }

  public static String getWindowHandle(WebDriver driver, int index) {
    int i = 0;
    try{
    for (String handle : driver.getWindowHandles()) {
      if (i == index) {
      return handle; 
      }
      i++;
    }
    }
	 catch(NoSuchWindowException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to window fail - cannot find a new window!");
	  }
    return null;
  }

  public static void getWindowHandle(WebDriver driver, String handle) {
    try {
      boolean has = false;
      for (String g : driver.getWindowHandles()) {
        if (g.equals(handle)) {
          has = true;
        }

      }
      if (has) {
        driver.switchTo().window(handle);
      } else {
        switchToDefault(driver);
      }
    } catch (Exception e) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e1) {
    	  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to window fail - cannot find a new window!");
      }
      switchToDefault(driver);
    }
  }

  public static WebDriver switchToDefault(WebDriver driver) {
	  try{
		  driver.switchTo().defaultContent();
	  }
	  catch(NoSuchWindowException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to window fail - cannot find a new window!");
	  }
    return driver;
  }

  public static Alert switchToAlert(WebDriver driver) {
	  Alert alert = null;
	  try 
	  {
		  alert=driver.switchTo().alert();
	  }
	  catch(NoAlertPresentException e)
	  {
		  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to alert fail - cannot find the alert!");
	  }
    return alert;
  }
  
  public static boolean switchToWindowByTitle(WebDriver driver, String windowTitle) {
      String currentWindow = driver.getWindowHandle();
      try{
    	  
      Set<String> availableWindows = driver.getWindowHandles();
      if(availableWindows.size() != 0) {
          Iterator<String> windowIDIterator = availableWindows.iterator();
          while(windowIDIterator.hasNext()) {
              if(driver.switchTo().window(windowIDIterator.next()).getTitle().equals(windowTitle)) {
                  return true;
              }
          }
          driver.switchTo().window(currentWindow);
      }
      }
      catch(NoSuchWindowException e)
      {
    	  logger.getScreenshotRecord(driver, "Error");
			logger.error(driver, "Switch to window fail - cannot find the window with title - "+windowTitle);
      }
      return false;
  }
}
