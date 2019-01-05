package keywords.main.selenium.utils;

import org.openqa.selenium.support.ui.Quotes;

import keywords.core.WebControls;

public class XpathUtils {

	// translate xpath property to lowercase
	public static String propXpath_NS_CI(String attrName, String attrValue) {
		return "normalize-space(translate(@" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) = "
				+ Quotes.escape(attrValue.toLowerCase()) + "";
	}

	// translate text xpath to lowercase
	public static String cPropXpath_NS_CI(String attrName, String attrValue) {
		return "contains(normalize-space(translate(@" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) , "
				+ Quotes.escape(attrValue.toLowerCase()) + ")";
	}

	/*- handled space, case in text */
	public static String textXpath_NS_CI(String text) {
		return "normalize-space(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) = " + Quotes.escape(text.toLowerCase()) + "";
	}

	/*- handled contains, space, case in text */
	public static String cTextXpath_NS_CI(String text) {
		return "contains(normalize-space(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) , " + Quotes.escape(text.toLowerCase())
				+ ")";
	}

	/*- ************************************************************************************* */

	// translate xpath property to lowercase
	public static String propXpath_NS_CS(String attrName, String attrValue) {
		return "normalize-space(@" + attrName + ") = " + Quotes.escape(attrValue) + "";
	}

	// translate text xpath to lowercase
	public static String cPropXpath_NS_CS(String attrName, String attrValue) {
		return "contains(normalize-space(@" + attrName + ") , " + Quotes.escape(attrValue) + ")";
	}

	/*- handled space, case in text */
	public static String textXpath_NS_CS(String textValue) {
		return "normalize-space(text()) = " + Quotes.escape(textValue) + "";
	}

	/*- handled contains, space, case in text */
	public static String cTextXpath_NS_CS(String textValue) {
		return "contains(normalize-space(text()) , " + Quotes.escape(textValue) + ")";
	}

	/*- ************************************************************************************* */

	public static String cTextXpath_CI(String text) {
		return "contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') , " + Quotes.escape(text.toLowerCase()) + ")";
	}

	public static String textXpath_CI(String text) {
		return "translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = " + Quotes.escape(text.toLowerCase());
	}

	public static String cDotXpath_CI(String text) {
		return "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') , " + Quotes.escape(text.toLowerCase()) + ")";
	}

	public static String dotXpath_CI(String text) {
		return "translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = " + Quotes.escape(text.toLowerCase());
	}

	/*- ************************************************************************************* */

	public static String anyAttrXpath_CI(String xttrName, String attrValue,boolean contains) {
		return contains==true? cAnyAttrXpath_CI(xttrName, attrValue):anyAttrXpath_CI(xttrName, attrValue);
	}
	
	public static String cAnyAttrXpath_CI(String xttrName, String attrValue) {
		return "contains(translate(" + xttrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') , " + Quotes.escape(attrValue.toLowerCase())
				+ ")";
	}

	public static String anyAttrXpath_CI(String xttrName, String attrValue) {
		return "translate(" + xttrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = " + Quotes.escape(attrValue.toLowerCase());
	}
	
	public static String anyAttrXpath_NS_CI(String xttrName, String attrValue,boolean contains) {
		return contains==true? cAnyAttrXpath_NS_CI(xttrName, attrValue):anyAttrXpath_NS_CI(xttrName, attrValue);
	}

	public static String cAnyAttrXpath_NS_CI(String xttrName, String attrValue) {
		return "contains(normalize-space(translate(" + xttrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) , "
				+ Quotes.escape(attrValue.toLowerCase()) + ")";
	}

	public static String anyAttrXpath_NS_CI(String xttrName, String attrValue) {
		return "normalize-space(translate(" + xttrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) = "
				+ Quotes.escape(attrValue.toLowerCase());
	}
	
	public static String anyAttrXpath_NS_CI_NBSP(String xttrName, String attrValue,boolean contains) {
		return contains==true? cAnyAttrXpath_NS_CI_NBSP(xttrName, attrValue):anyAttrXpath_NS_CI_NBSP(xttrName, attrValue);
	}
	
	public static String cAnyAttrXpath_NS_CI_NBSP(String xttrName, String attrValue) {
		return "contains(normalize-space(translate(" + xttrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00a0','abcdefghijklmnopqrstuvwxyz ')) , "
				+ Quotes.escape(attrValue.toLowerCase()) + ")";
	}
	
	public static String anyAttrXpath_NS_CI_NBSP(String xttrName, String attrValue) {
		return "normalize-space(translate(" + xttrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00a0','abcdefghijklmnopqrstuvwxyz ')) = "
				+ Quotes.escape(attrValue.toLowerCase());
	}

	/*- ************************************************************************************* */

	public static String xpathDirection(boolean before) {

		String xpathDirection = "";

		if (before)
			xpathDirection = ".//preceding::";
		else
			xpathDirection = ".//following::";
		return xpathDirection;
	}

	public static String typableEleXpath() {

		return "*[self::input[not(@type) or @type='' or @type='text' or @type='email' or @type='password' or @type='number' or @type='url'] or self::textarea]";
	}

	public static String dropdownXpath() {

		return "select";
	}

	public static String checkboxXpath() {

		return "input[@type='checkbox']";
	}

	public static String radioButtonXpath() {

		return "input[@type='radio']";
	}

	public static String componentXpathWithDirection(boolean before, WebControls webControls) {

		String componentXpath = "";

		switch (webControls) {
		case TYPABLE:
			componentXpath = XpathUtils.typableEleXpath();
			break;

		case CHECKBOX:
			componentXpath = XpathUtils.checkboxXpath();
			break;

		case RADIO:
			componentXpath = XpathUtils.radioButtonXpath();
			break;

		case DROPDOWN:
			componentXpath = XpathUtils.dropdownXpath();
			break;
		}

		return XpathUtils.xpathDirection(before) + componentXpath;
	}
}
