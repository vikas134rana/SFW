package core.excel.parser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import core.Keyword;
import core.KeywordInfo;
import core.KeywordUtils;
import core.WebObject;
import keywords.main.WebKW;

public class WebKeywordCaller {

	public Keyword invokeMethod(KeywordInfo kwInfo) {

		List<Class<?>> typeList = kwInfo.getArgumentsType().stream().map(i -> KeywordUtils.getClass(i)).collect(Collectors.toList());

		int indexOfWebObject = typeList.indexOf(WebObject.class);

		List<Object> valuesList = new ArrayList<>(kwInfo.getArgumentsValue());

		if (indexOfWebObject != -1)
			valuesList.set(indexOfWebObject, kwInfo.getWebObject());

		Class<?>[] typeArray = typeList.toArray(new Class[typeList.size()]);
		Object[] valuesArray = valuesList.toArray(new Object[kwInfo.getArgumentsValue().size()]);

		try {

			Class<?> cl = WebKW.class;
			Method method = cl.getMethod(kwInfo.getMethodName(), typeArray);

			/*- passing null in invoke first arg because we are invoking static method and invoking static method doesn't require class object */
			// WebKW webKWObj = new WebKW();
			method.invoke(null, valuesArray);

		} catch (Exception e) {
			System.out.println("!!! Keyword can't be invoked : " + e.getMessage());
		}
		return null;
	}

	public Keyword executeKeyword(KeywordInfo kwInfo) {

		beforeKeyword(kwInfo);

		// As the name suggest execute keyword
		invokeMethod(kwInfo);

		afterKeyword(kwInfo);

		return null;
	}

	public void beforeKeyword(KeywordInfo kwInfo) {
		// operation to do before every keyword
	}

	public void afterKeyword(KeywordInfo kwInfo) {
		// screen shot logic
	}

}
