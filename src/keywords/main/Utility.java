package keywords.main;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Utility {

	public static void waitUntil(int timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
		}
	}

	public static String getDelimiter(String delimiter) {
		if (delimiter.isEmpty())
			return ";";
		return delimiter;
	}

	public static String getDelimiter() {
		return ";";
	}

	public static <T extends Object> List<T> removeDuplicate(List<T> eles) {
		Set<T> set = new LinkedHashSet<T>(eles);
		eles.clear();
		eles.addAll(set);
		return eles;
	}

	public static String replaceNBSPWithSpace(String text) {
		text = text.trim();
		text = text.replace(' ', '\u00A0');
		return text;
	}

	public static <T extends Object> T getItemAt(List<T> eles, int index) {

		try {
			return eles.get(index);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}

		return null;
	}

}
