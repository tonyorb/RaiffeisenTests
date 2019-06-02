package hellocucumber;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicActions{
	MainPage mainPage = new MainPage(Hooks.driver);
	TransferPage transferPage = new TransferPage(Hooks.driver);
	DepositPage depositPage = new DepositPage(Hooks.driver);
	public void OpenUrl(String url) {
    	Hooks.driver.get(url);
	}

	public static void WaitUntilAjaxComplite(WebDriver driver) {
		new WebDriverWait(driver, 2000).until(new ExpectedCondition<Boolean>() {
			  public Boolean apply(WebDriver driver) {
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    return (Boolean) js.executeScript("return jQuery.active == 0");
			  }
			});
	}
}
