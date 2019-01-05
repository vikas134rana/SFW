package core;

import core.WebObject.MetaType;

public class WebObjectUtils {

	public static WebObject getTopmostObject(WebObject object) {

		WebObject topmostObject = object;

		/*- move until topmost object is found */
		while (topmostObject.getParentObject() != null) {
			topmostObject = topmostObject.getParentObject();
		}

		return topmostObject;
	}

	public static WebObjectProperty getWindowTitleProp(WebObject object) {

		WebObject topmostObject = getTopmostObject(object);

		/*- if topmost object type is Html(not frame) return tilte else return null */

		String metaTypeValue = topmostObject.getMetaType().getValue();

		if (metaTypeValue != null && metaTypeValue.equals(MetaType.HTML.toString()))
			return topmostObject.getMetaTitle();

		return null;
	}

	public static WebObjectProperty getWindowTitleIndexProp(WebObject object) {

		WebObject topmostObject = getTopmostObject(object);

		/*- if topmost object type is Html(not frame) return tilte else return null */

		String metaTypeValue = topmostObject.getMetaType().getValue();

		if (metaTypeValue != null && metaTypeValue.equals(MetaType.HTML.toString()))
			return topmostObject.getMetaTitleIndex();

		return null;
	}

}
