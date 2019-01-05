package core;

public enum KeywordArgumentType {

	WEB_OBJECT			("WebObject"),
	STRING				("String"),
	INTEGER				("Integer"),
	BOOLEAN				("Boolean"),
	DOUBLE				("Double"),
	LONG				("Long"),
	FLOAT				("Float");

	private String value;

	private KeywordArgumentType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
