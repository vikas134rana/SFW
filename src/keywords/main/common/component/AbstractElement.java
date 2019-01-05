package keywords.main.common.component;

import org.openqa.selenium.WebElement;

import keywords.core.type.Get;
import keywords.core.type.Verify;

public interface AbstractElement {

	void clickElement(WebElement ele);

	void typeOnElement(WebElement ele, String value);

	Get getElementText(WebElement ele);

	Verify verifyElementText(WebElement ele, String expectedText);

}
