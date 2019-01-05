package keywords.main.js.scripts;

public class ActionScript {

	// @formatter:off

	public static String click() {
		return " arguments[0].click(); ";
	}
	
	public static String type(String value) {
		return " arguments[0].value='"+value+"'; ";
	}
	
	// @formatter:on

}
