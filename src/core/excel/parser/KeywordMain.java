package core.excel.parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.gson.Gson;

import core.KeywordArgumentType;
import core.KeywordInfo;
import core.KeywordUtils;
import core.WebObject;
import keywords.main.WebKW;

public class KeywordMain {

	public void execute() throws InvalidFormatException, IOException {

		TCGen tcExcelObj = new TCGen();
		KeywordGen keywordExcelObj = new KeywordGen();
		KeywordArgumentGen keywordArgumentExcelObj = new KeywordArgumentGen();
		TC_ORGen tcORExcelObj = new TC_ORGen();
		tcExcelObj.generate(1);

		for (int i = 1; i <= tcExcelObj.getLastRow(); i++) {

			tcExcelObj.generate(i);
			String keywordName = tcExcelObj.getKeywordName();
			List<String> argumentsValue = tcExcelObj.getArgumentsValue();

			keywordExcelObj.generate(keywordName);
			String methodName = keywordExcelObj.getMethodName();
			String keywordGuid = keywordExcelObj.getKeywordGuid();

			keywordArgumentExcelObj.generate(keywordGuid);
			List<KeywordArgumentType> argumentsType = keywordArgumentExcelObj.getArgumentTypeList().stream().map(str -> KeywordUtils.getKeywordArgType(str))
					.collect(Collectors.toList());

			int objectIndex = argumentsType.indexOf(KeywordArgumentType.WEB_OBJECT);
			if (objectIndex != -1) {
				String objectName = argumentsValue.get(objectIndex);
				tcORExcelObj.generate(objectName);
			}
			String json = tcORExcelObj.getJson();

			System.out.println("### STEP: " + i);
			System.out.println("### keywordName: " + keywordName);
			System.out.println("### argumentsValue: " + argumentsValue);
			System.out.println("### argumentsType: " + argumentsType);
			System.out.println("### methodName: " + methodName);
			System.out.println("### keywordGuid: " + keywordGuid);
			System.out.println("### json: " + json);

			Gson gson = new Gson();
			WebObject webObject = gson.fromJson(json, WebObject.class);

			KeywordInfo kwInfo = new KeywordInfo(i, keywordName, methodName, keywordGuid, webObject, argumentsValue, argumentsType);
			System.out.println("****************************************************************");
			System.out.println(kwInfo);
			// System.out.println("WebObject -> " + webObject);
			System.out.println("****************************************************************");

			try {
				executeKeyword(kwInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void executeKeyword(KeywordInfo keywordInfo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		List<Class<?>> typeList = keywordInfo.getArgumentsType().stream().map(i -> KeywordUtils.getClass(i)).collect(Collectors.toList());

		int indexOfWebObject = typeList.indexOf(WebObject.class);

		List<Object> valuesList = new ArrayList<>(keywordInfo.getArgumentsValue());

		if (indexOfWebObject != -1)
			valuesList.set(indexOfWebObject, keywordInfo.getWebObject());

		Class<?>[] typeArray = typeList.toArray(new Class[typeList.size()]);
		Object[] valuesArray = valuesList.toArray(new Object[keywordInfo.getArgumentsValue().size()]);

		try {

			System.out.println("Values: " + keywordInfo.getArgumentsValue());
			System.out.println("Types: " + keywordInfo.getArgumentsType());
			System.out.println("TypesList: " + typeList);
			System.out.println("ValuesList: " + valuesList);

			Class<?> cl = WebKW.class;
			Method method = cl.getMethod(keywordInfo.getMethodName(), typeArray);

			/*- passing null in invoke first arg because we are invoking static method and invoking static method doesn't require class object */
			// WebKW webKWObj = new WebKW();
			method.invoke(null, valuesArray);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		KeywordMain obj = new KeywordMain();
		obj.execute();
		System.out.println("DONE");
	}

}
