package keywords.main.selenium.utils;

import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Node;
import org.openqa.selenium.support.ui.Quotes;

import keywords.core.WebControls;

/*- 
 * 1. NS 	- handled normalize-space 		(for eg in DOM ' FirstName ' and 'FirstName' is same )
 * 			  Normalize-Space -> The normalize-space function strips leading and trailing white-space from a string,
 * 			  replaces sequences of whitespace characters by a single space, and returns the resulting string.
 * 			  SYNTAX -> normalize-space( [string] )		 
 * 			  LINK -> https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/normalize-space
 * 
 * 2. CI 	- handled case insensitive		(for eg in DOM 'FirstName' and 'firstname' is same )
 * 			  There is no direct case insensitive method in xpath. Using translate method to deal with this situation.
 * 			  Translate -> The translate function evaluates a string and a set of characters to translate and returns the translated string.
 * 			  SYNTAX -> translate(anyString ,abc ,XYZ )
 * 						where in String 'anyString'  a is replace with X , b is replace with Y and so on.
 * 						using translate we can achieve lowercase, uppercase etc 	
 * 			  LINK -> https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/translate	
 * 
 * 
 * 3. NBSP - handled non-breaking space	(for eg in DOM '&nbsp;FirstName' and ' FirstName' is same )
 * 			 There is no direct NBSP method in xpath. Using translate method to deal with this situation.
 * 			 SYNTAX -> translate(anyString ,'\u00a0' ,' ' )
 * 						where in String 'anyString' '\u00a0'(nbsp space unicode in java) is replace with normal-space.
 * 			 LINK -> https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/translate
 */

/*- 
 *  HTML 		- attribute
 *  JavaScript 	- property
 * */

public class XpathUtils {

	/*- ************************************************************************************* */

	// NS
	public static String attrXpath_NS(String attrName, String attrValue, boolean contains) {
		return contains == true ? attrXpath_NS(attrName, attrValue) : cAttrXpath_NS(attrName, attrValue);
	}

	public static String attrXpath_NS(String attrName, String attrValue) {
		return "normalize-space(" + attrName + ") = " + Quotes.escape(attrValue.toLowerCase());
	}

	public static String cAttrXpath_NS(String attrName, String attrValue) {
		return "contains(normalize-space(" + attrName + ") , " + Quotes.escape(attrValue.toLowerCase()) + ")";
	}

	// CI
	public static String attrXpath_CI(String attrName, String attrValue, boolean contains) {
		return contains == true ? cAttrXpath_CI(attrName, attrValue) : attrXpath_CI(attrName, attrValue);
	}

	public static String cAttrXpath_CI(String attrName, String attrValue) {
		return "contains(translate(" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') , " + Quotes.escape(attrValue.toLowerCase())
				+ ")";
	}

	public static String attrXpath_CI(String attrName, String attrValue) {
		return "translate(" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = " + Quotes.escape(attrValue.toLowerCase());
	}

	// NS_CI
	public static String attrXpath_NS_CI(String attrName, String attrValue, boolean contains) {
		return contains == true ? cAttrXpath_NS_CI(attrName, attrValue) : attrXpath_NS_CI(attrName, attrValue);
	}

	public static String cAttrXpath_NS_CI(String attrName, String attrValue) {
		return "contains(normalize-space(translate(" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) , "
				+ Quotes.escape(attrValue.toLowerCase()) + ")";
	}

	public static String attrXpath_NS_CI(String attrName, String attrValue) {
		return "normalize-space(translate(" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) = "
				+ Quotes.escape(attrValue.toLowerCase());
	}

	// NS_CI_NBSP
	public static String attrXpath_NS_CI_NBSP(String attrName, String attrValue, boolean contains) {
		return contains == true ? cAttrXpath_NS_CI_NBSP(attrName, attrValue) : attrXpath_NS_CI_NBSP(attrName, attrValue);
	}

	public static String cAttrXpath_NS_CI_NBSP(String attrName, String attrValue) {
		return "contains(normalize-space(translate(" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00a0','abcdefghijklmnopqrstuvwxyz ')) , "
				+ Quotes.escape(attrValue.toLowerCase()) + ")";
	}

	public static String attrXpath_NS_CI_NBSP(String attrName, String attrValue) {
		return "normalize-space(translate(" + attrName + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00a0','abcdefghijklmnopqrstuvwxyz ')) = "
				+ Quotes.escape(attrValue.toLowerCase());
	}

	/*- ************************************************************************************* */

	public static String createXpath(Node n) {
		int index = 1;
		String xpath = "";
		while (!n.nodeName().equals("body")) {
			index = elementIndex(n);
			xpath = "/" + n.nodeName() + "[" + index + "]" + xpath;
			n = n.parentNode();
		}
		return "//body" + xpath;
	}

	public static int elementIndex(Node n) {
		int index = 0;
		String nodeName = n.nodeName();
		while (n != null) {
			if (n.nodeName().equals(nodeName)) {
				index++;
			}
			n = n.previousSibling();
		}
		return index;
	}

	/*- ************************************************************************************* */

	/**
	 * 1. Before = null -> (.//)
	 * 
	 * 2. Before = true -> (.//preceding::)
	 * 
	 * 3. Before = false -> (.//following::)
	 * 
	 * @param before
	 * @return
	 */
	public static String xpathDirection(Boolean before) {

		String xpathDirection = ".//";

		if (before != null) {
			if (before)
				xpathDirection = ".//preceding::";
			else
				xpathDirection = ".//following::";
		}

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

	public static String componentXpathWithDirection(Boolean before, WebControls webControls) {

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
