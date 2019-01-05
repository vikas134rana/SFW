package keywords.main.common.component;

import org.openqa.selenium.WebElement;

import keywords.core.type.Get;
import keywords.core.type.Verify;

public interface AbstractButton {

	void clickButton(WebElement ele);

	Get getButtonText(WebElement ele);

	Verify verifyButtonText(WebElement ele, String expectedText);

}
