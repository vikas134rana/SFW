package keywords.main.js;

import org.openqa.selenium.WebElement;

import keywords.core.Driver;
import keywords.core.other.Message;
import keywords.core.type.Get;
import keywords.core.type.Verify;
import keywords.main.common.component.AbstractButton;
import keywords.main.js.scripts.ActionScript;
import keywords.main.js.scripts.Script;

public class ButtonJS implements AbstractButton {

	@Override
	public void clickButton(WebElement ele) {

		JSE.get(Driver.getDriver()).executeScript(ActionScript.click(), ele);
	}

	@Override
	public Get getButtonText(WebElement ele) {

		String buttonText = (String)JSE.get(Driver.getDriver()).executeScript(Script.textContent(), ele);
		return new Get(buttonText);
	}

	@Override
	public Verify verifyButtonText(WebElement ele, String expectedText) {
		
		String buttonText = (String)JSE.get(Driver.getDriver()).executeScript(Script.textContent(), ele);
		
		if (buttonText.equals(expectedText))
			return new Verify(true, Message.VERIFIED);
		else
			return new Verify(false, Message.NOT_VERIFIED);
	}

}
