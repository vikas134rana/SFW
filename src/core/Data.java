package core;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Data {

	public static String getString(Object object) {
		if (object == null)
			return null;
		return object.toString();
	}

	public static int getInt(Object object) {
		if (object == null)
			return 0;
		return NumberUtils.toInt(object.toString());
	}

	public static boolean getBoolean(Object object) {
		if (object == null)
			return false;
		return BooleanUtils.toBoolean(object.toString());
	}

	public static long getLong(Object object) {
		if (object == null)
			return 0l;
		return NumberUtils.toLong(object.toString());
	}

	public static double getDouble(Object object) {
		if (object == null)
			return 0.0d;
		return NumberUtils.toDouble(object.toString());
	}

}
