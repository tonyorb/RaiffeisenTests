package hellocucumber;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransferPage {
	
	private String url = "https://online.raiffeisen.ru/demo/#/transfer/templates";
		
	@FindBy(xpath = "//div[@class='page-navigation-item__name'][contains(text(),'Between cards') "
			+ "or contains(text(),'С карты на карту')]")
	private List <WebElement> betweenCardsList;
	
	//есть id, но он меняется на странице и тесты падают
	@FindBy(xpath = "//*[@placeholder='С любой карты' or @placeholder='From the card of another bank' ]")
	private WebElement fromCardNumber;
	
	@FindBy(xpath = "//*[@placeholder='На любую карту' or @placeholder='To the card of another bank' ]")
	private WebElement toCardNumber;
	
	@FindBy(xpath = "//input[@class='c-input-select__input c-input ng-untouched ng-pristine ng-valid']")
	private List <WebElement> cardToCardInputs;
	
	@FindBy(xpath = "//*[@class='c-input-select__options c-select-options_bottom c-select-options c-select-options_show']"
			+ "//div[@data-card-id='1']")
	private WebElement myDebetCard;
	
	@FindBy(xpath = "//*[@class='c-input-select__options c-select-options_bottom c-select-options c-select-options_show']"
			+ "//div[@data-card-id='2']")
	private WebElement myCreditCard;
	
	@FindBy(xpath = "//span[@class='simple-error']")
	private List <WebElement> errorList;
	
	public void SelectMenuItem(String name) {	
		switch (name) {
			case("С карты на карту"):
				SelectBetweenCardsItem();
				break;
			case("Between cards"):
				SelectBetweenCardsItem();
				break;
			default:
				System.out.println("Данный пункт меню не найден");
				break;
		}
	}

	public String GetUrl () {
		return url;
	}
	public void SelectBetweenCardsItem() {
		betweenCardsList.get(1).click();
	}
	public void SelectBetweenCardsToMySelftem() {
		betweenCardsList.get(0).click();
	}
	public TransferPage SelectFromCardNumberForm() {
		fromCardNumber.click();
		return this;
	}
	public void SelectFromCard (String type) {
		switch (type) {
		case("Дебетовка в рублях"):
			SelectFromCardNumberForm().myDebetCard.click();
			break;
		case("Кредитка Visa"):
			SelectFromCardNumberForm().myCreditCard.click();
			break;
		default:
			System.out.println("Данная карта не найдена");
			break;
		}
	}
	public void EnterToCardNumber (String number) {
//		toCardNumber.click();
		toCardNumber.sendKeys(number);
	}
	public Boolean isAnyErrorsExist() {
		return errorList.size()>0;		
	}
	public Boolean isCardToCardInterfaceExists() {
		return cardToCardInputs.size() == 2;	
	}
	public TransferPage(final WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
