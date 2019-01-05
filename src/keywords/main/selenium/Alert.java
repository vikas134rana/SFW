package keywords.main.selenium;

import keywords.core.Driver;

public class Alert {

	public static void accept() {
		Driver.getDriver().switchTo().alert().accept();
	}

	public static void dismiss() {
		Driver.getDriver().switchTo().alert().dismiss();
	}

	public static void getText() {
		Driver.getDriver().switchTo().alert().getText();
	}

	public static void sendKeys(String value) {
		Driver.getDriver().switchTo().alert().sendKeys(value);
	}

}
