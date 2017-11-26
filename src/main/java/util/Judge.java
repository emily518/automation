package util;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Judge {

  /*public static boolean doesWebElementExist(WebDriver driver, By selector) {
    if (driver.findElements(selector).size() > 0) {
      return true;
    } else {
      return false;
    }
  }*/

  //The Firefox17 didn't support NoSuchElementException .when the node can't be found,the Firefox will keep searching util find it.
    public static boolean doesWebElementExist(WebDriver driver, By selector) {
      try {
        driver.findElement(selector);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

  public static boolean doesWebElementExist(WebElement questionDiv, By selector) {
    if (questionDiv.findElements(selector).size() > 0) { return true; }
    return false;

  }
  public static boolean isSelected(WebDriver driver,String xpath)
  {
  	return driver.findElement(By.xpath(xpath)).isSelected();
  }
  //  public static boolean doesWebElementExist(WebElement questionDiv, By selector) {
  //    try {
  //      questionDiv.findElement(selector);
  //      return true;
  //    } catch (NoSuchElementException e) {
  //      return false;
  //    }
  //  }

  public static boolean doesWebElementExist(WebDriver driver, WebElement questionDiv, By selector) {
    if (questionDiv.findElements(selector).size() > 0) return true;
    return false;
  }

  //  public static boolean doesWebElementExist(WebDriver driver, WebElement questionDiv, By selector) {
  //    try {
  //      questionDiv.findElement(selector);
  //      return true;
  //    } catch (NoSuchElementException e) {
  //      return false;
  //    }
  //  }

}
