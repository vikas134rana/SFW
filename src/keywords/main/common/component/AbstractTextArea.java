package keywords.main.common.component;

import org.openqa.selenium.WebElement;

public interface AbstractTextArea {

	void typeOnTextarea(WebElement ele, String value);

	void getTextareaText(WebElement ele);

	void verifyTextareaText(WebElement ele, String expectedText);

}
