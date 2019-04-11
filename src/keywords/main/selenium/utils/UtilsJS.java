package keywords.main.selenium.utils;

import org.openqa.selenium.WebElement;

import keywords.main.js.JSE;

public class UtilsJS {

	public static boolean hasChildTextNode(WebElement ele) {
		// @formatter:off
		String script = "function hasChildTextNode(ele){\r\n" + 
				"	var childCount = ele.childNodes.length;\r\n" + 
				"\r\n" + 
				"	for(var i=0;i<childCount;i++){\r\n" + 
				"		var childNode = ele.childNodes[i];\r\n" + 
				"		//console.log(childNode);\r\n" + 
				"		\r\n" + 
				"		if(childNode.nodeType==3)\r\n" + 
				"			return true;\r\n" + 
				"		\r\n" + 
				"		var isChildTextNode = hasChildTextNode(childNode);\r\n" + 
				"		if(isChildTextNode)\r\n" + 
				"			return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		// @formatter:on
		
		
		boolean hasChildTextNode = (boolean) JSE.executeScript(script+" return hasChildTextNode(arguments[0]);", ele);
		
		return hasChildTextNode;
	}

}
