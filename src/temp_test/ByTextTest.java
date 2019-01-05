package temp_test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import keywords.core.Driver;
import keywords.main.Utility;
import keywords.main.WebKW;
import keywords.main.selenium.TextKeywords;
import keywords.main.selenium.utils.FinderUtils;
import keywords.main.selenium.utils.XpathUtils;

public class ByTextTest {

	public static void main(String[] args) {

		byTextBeforeAfterTest();
	}

	static void faltuTest() {

		WebKW.openBrowser("chrome", "http://www.asuspromo.in/");

		WebElement divEle = FinderUtils.findElement("//div[@class='navigator']");

		FinderUtils.findElement(divEle, ".//li");
	}

	static void asusRegistrationForm() {

		WebKW.openBrowser("chrome", "http://www.asuspromo.in/");

		TextKeywords.clickByText("REGISTER", 0, false, null, null);

		Utility.waitUntil(1000);

		TextKeywords.selectDropdownByText("STATE ", 0, false, null, null, false, "Delhi");
		TextKeywords.typeByText("MODEL NO", 0, false, "", "", false, "X510UF-EJ592T");
		TextKeywords.typeByText("SERIAL NUMBER", 0, false, "", "", false, "JAN0CX01C40941B");

		FinderUtils.findElement("//*[@name='txtPurchasedate']").click();
		FinderUtils.findElement("//*[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']").click();

		TextKeywords.selectRadioButtonByText("New Registration", 0, false, null, null, true);
		TextKeywords.clickByText("SUBMIT", 0, false, null, null);
		TextKeywords.clickByText("Select", 0, false, null, null);
		TextKeywords.selectDropdownByText("HOW DID YOU KNOW ABOUT THE PRODUCT?", 0, false, null, null, false, "Google");
		TextKeywords.selectDropdownByText("WHERE DID YOU BUY THE PRODUCT?", 0, false, null, null, false,
				"ASUS EXCLUSIVE STORE");
		TextKeywords.typeByText("STORE NAME", 0, false, "", "", false, "LAPCOM PERIPHERALS");
		TextKeywords.typeByText("PURCHASE PRICE", 0, false, "", "", false, "50000");

		TextKeywords.typeByText("ENTER FULL NAME", 0, false, "", "", false, "VIKAS RANA");
		TextKeywords.typeByText("ENTER YOUR MOBILE NUMBER", 0, false, "", "", false, "9958350998");
		TextKeywords.typeByText("ENTER YOUR EMAIL ID", 0, false, "", "", false, "VIKAS134RANA@GMAIL.COM");
		TextKeywords.typeByText("ADDRESS", 0, false, "", "", false, "F108 , ROOM NO P1, KATWARIA SARAI");
		TextKeywords.typeByText("LANDMARK", 0, false, "", "", false, "NEAR PANCHAYATI GHAR");
		TextKeywords.selectDropdownByText("STATE ", 1, false, null, null, false, "Delhi");
		TextKeywords.selectDropdownByText("CITY", 0, false, null, null, false, "New delhi");
		TextKeywords.typeByText("PINCODE", 0, false, "", "", false, "110016");
		TextKeywords.selectDropdownByText("AGE", 0, false, null, null, false, "21-25");
		TextKeywords.selectDropdownByText("PROFESSION", 0, false, null, null, false, "IT Professional");
		TextKeywords.selectCheckboxByText("I agree to have Read", 0, true, null, null, true);
	}

	static void byTextBeforeAfterTest() {

		WebKW.openBrowser("chrome", "file:///D:/Work/EclipseWorkspace/plugin/other/TextKeywords.html");

		/*- action on second column ele */
		TextKeywords.typeByText("LastName", 0, false, "Gender", "", false, "xyz");
		TextKeywords.selectRadioButtonByText("Female", 0, false, "", "Name", true);
		TextKeywords.selectCheckboxByText("Automation ", 0, false, "", "Profile", true);
		TextKeywords.selectDropdownByText("Country", 0, false, "", "Address", false, "UK");

		Utility.waitUntil(5000);

		/*- action on first column ele */
		TextKeywords.typeByText("FirstName", 0, false, "", "", false, "vikas");
		TextKeywords.selectRadioButtonByText("Male", 0, false, null, null, true);
		TextKeywords.selectCheckboxByText("Manual", 0, false, null, null, true);
		TextKeywords.selectDropdownByText("Continent", 0, false, null, null, false, "Africa");

		Utility.waitUntil(5000);
	}

	static void byTextTest() {

		WebKW.openBrowser("chrome", "file:///D:/Work/EclipseWorkspace/plugin/other/TextKeywords.html");

		TextKeywords.selectDropdownByText("Log In", 0, false, null, null, true, "UK");

		/*- action on second column ele */
		TextKeywords.typeByText("LastName", 0, false, "", "", false, "xyz");
		TextKeywords.typeByText("YOUR TEXT", 0, false, "", "", false, "comment");
		TextKeywords.selectRadioButtonByText("Female", 0, false, null, null, true);
		TextKeywords.selectCheckboxByText("Automation ", 0, false, null, null, true);
		TextKeywords.selectDropdownByText("Country", 0, false, null, null, false, "UK");

		Utility.waitUntil(5000);

		/*- action on first column ele */
		TextKeywords.typeByText("FirstName", 0, false, "", "", false, "vikas");
		TextKeywords.selectRadioButtonByText("Male", 0, false, null, null, true);
		TextKeywords.selectCheckboxByText("Manual", 0, false, null, null, true);
		TextKeywords.selectDropdownByText("Continent", 0, false, null, null, false, "Africa");

		Utility.waitUntil(5000);

		TextKeywords.mouseHoverOnText("Danger", 0, false, null, null);

		/*- action on label (before or after which is not present) (-ve scenario) */
		TextKeywords.typeByText("FirstName", 0, false, "", "", true, "vikas");
		TextKeywords.selectRadioButtonByText("Female", 0, false, null, null, false);
		TextKeywords.selectCheckboxByText("Automation", 0, false, null, null, false);
		TextKeywords.selectDropdownByText("Continent", 0, false, null, null, true, "Africa");
	}

	static void textMethodTest() {
		WebKW.openBrowser("chrome", "http://toolsqa.com/automation-practice-form/");

		List<WebElement> eles = Driver.getDriver().findElements(
				By.xpath("//*[@id='content']/div[1]/div/div/div/div[2]/div/form/fieldset/div[20]//text()"));

		for (WebElement ele : eles) {
			System.out.println("TAG : " + ele.getTagName());
		}
	}

	static void toolsqaByText() {

		WebKW.openBrowser("chrome", "http://toolsqa.com/automation-practice-form/");

		TextKeywords.typeByText("First name:", 0, false, "", "", false, "vikas");
		TextKeywords.typeByText("Last name:", 0, false, "", "", false, "rana");
		// TextKeywords.selectRadioButtonByText("Male", 0, false, null, null, true);
		// TextKeywords.selectRadioButtonByText("5", 0, false, null, null, true);
		TextKeywords.typeByText("Date:", 0, false, "", "", false, "05-11-1999");
		TextKeywords.selectCheckboxByText("Automation Tester", 0, false, null, null, true);
		// TextKeywords.selectCheckboxByText("Selenium Webdriver", 0, false, null, null,
		// true);
		TextKeywords.selectDropdownByText("Continents", 0, false, null, null, false, "Australia");
		TextKeywords.selectDropdownByText("Selenium Commands", 0, false, null, null, false, "WebElement Commands");
		// TextKeywords.clickByText("Button", 0, false, null, null);

	}

	static void salesforceByText() {

		WebKW.openBrowser("chrome", "https://srikantp-dev-ed.my.salesforce.com/001/o");

		Driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		/*- Login Credentials */
		TextKeywords.typeByText("Username", 0, false, null, null, false, "srikant@xyz.com");
		TextKeywords.typeByText("Password", 0, false, null, null, false, "Crestech8");
		TextKeywords.clickByText("Log In", 0, false, null, null);

		Utility.waitUntil(5000);

		TextKeywords.clickByText("Close", 0, false, null, null);
		TextKeywords.clickByText("New", 0, false, null, null);

		/*- Account Information */

		TextKeywords.typeByText("Account Name", 0, false, null, null, false, "john");
		TextKeywords.typeByText("Parent Account", 0, false, null, null, false, "john parent");
		TextKeywords.typeByText("Account Number	", 0, false, null, null, false, "101");
		TextKeywords.typeByText("Account Site", 0, false, null, null, false, "john@wwe.com");
		TextKeywords.selectDropdownByText("Type", 0, false, "", "", false, "Customer - Channel");
		TextKeywords.selectDropdownByText("Industry", 0, false, "", "", false, "Banking");
		TextKeywords.typeByText("Annual Revenue", 0, false, null, null, false, "90000000");
		TextKeywords.selectDropdownByText("Rating", 0, false, "", "", false, "Warm");
		TextKeywords.typeByText("Phone", 0, false, null, null, false, "9876543210");
		TextKeywords.typeByText("Fax", 0, false, null, null, false, "0123456789");
		TextKeywords.typeByText("Website", 0, false, null, null, false, "wwe.com");
		TextKeywords.typeByText("Ticker Symbol", 0, false, null, null, false, "u cant see me");
		TextKeywords.selectDropdownByText("Ownership", 0, false, "", "", false, "Private");
		TextKeywords.typeByText("Employees", 0, false, null, null, false, "999");
		TextKeywords.typeByText("SIC Code", 0, false, null, null, false, "7639");

		/*- Address Information */

		TextKeywords.typeByText("Billing Street", 0, false, null, null, false, "Gali no 4");
		TextKeywords.typeByText("Billing City", 0, false, null, null, false, "Noida");
		TextKeywords.typeByText("Billing State/Province", 0, false, null, null, false, "UP");
		TextKeywords.typeByText("Billing Zip/Postal Code", 0, false, null, null, false, "240108");
		TextKeywords.typeByText("Billing Country", 0, false, null, null, false, "Nepal");
		TextKeywords.typeByText("Shipping Street", 0, false, null, null, false, "MG road");
		TextKeywords.typeByText("Shipping City", 0, false, null, null, false, "Bangaluru");
		TextKeywords.typeByText("Shipping State/Province", 0, false, null, null, false, "Kar-natak");
		TextKeywords.typeByText("Shipping Zip/Postal Code", 0, false, null, null, false, "239007");
		TextKeywords.typeByText("Shipping Country", 0, false, null, null, false, "Bhutan");

		/*- Additional Information */

		TextKeywords.selectDropdownByText("Customer Priority", 0, false, "", "", false, "High");
		TextKeywords.typeByText("SLA Expiration Date", 0, false, null, null, false, "12/21/2018");
		TextKeywords.typeByText("Number of Locations", 0, false, null, null, false, "5");
		TextKeywords.selectDropdownByText("Active", 0, false, "", "", false, "No");
		TextKeywords.selectDropdownByText("SLA", 0, false, "", "", false, "Platinum");
		TextKeywords.typeByText("SLA Serial Number", 0, false, null, null, false, "57687");
		TextKeywords.selectDropdownByText("Upsell Opportunity	", 0, false, "", "", false, "Maybe");

		/*- Description Information */

		TextKeywords.typeByText("Description Information", 0, false, null, null, false, "Best Wrestler bhidu.");
	}

}
