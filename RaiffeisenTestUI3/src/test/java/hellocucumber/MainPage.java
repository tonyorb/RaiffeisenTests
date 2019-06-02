package hellocucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage{
		
	@FindBy(xpath = "//*[@d='M-4-4h24v24H-4z']")
	private WebElement closePopUp;

	@FindBy(xpath = "//span[@class='actions-menu__item-label'][contains(text(),'Transfer') or contains(text(),'Перевести')]")
	private WebElement transferItem;
	
	@FindBy(xpath = "//span[@class='left-sidebar__label'][contains(text(),'Deposits') or contains(text(),'Вклады')]")
	private WebElement depositsItem;
	
	public void SelectTransferItem() {
		transferItem.click();
	}
	public void SelectDepositsItem() {
		depositsItem.click();
	}
	public void ClosePopUp() {
		closePopUp.click();
	}
	public void SelectMenuItem(String name) {	
		switch (name) {
			case("Перевести"):
				SelectTransferItem();
				break;
			case("Вклады"):
				SelectDepositsItem();
				break;
			case("Transfer"):
				SelectTransferItem();
				break;
			case("Deposits"):
				SelectDepositsItem();;
				break;
			default:
				System.out.println("Данный пункт меню не найден");
				break;
		}
	}
	public MainPage(final WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
