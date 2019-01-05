package core.excel.parser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.gson.Gson;

import core.KeywordArgumentType;
import core.KeywordInfo;
import core.KeywordUtils;
import core.WebKeywordCaller;
import core.WebObject;

public class KeywordInfoCreater {

	public void create() throws InvalidFormatException, IOException {

		TCGen tcExcelObj = new TCGen();
		KeywordGen keywordExcelObj = new KeywordGen();
		KeywordArgumentGen keywordArgumentExcelObj = new KeywordArgumentGen();
		TC_ORGen tcORExcelObj = new TC_ORGen();

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

			WebKeywordCaller caller = new WebKeywordCaller();
			caller.executeKeyword(kwInfo);

		}

	}

	public static void main(String[] args) {
		try {
		new KeywordInfoCreater().create();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
