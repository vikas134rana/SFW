package temp_test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import keywords.core.Driver;
import keywords.main.WebKW;
import keywords.main.selenium.Dropdown;
import keywords.main.selenium.utils.FinderUtils;

public class DropdownTest {

	public static void main(String[] args) {

		// Open Browser
		WebKW.openBrowser("chrome", "http://toolsqa.com/automation-practice-form/");

		// set Implicit Wait to find Element
		Driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		WebElement dropdownEle = FinderUtils.findElement("//*[@id='continents']");
		WebElement multiDropdownEle = FinderUtils.findElement("//*[@id='selenium_commands']");

		Dropdown.selectByText(dropdownEle, "Europe");

		Dropdown.selectByIndex(dropdownEle, 2);

		try {
			Dropdown.selectByValue(dropdownEle, "Europe");
		} catch (Exception e) {
			System.out.println("FAIL : " + e.getMessage());
		}

		Dropdown.selectByTexts(multiDropdownEle, "a;Navigation Commands;WebElement Commands", "", true);

	}

}
