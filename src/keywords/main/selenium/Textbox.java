package keywords.main.selenium;

import org.openqa.selenium.WebElement;

import keywords.core.type.Get;
import keywords.main.common.component.AbstractTextbox;

public class Textbox implements AbstractTextbox {

	static Textbox obj;

	public static Textbox getInstance() {
		if (obj == null)
			obj = new Textbox();

		return obj;
	}
	
	@Override
	public void typeOnTextBox(WebElement ele, String value) {

		ele.sendKeys(value);
	}

	@Override
	public Get getTextBoxValue(WebElement ele) {

		String textboxValue = ele.getAttribute("value");
		return new Get(textboxValue);
	}

}
