package util;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

public class SelLogger {

  private static Logger logger = null;

  private static SelLogger logg = null;

  public static SelLogger getLogger(Class<?> T) {
    if (logger == null) {
      Properties props = new Properties();

      try {
        InputStream is = new FileInputStream("src//test//java//com//haowu//business//util//log4j.properties");
        props.load(is);
      } catch (IOException e) {
        e.printStackTrace();
      }

      PropertyConfigurator.configure(props);
      logger = Logger.getLogger(T);
      logg = new SelLogger();
    }
    return logg;
  }

  // 重写logger方法
  public void log(String msg) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar ca = Calendar.getInstance();
    logger.info(msg);
    Reporter.log("Reporter:" + sdf.format(ca.getTime()) + "===>" + msg);
  }

  public void debug(String msg) {
    logger.debug(msg);
  }

  public void warn(String msg) {
    logger.warn(msg);
    Reporter.log("Reporter:" + msg);
  }

  public void warn(String msg, Exception e) {
    logger.warn(msg, e);
    Reporter.log("Reporter:" + msg);
  }

  public void pass(String msg) {
    logger.info(msg);
    Reporter.log("Reporter:" + msg);
  }
  public void getScreenshotRecord(WebDriver driver,String picName)
  {
	  try{
	      String filePath="logs//TestReport//";
	      File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   
	      FileUtils.copyFile(screenShotFile, new File(filePath+picName+".png"));
	    }catch(IOException e){
	        e.printStackTrace();
	    } 
  }
  public void error(WebDriver driver,String msg) {
    logger.error(msg);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SS");
    Calendar ca = Calendar.getInstance();
    try{
      String filePath="logs//Fail_Log//";
      File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   
      FileUtils.copyFile(screenShotFile, new File(filePath+sdf.format(ca.getTime())+".png"));
    }catch(IOException e){
        e.printStackTrace();
    }
  }
}
