package core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ObjectProperty")
public class WebObjectProperty {

	private String name;
	private String value;
	private boolean isUsed;
	private boolean isRegex;

	public WebObjectProperty() {
	}

	public WebObjectProperty(String name, String value, boolean isUsed, boolean isRegex) {
		this.name = name;
		this.value = value;
		this.isUsed = isUsed;
		this.isRegex = isRegex;
	}

	@XmlElement(name = "ObjectPropertyName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "ObjectPropertyValue")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement(name = "ObjectPropertyIsUsed")
	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	@XmlElement(name = "ObjectPropertyIsRegex")
	public boolean isRegex() {
		return isRegex;
	}

	public void setRegex(boolean isRegex) {
		this.isRegex = isRegex;
	}

	@Override
	public String toString() {
		return "[" + this.name + ", " + this.value + ", " + this.isUsed + " , " + this.isRegex + "] -> isUsable: " + isUsable();
	}

	public boolean isValueNullOrEmpty() {
		if (this == null || this.value == null || this.value.isEmpty())
			return true;
		return false;
	}

	public boolean isUsable() {
		if (!isValueNullOrEmpty() && this.isUsed)
			return true;
		return false;
	}

	public boolean isUsableIgnoringEmptyValue() {
		if (this != null && this.value != null && this.isUsed)
			return true;
		return false;
	}
}
