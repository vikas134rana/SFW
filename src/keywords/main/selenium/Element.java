package keywords.main.selenium;

import org.openqa.selenium.WebElement;

import keywords.core.other.Message;
import keywords.core.type.Get;
import keywords.core.type.Verify;
import keywords.main.common.component.AbstractElement;

public class Element implements AbstractElement {

	static Element obj;

	public static Element getInstance() {

		if (obj == null)
			obj = new Element();

		return obj;
	}

	@Override
	public void clickElement(WebElement ele) {

		ele.click();
	}

	@Override
	public void typeOnElement(WebElement ele, String value) {

		ele.clear();
		ele.sendKeys(value);
	}

	@Override
	public Get getElementText(WebElement ele) {

		String buttonText = ele.getText();
		return new Get(buttonText);
	}

	@Override
	public Verify verifyElementText(WebElement ele, String expectedText) {

		String buttonText = ele.getText();

		if (buttonText.equals(expectedText))
			return new Verify(true, Message.VERIFIED);
		else
			return new Verify(false, Message.NOT_VERIFIED);
	}

}
