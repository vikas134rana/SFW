package core;

public class KeywordUtils {

	public static KeywordArgumentType getKeywordArgType(String argType) {

		switch (argType) {

		case "WebObject":
			return KeywordArgumentType.WEB_OBJECT;

		case "String":
			return KeywordArgumentType.STRING;

		case "Integer":
			return KeywordArgumentType.INTEGER;

		case "Long":
			return KeywordArgumentType.LONG;

		case "Boolean":
			return KeywordArgumentType.BOOLEAN;

		case "Double":
			return KeywordArgumentType.DOUBLE;

		case "Float":
			return KeywordArgumentType.FLOAT;

		}
		return null;

	}

	public static Class getClass(KeywordArgumentType argType) {

		switch (argType) {

		case WEB_OBJECT:
			return WebObject.class;

		case STRING:
			return String.class;

		case INTEGER:
			return Integer.class;

		case LONG:
			return Long.class;

		case BOOLEAN:
			return Boolean.class;

		case DOUBLE:
			return Double.class;

		case FLOAT:
			return Float.class;

		}
		return null;

	}

}
