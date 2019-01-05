package core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class KeywordInfo {

	private int stepNum;
	private String keywordName;
	private String methodName;
	private String keywordGuid;
	private WebObject webObject;
	private List<String> argumentsValue = new ArrayList<>();
	private List<KeywordArgumentType> argumentsType = new ArrayList<>();

	public KeywordInfo(int stepNum, String keywordName, String methodName, String keywordGuid, WebObject webObject, List<String> argumentsValue,
			List<KeywordArgumentType> argumentsType) {
		this.stepNum = stepNum;
		this.keywordName = keywordName;
		this.methodName = methodName;
		this.keywordGuid = keywordGuid;
		this.webObject = webObject;
		this.argumentsValue = argumentsValue;
		this.argumentsType = argumentsType;
	}

	public int getStepNum() {
		return stepNum;
	}

	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getKeywordGuid() {
		return keywordGuid;
	}

	public void setKeywordGuid(String keywordGuid) {
		this.keywordGuid = keywordGuid;
	}

	public WebObject getWebObject() {
		return webObject;
	}

	public void setWebObject(WebObject webObject) {
		this.webObject = webObject;
	}

	public List<String> getArgumentsValue() {
		return argumentsValue;
	}

	public void setArgumentsValue(List<String> argumentsValue) {
		this.argumentsValue = argumentsValue;
	}

	public List<KeywordArgumentType> getArgumentsType() {
		return argumentsType;
	}

	public void setArgumentsType(List<KeywordArgumentType> argumentsType) {
		this.argumentsType = argumentsType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
