/**
 * @company JJE
 * @author Emily.Wang
 * @Time 2014-1-21 下午05:52:55
 * @Description
 */
package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ExceptionHandle {
	
 public static boolean isElementPresent(WebDriver driver,By by) {
    try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

}

