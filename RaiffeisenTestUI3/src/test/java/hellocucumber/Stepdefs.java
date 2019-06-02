package hellocucumber;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;

import static org.junit.Assert.*;

public class Stepdefs extends BasicActions {

	private String url;
	
	@Дано("существует демо версия интернет банка на ресурсе {string}")
	public void существует_демо_версия_интернет_банка_на_ресурсе(String url) {
		this.url = url;
	}

	@Когда("я зашел на ресурс и выбрал в основном меню вкладку {string}")
	public void я_зашел_на_ресурс_и_выбрал_в_основном_меню_вкладку(String item) {
		OpenUrl(url);
    	mainPage.ClosePopUp();
		mainPage.SelectMenuItem(item);
	}

	@То("я переместился на страницу Переводы интернет-банка")
	public void я_переместился_на_страницу_Переводы_интернет_банка() {
		assertEquals("https://online.raiffeisen.ru/demo/#/transfer/templates", Hooks.driver.getCurrentUrl());
	}

	@Когда("на странице Переводы я выбрал пункт меню {string} в разделе Людям")
	public void на_странице_Переводы_я_выбрал_пункт_меню_в_разделе_Людям(String item) {
		transferPage.SelectMenuItem(item);
	}

	@То("стал доступен интерфейс отправки средств С карты на карту")
	public void стал_доступен_интерфейс_отправки_средств_С_карты_на_карту() {
		assertTrue("Интерфейс не доступен",transferPage.isCardToCardInterfaceExists());
	}

	@Когда("я выбрал карту списания {string} и ввел невалидный номер карты получателя {string}")
	public void я_выбрал_карту_списания_и_ввел_невалидный_номер_карты_получателя(String name, String number) {
		transferPage.SelectFromCard(name);
		assertFalse("Существуют ошибки",transferPage.isAnyErrorsExist());
		transferPage.EnterToCardNumber(number);
	}

	@То("увидел ошибку {string}")
	public void увидел_ошибку(String string) {
		assertTrue("Ошибок не обнаружено",transferPage.isAnyErrorsExist());
	}
	
	@Дано("существует демо-версия интернет-банка на ресурсе {string}")
	public void существует_демо_версия_интернет_банка_наресурсе(String url) {
		this.url = url;
	}

	@Когда("я зашел на ресурс и выбрал в боковом меню вкладку {string}")
	public void я_зашел_на_ресурс_и_выбрал_в_боковом_меню_вкладку(String item) {
		OpenUrl(url);
    	mainPage.ClosePopUp();
		mainPage.SelectMenuItem(item);
	}

	@То("я переместился на страницу Вклады интернет-банка")
	public void я_переместился_на_страницу_Вклады_интернет_банка() {
		assertEquals("https://online.raiffeisen.ru/demo/#/deposits", Hooks.driver.getCurrentUrl());
	}

	@Когда("на странице Вклады я нажал на кнопку {string} в пункте {string}")
	public void на_странице_Вклады_я_нажал_на_кнопку_в_пункте(String action, String item) {
		depositPage.ChooseAction(item, action);
	}

	@То("открылось окно с интерфейсом пополнения вклада")
	public void открылось_окно_с_интерфейсом_пополнения_вклада() {
		assertTrue("Интерфейс пополнения вклада недоступен",depositPage.isReplenishInterfaceExists());
	}

	@Когда("я выбрал счет списания случайным образом, ввел сумму пополлнения, которая не превышает остаток средств на выбранном счете")
	public void я_выбрал_счет_списания_случайным_образом_ввел_сумму_пополлнения_которая_не_превышает_остаток_средств_на_выбранном_счете() {
		depositPage.ChooseRandomAccountAndSum();
	}

	@Когда("нажал на кнопку Пополнить")
	public void нажал_на_кнопку_Пополнить() {
		depositPage.Submit();
	}

	@То("выполнился переход к пункту {string}")
	public void выполнился_переход_к_пункту(String label) {
		assertEquals("Переход не был выполнен", label, depositPage.GetCurrentStepLabel());
	}

	@Когда("я ввел СМС-код {string} в поле для Подтверждения")
	public void я_ввел_СМС_код_в_поле_для_Подтверждения(String code) {
		depositPage.EnterSMSCode(code);
	}

	@Когда("нажал на кнопку Подтвердить")
	public void нажал_на_кнопку_Подтвердить() {
		depositPage.SMSConfirm();
	}

	@То("на экран вывелось сообщение {string}")
	public void на_экран_вывелось_сообщение(String string) {
		assertTrue("Пополнение вклада не удалось",depositPage.isOperationSuccessful());
	} 
			
}
