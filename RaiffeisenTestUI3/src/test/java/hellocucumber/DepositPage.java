package hellocucumber;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepositPage {
	
	
	
	@FindBy(xpath = "//button[contains(text(),'Пополнить вклад') or contains(text(),'Replenish the deposit')]")
	private List <WebElement> replenishTheDepositButtons;
	

	@FindBy(xpath = "//c-select-item")
	private List <WebElement> selectAccountList;
		
	@FindBy(xpath = "//button[@class ='rc-form__submit rc-button']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//button[@class='confirm-operation-row__side-submit rc-button']")
	private WebElement smsConfirmButton;
	
	@FindBy(xpath = "//*[@class='rc-select__select-template']//span[@class='amount__wholes']")
	private List <WebElement> balanceAccountList;
	
	@FindBy(xpath = "//*[@class='steps__item-title']")
	private List <WebElement> replenishSteps;
	
	@FindBy(xpath = "//*[@class='steps__item ng-star-inserted steps__item_current']/span[2]")
	private WebElement currentStepLabel;
		
	@FindBy(xpath = "//*[@class='c-input-currency__input c-input ng-untouched ng-pristine ng-invalid']")
	private WebElement amountInput;
	
	@FindBy(xpath = "//input[@autofocus]")
	private WebElement smsField;
		
	@FindBy(xpath = "//success-operation-title")
	private List <WebElement> successTitleList;
	
	public void ChooseAction (String item, String action) {
		switch (item) {
			case ("Вклад Тройная выгода"):
				switch (action) {
				case ("Пополнить вклад"):
					replenishTheDepositButtons.get(3).click();
					break;
				}
				break;
			case ("Вклад Свобода Действий"):
				switch (action) {
				case ("Пополнить вклад"):
					replenishTheDepositButtons.get(0).click();
					break;
				}
				break;
			case ("Deposit Тройная выгода"):
				switch (action) {
				case ("Replenish the deposit"):
					replenishTheDepositButtons.get(3).click();
					break;
				}
				break;
			case ("Deposit Свобода Действий"):
				switch (action) {
				case ("Replenish the deposit"):
					replenishTheDepositButtons.get(0).click();
					break;
				}
				break;
			default:
				System.out.println("Данный вклад не найден");
		
		}
	}
	public void ChooseRandomAccountAndSum () {
		Random random = new Random();
		selectAccountList.get(0).click();
		selectAccountList.get(random.nextInt(7) + 1).click();
		int currentAccountBalanse = Integer.parseInt(balanceAccountList.get(0).getText().replaceAll("\\s+",""));
		String replenishAmount = Integer.toString(random.nextInt(currentAccountBalanse));
		amountInput.sendKeys(replenishAmount);	
	}
	public void Submit () {
		submitButton.click();		
	}
	public String GetCurrentStepLabel () {
		return currentStepLabel.getText();		
	}
	public void EnterSMSCode (String code) {
		smsField.sendKeys(code);		
	}
	public void SMSConfirm () {
		smsConfirmButton.click();		
	}
	public Boolean isOperationSuccessful () {
		return successTitleList.size()>0;		
	}
	public Boolean isReplenishInterfaceExists () {
		return	replenishSteps.size()==3;
	}
	public DepositPage(final WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
