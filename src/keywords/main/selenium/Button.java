package keywords.main.selenium;

import org.openqa.selenium.WebElement;

import keywords.core.other.Message;
import keywords.core.type.Get;
import keywords.core.type.Verify;
import keywords.main.common.component.AbstractButton;

public class Button implements AbstractButton {

	static Button obj;

	public static Button getInstance() {
		if (obj == null)
			obj = new Button();

		return obj;
	}
	
	@Override
	public void clickButton(WebElement ele) {
		
		ele.click();
	}

	@Override
	public Get getButtonText(WebElement ele) {
		
		String buttonText = ele.getText();
		return new Get(buttonText);
	}

	@Override
	public Verify verifyButtonText(WebElement ele, String expectedText) {
		
		String buttonText = getButtonText(ele).getValue();
		
		if (buttonText.equals(expectedText))
			return new Verify(true, Message.VERIFIED);
		else
			return new Verify(false, Message.NOT_VERIFIED);
	}

}
