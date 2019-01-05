package keywords.main.common.component;

import org.openqa.selenium.WebElement;

import keywords.core.type.Get;

public interface AbstractTextbox {
	
	void typeOnTextBox(WebElement ele,String value);
	
	Get getTextBoxValue(WebElement ele);

}
