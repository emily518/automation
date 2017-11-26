package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

/**
* auther: Tanner.Li
* @param driver
* @param by
*/

public class PublicMethods {
	private static int maxWaitfor = 120;
	public static int maxLoadTime = 90;
	private static final String SMARK = "~";
	static Logger log4wd=Logger.getLogger("classname");
	
	/**
	* 在弹出的对话框（Dialog）上点击确认/是等接受性按钮。
	*/
	public static void chooseOKOnAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
		pass("Choose OK on alert successfully!");
	}
	
	public static void mouseOverToElement(WebDriver driver,By by){
		 new Actions(driver).moveToElement(driver.findElement(by)).perform();  
	}
	
	/**
	* 明确等待120s
	*/
	public static void implicitWait(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);   
	}	
	
	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {    
	    return new ExpectedCondition<WebElement>() {    
	          public WebElement apply(WebDriver driver) {    
	            WebElement element = driver.findElement(by);    
	            return element.isDisplayed() ? element : null;    
	          }
	    };    
	}
	
	public static void wait(WebDriver driver,By by){	
		boolean isDisplayed = false;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
		WebElement element = wait.until(visibilityOfElementLocated(by));
		while (!isDisplayed) {
			isDisplayed = (element == null)? false : element.isDisplayed();
		}
	}
	
	/**
	* 将滚动条滚到适合的位置
	*/
	public static void setScroll(WebDriver driver, int height) {
		try {
			String setscroll = "document.documentElement.scrollTop=" + height;
			String setscroll0="document.body.scrollTop=" + height;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(setscroll0);
			js.executeScript(setscroll);
		} catch (Exception e) {
			System.out.println("Fail to set the scroll.");
		}
	}
	
	/**
	* 查找元素个数
	*/
	public static int elementSize(WebDriver driver,By by,String tag){
		waitUtilElementVisible(driver.findElement(by),10);
		return driver.findElement(by).findElements(By.tagName(tag)).size();
	}
	
	public static String getTableText(WebDriver driver, String path, String tag, String extractPath1, String extractPath2, String targect) {
		String expected = null;
		int row = driver.findElement(By.xpath(path)).findElements(By.tagName(tag)).size();
		for (int i = 2; i <= row; i++) {
			String actual = PublicMethods.getText(driver, By.xpath(path + "/" + tag + "[" + i + "]/" + extractPath1));
			if (actual.contains(targect)) {
				expected = PublicMethods.getText(driver, By.xpath(path + "/" + tag + "[" + i + "]/" + extractPath2));
				break;
			}
		}
		return expected;
	}	
		
	public static void clickTableText(WebDriver driver, String path, String tag, String extractPath1, String extractPath2, String targect) {
		int row = driver.findElement(By.xpath(path)).findElements(By.tagName(tag)).size();
		for (int i = 2; i <= row; i++) {
			String actual = PublicMethods.getText(driver, By.xpath(path + "/" + tag + "[" + i + "]/" + extractPath1));
			if (actual.contains(targect)) {
				PublicMethods.click(driver, By.xpath(path + "/" + tag + "[" + i + "]/" + extractPath2));
				break;
			}
		}
	}	
	
	public static void clickTableTextRandon(WebDriver driver, String path, String tag, String extractPath1, String extractPath2, String targect) {	
		int row = driver.findElement(By.xpath(path)).findElements(By.tagName(tag)).size();
		int[] index=new int[row-1];
		int i = 0;
		for(int j=2; j<=row; j++){
				String actual = PublicMethods.getValueByAttribute(driver, By.xpath(path + "/" + tag + "[" + j+  "]/" + extractPath1),"value");	
				if (actual.equals(targect)) {						
					index[i]=j;
					i++;
				}		
		}
		PublicMethods.click(driver, By.xpath(path + "/" + tag + "[" + index[new Random().nextInt(index.length)] + "]/" + extractPath2));
	}
	
	
	public static String getString(WebDriver driver,By by,int beginIndex,int endIndex){
		String text = getText(driver, by);
		return text.substring(beginIndex, endIndex);		
	}

	/**
	 * 获取属性值
	 */
	public static String getValueByAttribute(WebDriver driver,By by,String attributeName){
		return driver.findElement(by).getAttribute(attributeName);
	}
	
	/**
	* 地址跳转方法，与WebDriver原生navigate.back方法内容完全一致。
	*/
	public static void navigateBack(WebDriver driver){
		driver.navigate().back();
	}
	
	/**
	* 在等到指定对象可见之后在该对象上做清理操作，一般用于输入框和选择框。
	*/
	public static void clear(WebDriver driver,By by) {
		WebElement element = driver.findElement(by);
		element.clear();
	}

	public static void waitUtilElementVisible(WebElement element) {
		waitUtilElementVisible(element, maxWaitfor);
	}
		
	/**
	* 在指定时间内循环等待，直到对象可见，超时之后直接抛出对象不可见异常信息。
	 * @return 
	*/
	public static void waitUtilElementVisible(WebElement element, int timeout) {
		long start = System.currentTimeMillis();
		boolean isDisplayed = false;
		while (!isDisplayed && ((System.currentTimeMillis() - start) < timeout * 1000)) {
			isDisplayed = (element == null)? false : element.isDisplayed();
			isDisplayed = (element == null)? false : element.isDisplayed();
		}
		if (!isDisplayed){
			throw new ElementNotVisibleException("the element is not visible in " + timeout + "seconds!");
		}
	}
	
	public static void clickByJavaScript(WebDriver driver,By by){
		waitUtilElementVisible(driver.findElement(by));
		((JavascriptExecutor) driver).executeScript("return arguments[0].click();",driver.findElement(by));
	}
	
	public static void click(WebDriver driver, By by) {
		waitUtilElementVisible(driver.findElement(by),90);
		driver.findElement(by).click();
		pass("click on element [" + by.toString() + "]");
	}
	
	/**
	* 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	*/
	public static String getText(WebDriver driver,By by) {
		String text = driver.findElement(by).getText();
		return text;
	}

	/**
	* get current time string in specified date format.
	* @param dateFormat：the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS
	*/
	public String formatedTime(String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(new Date());
	}

	/**
	* @param addDays: days after or before current date, use + and - to add.
	* @param dateFormat: the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
	*/
	public String addDaysByFormatter(int addDays, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, addDays);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(cal.getTime());
	}

	/**
	* 以追加文本的模式在指定可编辑对象中输入文本，操作之前自动等待到对象可见。
	*/
	public static void sendKeysAppend(WebDriver driver, By by, String text) {
		WebElement element = driver.findElement(by);
		waitUtilElementVisible(element);
		element.sendKeys(text);
		pass("send text [" + text + "] to element");
	}

	/**
	* 清理指定对象中已经输入的内容重新输入，操作之前自动等待到对象可见。
	*/
	public static void sendKeys(WebDriver driver, By by, String text) {
		WebElement element = driver.findElement(by);
		waitUtilElementVisible(element);
		element.clear();
		element.sendKeys(text);
		pass("input text [" + text + "] to element [" + by.toString() + "]");
	}

	/**
	* 使用remote webdriver执行JS函数。
	* @param report: text content to be reported
	*/
	public static void jsExecutor(WebDriver driver, String js, String report) {
		((JavascriptExecutor) driver).executeScript(js);
	}

	/**
	* 使用DOM（Documnet Object Modal）修改页面中对象的文本属性值，使用ID定位对象则返回唯一对象，其余返回数组。
	*/
	public static void sendKeysByDOM(WebDriver driver, String by,String byValue, String text, int index) {
		String js = null;
		if (by.equalsIgnoreCase("tagname")) {
			js = "document.getElementsByTagName('" + byValue + "')[" + index
					+ "].value='" + text + "'";
		} else if (by.equalsIgnoreCase("name")) {
			js = "document.getElementsByName('" + byValue + "')[" + index
					+ "].value='" + text + "'";
		} else if (by.equalsIgnoreCase("id")) {
			js = "document.getElementById('" + byValue + "').value='" + text
					+ "'";
		} else {
			throw new IllegalArgumentException(
					"only can find element by TagName/Name/Id");
		}
		jsExecutor(driver, js, "input text [" + text + "] to element [" + by
				+ " ]");
	}

	/**
	* 按照ID定位页面中对象，并使用DOM（Documnet Object Modal）修改其文本属性值。
	*/
	public static void sendKeysById(WebDriver driver, String elementId,String text) {
		sendKeysByDOM(driver, "Id", elementId, text, 0);
	}

	/**
	* 按照名称（Name）和序号定位页面中对象，并使用DOM（Documnet Object Modal）修改其文本属性值。
	*/
	public static void sendKeysByName(WebDriver driver, String elementName,String text, int elementIndex) {
		sendKeysByDOM(driver, "Name", elementName, text, elementIndex);
	}

	/**
	* 按照标签名称（TagName）和序号定位页面中对象，并使用DOM（Documnet Object Modal）修改其文本属性值。
	* @param elementIndex： the index of the elements shared the same tag name, begins with 0
	*/
	public static void sendKeysByTagName(WebDriver driver,
			String elementTagName, String text, int elementIndex) {
		sendKeysByDOM(driver, "TagName", elementTagName, text, elementIndex);
	}

	/**
	* 按照指定序号选择下拉列表中的选项。
	*/
	public static void selectByIndex(WebDriver driver, By by, int index) {
		WebElement element = driver.findElement(by);
		//waitUtilElementVisible(element);
		Select select = new Select(element);
		select.selectByIndex(index);
		pass("Item selected by index [" + index + "] on [" + by.toString() + "]");
	}

	/**
	* 按照指定选项的实际值（不是可见文本值，而是对象的“value”属性的值）选择下拉列表中的选项。
	*/
	public static void selectByValue(WebDriver driver, By by, String itemValue) {
		WebElement element = driver.findElement(by);
		//waitUtilElementVisible(element);
		Select select = new Select(element);
		select.selectByValue(itemValue);
		pass("Item selected by item value [" + itemValue + "] on [" + by.toString() + "]");
	}

	/**
	* 按照指定选项的可见文本值（用户直接可以看到的文本）选择下拉列表中的选项。
	*/
	public static void selectByVisibleText(WebDriver driver, By by, String text) {
		WebElement element = driver.findElement(by);
		//waitUtilElementVisible(element);
		Select select = new Select(element);
		select.selectByVisibleText(text);
		pass("Item selected by visible text [" + text + "] on [" + by.toString() + "]");
	}

	/**
	* 判断弹出的对话框（Dialog）是否存在。
	*/
	public static boolean alertExists(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ne) {
			return false;
		}
	}

	/**
	* 在指定的时间内判断弹出的对话框（Dialog）是否存在。
	*/
	public boolean alertExists(WebDriver driver, int seconds) {
		long start = System.currentTimeMillis();
		while ((System.currentTimeMillis() - start) < seconds * 1000) {
			try {
				driver.switchTo().alert();
				return true;
			} catch (NoAlertPresentException ne) {
			}
		}
		return false;
	}

	/**
	* 判断指定的对象是否存在。
	*/
	public static boolean elementExists(WebDriver driver, By by) {
		return (driver.findElements(by).size() > 0) ? true : false;
	}

	/**
	* 按照网页标题判断页面是否存在，标题可使用部分内容匹配。
	*/
	public static boolean browserExists(WebDriver driver, String browserTitle) {
		try {
			String defaultHandle = driver.getWindowHandle();
			Set<String> windowHandles = driver.getWindowHandles();
			for (int i = 0; i < 20; i++) {
				Thread.sleep(500);
				if (driver.getWindowHandles().equals(windowHandles)) {
					break;
				}
				if (i == 20 && !driver.getWindowHandles().equals(windowHandles)) {
					return false;
				}
			}
			for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(browserTitle)) {
					driver.switchTo().window(defaultHandle);
					return true;
				}
			}
			driver.switchTo().window(defaultHandle);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	* 在指定时间内按照网页标题判断页面是否存在，标题可使用部分内容匹配。
	*/
	public static boolean browserExists(WebDriver driver, String browserTitle, int seconds) {
		long start = System.currentTimeMillis();
		boolean isExist = false;
		while (!isExist
				&& (System.currentTimeMillis() - start) < seconds * 1000) {
			isExist = browserExists(driver, browserTitle);
		}
		return isExist;
	}

	/**
	* 网页窗口最大化操作。
	*/
	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	* 在当前页面中自动选择默认的页面框架（frame）
	*/
	public static void selectDefaultWindowFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
		pass("Select default windowFrame!");
	}

	/**
	* 在当前操作的页面和对象时自动选择已被激活的对象。
	*/
	public static void focusOnActiveElement(WebDriver driver) {
		driver.switchTo().activeElement();
	}

	/**
	* 选择最新弹出的窗口，需要预存第一个窗口的handle。
	*/
	public static void selectNewWindow(WebDriver driver, String firstHandle) {
		Set<String> handles = null;
		Iterator<String> it = null;
		handles = driver.getWindowHandles();
		handles = driver.getWindowHandles();
		handles.remove(firstHandle);
		it = handles.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
		}
		driver.switchTo().defaultContent();
		pass("Select new window!");
	}

	/**
	* 选择最新弹出的窗口，需要预存所有已有窗口的handles。
	*/
	public static void selectNewWindow(WebDriver driver, Set<String> originalHandles) {
		Set<String> newHandles = driver.getWindowHandles();
		newHandles = driver.getWindowHandles();
		Iterator<String> olds = originalHandles.iterator();
		while (olds.hasNext()) {
			newHandles.remove(olds.next());
		}
		Iterator<String> news = newHandles.iterator();
		while (news.hasNext()) {
			driver.switchTo().window(news.next());
		}
		driver.switchTo().defaultContent();
		pass("Select new window!");
	}

	/**
	* 选择最新打开的窗口
	*/
	public static void selectWindowLatest(WebDriver driver) {
		Object[] winArray = driver.getWindowHandles().toArray();
		winArray = driver.getWindowHandles().toArray();
		driver.switchTo().window(winArray[winArray.length-1].toString());
		pass("Select the last opened window !");
	}

	
	/**
	* 按窗口的index选择窗口,从1开始
	*/
	public static void selectWindowByIndex(WebDriver driver,int index) {
		Object[] winArray = driver.getWindowHandles().toArray();
		winArray = driver.getWindowHandles().toArray();
		System.out.println(winArray.length);
		for (int i = 0; i<winArray.length ; i++) {
			if(index==i+1){
			driver.switchTo().window(winArray[i].toString());
			}
		}
	}
		
	public static void selectWindowExcept(WebDriver driver,String windowTitle) {
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			String title = driver.getTitle();
			if (!windowTitle.equals(title)) {
				driver.switchTo().defaultContent();
			}
		}
	}
	
	/**
	* 按照网页标题选择窗口，标题内容需要全部匹配。
	*/
	public static void selectWindow(WebDriver driver, String windowTitle) {
		Set<String> windowHandles = null;
		windowHandles = driver.getWindowHandles();
		windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			String title = driver.getTitle();
			if (windowTitle.contains(title)) {
				driver.switchTo().defaultContent();
				return;
			}
		}
	}

	/**
	* 按照网页标题选择窗口，标题内容需要全部匹配，超时未出现则报错。
	*/
	public static void selectWindowWithTimeout(WebDriver driver, String windowTitle, int timeout) {
		Assert.assertTrue(browserExists(driver, windowTitle, timeout));
		selectWindow(driver, windowTitle);
	}

	/**
	* 按照网页标题选择并且关闭窗口，重名窗口按照指定的重名的序号关闭，标题内容需要全部匹配。
	* @param index：the index of the window which shared the same title, begins with 1.
	*/
	public static void closeWindow(WebDriver driver, String windowTitle, int index) {
		Object[] winArray = null;
		List<String> winList = new ArrayList<String>();
		winArray = driver.getWindowHandles().toArray();
		winArray = driver.getWindowHandles().toArray();
		for (int i = 0; i < winArray.length - 1; i++) {
			driver.switchTo().window(winArray[i].toString());
			if (windowTitle.equals(driver.getTitle())) {
				winList.add(winArray[i].toString());
			}
		}
		driver.switchTo().window(winList.get(index - 1));
		driver.switchTo().defaultContent();
		driver.close();
	}

	/**
	* 按照网页标题选择窗口，适用于无重名的窗口，标题内容需要全部匹配。
	*/
	public static void closeWindow(WebDriver driver, String windowTitle) {
		Object[] winArray = driver.getWindowHandles().toArray();
		winArray = driver.getWindowHandles().toArray();
		for (int i = winArray.length - 1; i > 0; i--) {
			driver.switchTo().window(winArray[i].toString());
			if (windowTitle.equals(driver.getTitle())) {
				driver.switchTo().defaultContent();
				driver.close();
				break;
			}
		}
	}

	/**
	* 关闭除了指定标题页面之外的所有窗口，适用于例外窗口无重名的情况，标题内容需要全部匹配。
	*/
	public static void closeWindowExcept(WebDriver driver, String windowTitle) {
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			String title = driver.getTitle();
			if (!windowTitle.equals(title)) {
				driver.switchTo().defaultContent();
				driver.close();
			}
		}
	}

	/**
	* 关闭除了指定标题页面之外的所有窗口，例外窗口如果重名，按照指定的重名顺序关闭，标题内容需要全部匹配。
	* @param index： the index of the window to keep shared the same title with others, begins with 1.
	*/
	public static void closeWindowExcept(WebDriver driver, String windowTitle,
			int index) {
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			String title = driver.getTitle();
			if (!windowTitle.equals(title)) {
				driver.switchTo().defaultContent();
				driver.close();
			}
		}
		Object[] winArray = driver.getWindowHandles().toArray();
		winArray = driver.getWindowHandles().toArray();
		for (int i = 0; i < winArray.length; i++) {
			driver.switchTo().window(winArray[i].toString());
			if (i + 1 != index) {
				driver.switchTo().defaultContent();
				driver.close();
			}
		}
	}

	/**
	* 判断在指定的时间内是否有新的窗口弹出，无论其是否有标题。
	*/
	public static boolean isNewWindowExits(WebDriver driver, int browserCount, int seconds) {
		Set<String> windowHandles = null;
		boolean isExist = false;
		long begins = System.currentTimeMillis();
		while ((System.currentTimeMillis() - begins < seconds * 1000)
				&& !isExist) {
			windowHandles = driver.getWindowHandles();
			windowHandles = driver.getWindowHandles();
			isExist = (windowHandles.size() > browserCount) ? true : false;
		}
		return isExist;
	}

	/**
	* 判断在指定的时间内是否有新的窗口弹出，无论其是否有标题。
	*/
	public boolean isNewWindowExits(WebDriver driver, Set<String> oldHandles, int seconds) {
		boolean isExist = false;
		Set<String> windowHandles = null;
		long begins = System.currentTimeMillis();
		while ((System.currentTimeMillis() - begins < seconds * 1000)
				&& !isExist) {
			windowHandles = driver.getWindowHandles();
			windowHandles = driver.getWindowHandles();
			isExist = (windowHandles.size() > oldHandles.size()) ? true : false;
		}
		return isExist;
	}

	/**
	* 按照序号选择框架（frame）。
	*/
	public static void selectFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	* 按照名称或者ID选择框架（frame）。
	*/
	public static void selectFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	* 按照指定的元素定位方式选择框架（frame）。
	*/
	public static void selectFrame(WebDriver driver, By by) {
		driver.switchTo().frame(driver.findElement(by));
	}
	
	public static void pass(String message) {
		report("passed", message);
	}

	
	public static void fail(String message) {
		report("failed", message);
	}
	
	public static void report(String status, String message) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		int first = 3, last = 3;
		String methodName = trace[first].getMethodName();
		for (int i = first; i < trace.length; i++) {
			if (trace[i].getClassName().contains(".reflect.")) {
				last = ((i - 1) <= first) ? first : (i - 1);
				break;
			}
		}
		String traceClass = trace[last].getClassName() + "#"+ trace[last].getLineNumber();
		String method = traceClass.split("\\.")[traceClass.split("\\.").length-1];
		log4wd.info(method + SMARK + methodName + SMARK + status + SMARK + message.replace(SMARK, "-").replace("&", "&amp;"));
	}
	
	/**
	 * 地址跳转方法，使用WebDriver原生get方法，加入失败重试的次数定义。
	 */
	public static void get(WebDriver driver, String url, int actionCount) {
		for (int i = 0; i < actionCount; i ++){
			if (i == 0){
				setPageLoadTimeOut(driver,60);
			}
			try {
				driver.get(url);
				pass("navigate to url [" + url + "]");
				return;
			} catch (Exception e) {
			} finally{
				setPageLoadTimeOut(driver,maxLoadTime);				
			}
		}
	}

	/**
	 * 地址跳转方法，使用WebDriver原生get方法，默认加载超重试【1】次。
	 */
	public static void get(WebDriver driver,String url) {
		get(driver, url, 2);
	}
	
	public static void setScriptingTimeOut(WebDriver driver,int seconds) {
		driver.manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);	
	}

	public static void setElementLocateTimeOut(WebDriver driver,int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);	
	}

	public static void setPageLoadTimeOut(WebDriver driver,int seconds) {
		driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
	}
	
	public static Wait<WebDriver> getWait(WebDriver driver)
	    {
	        return new FluentWait<WebDriver>(driver)
	                .withTimeout(60, TimeUnit.SECONDS)
	                .pollingEvery(5, TimeUnit.SECONDS)
	                .ignoring(NoSuchElementException.class);
	    }
}
