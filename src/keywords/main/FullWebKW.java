package keywords.main;

import core.Keyword;
import core.KeywordExecutor;
import core.TextObject;
import core.WebObject;

public class FullWebKW {

	public static Keyword openBrowser(String browserName, String url) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.openBrowser(browserName, url);
			}
		}.execute();

	}

	public static Keyword closeAllBrowser() {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.closeAllBrowser();
			}
		}.execute();

	}

	/*- ========================================= ACTION/FUNTION================================================== */
	public static Keyword click(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.click(object);
			}
		}.execute();

	}

	public static Keyword clickByJS(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.clickByJS(object);
			}
		}.execute();
	}

	public static Keyword type(WebObject object, String value) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.type(object, value);
			}
		}.execute();

	}

	public static Keyword getText(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getText(object);
			}
		}.execute();

	}

	/*- ========================================= Dropdown ================================================== */

	public static Keyword selectDropdownUsingText(WebObject object, String text) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectDropdownUsingText(object, text);
			}
		}.execute();

	}

	public static Keyword selectDropdownByValue(WebObject object, String value) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectDropdownByValue(object, value);
			}
		}.execute();

	}

	public static Keyword selectDropdownByIndex(WebObject object, int index) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectDropdownByIndex(object, index);
			}
		}.execute();

	}

	public static Keyword selectDropdownByValueOrText(WebObject object, String valueOrText) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectDropdownByValueOrText(object, valueOrText);
			}
		}.execute();

	}

	public static Keyword getDropdownOptionsText(WebObject object, String delimiter) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getDropdownOptionsText(object, delimiter);
			}
		}.execute();

	}

	public static Keyword getDropdownOptionsValue(WebObject object, String delimiter) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getDropdownOptionsValue(object, delimiter);
			}
		}.execute();

	}

	// *********** Multiple Dropdwon **************

	public static Keyword deselectAll(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.deselectAll(object);
			}
		}.execute();

	}

	public static Keyword deselectDropdownUsingText(WebObject object, String text) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.deselectDropdownUsingText(object, text);
			}
		}.execute();

	}

	public static Keyword deselectDropdownByValue(WebObject object, String value) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.deselectDropdownByValue(object, value);
			}
		}.execute();

	}

	public static Keyword deselectDropdpwnByIndex(WebObject object, int index) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.deselectDropdpwnByIndex(object, index);
			}
		}.execute();

	}

	public static Keyword getDropdownFirstSelectedText(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getDropdownFirstSelectedText(object);
			}
		}.execute();

	}

	public static Keyword getDropdownFirstSelectedValue(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getDropdownFirstSelectedValue(object);
			}
		}.execute();

	}

	public static Keyword getDropdownAllSelectedText(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getDropdownAllSelectedValue(object);
			}
		}.execute();

	}

	public static Keyword getDropdownAllSelectedValue(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.getDropdownAllSelectedValue(object);
			}
		}.execute();

	}

	public static Keyword selectDropdownMultipleText(WebObject object, String texts, String delimiter, boolean canContinue) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectDropdownMultipleText(object, texts, delimiter, canContinue);
			}
		}.execute();

	}

	// ====================================================================================

	public static Keyword rightClick(WebObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.rightClick(object);
			}
		}.execute();

	}

	public static Keyword acceptAlert() {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.acceptAlert();
			}
		}.execute();

	}

	/*- ========================================= By_Text ================================================== */

	public static Keyword clickByText(TextObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.clickByText(object);
			}
		}.execute();
	}

	public static Keyword typeByText(TextObject object, boolean before, String textToType) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.typeByText(object, before, textToType);
			}
		}.execute();

	}

	public static Keyword selectDropdownByText(TextObject object, boolean before, String textToSelect) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectDropdownByText(object, before, textToSelect);
			}
		}.execute();

	}

	public static Keyword selectCheckboxByText(TextObject object, boolean before) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectCheckboxByText(object, before);
			}
		}.execute();

	}

	public static Keyword selectRadioButtonByText(TextObject object, boolean before) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.selectRadioButtonByText(object, before);
			}
		}.execute();

	}

	public static Keyword mouseHoverOnText(TextObject object) {

		return new KeywordExecutor() {

			@Override
			public Keyword innerExecute() {
				return WebKW.mouseHoverOnText(object);
			}
		}.execute();

	}

}
