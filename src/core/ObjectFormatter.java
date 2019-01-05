package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ObjectFormatter {

	WebObject webObject;

	public static void main(String[] args) throws JAXBException, FileNotFoundException {

		new ObjectFormatter().format();

		System.out.println("DONE");

	}

	public void format() throws JAXBException {

		formatAndSetAllWebObjectProperties();

	}

	public void formatAndSetAllWebObjectProperties() throws JAXBException {
		JAXBContext jaxbObj = JAXBContext.newInstance(WebObject.class);
		Unmarshaller unmarshaller = jaxbObj.createUnmarshaller();
		webObject = (WebObject) unmarshaller.unmarshal(new File("D:\\Soft\\workspace\\plugin\\other\\abc.xml"));
	}

	public void formatAndSetEveryWebObjectProperty() throws JAXBException {

		formatAndSetAllWebObjectProperties();

		for (WebObjectProperty wop : webObject.getAllWebObjectProperty()) {

			switch (wop.getName()) {

			case "id":
				webObject.setId(wop);
				break;

			case "name":
				webObject.setName(wop);
				break;

			case "innerText":
				webObject.setInnerText(wop);
				break;

			case "textContent":
				webObject.setTextContent(wop);
				break;

			case "class":
				webObject.setClassName(wop);
				break;

			}

			if (wop.getName().trim().contains("xpath"))
				webObject.addXpaths(wop);

			else if (wop.getName().trim().contains("cssselector"))
				webObject.addCssSelectors(wop);
		}
	}

	/*- *********************************************************************************************** */

	public static void marshaller() throws FileNotFoundException, JAXBException {
		JAXBContext jaxbObj = JAXBContext.newInstance(WebObjectProperty.class);
		Marshaller marshaller = jaxbObj.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		WebObjectProperty wop = new WebObjectProperty("id", "login_button", true, false);
		marshaller.marshal(wop, new FileOutputStream("D:\\Soft\\workspace\\plugin\\other\\abc.xml"));
	}

	public static void unmarshaller() throws FileNotFoundException, JAXBException {
		JAXBContext jaxbObj = JAXBContext.newInstance(WebObjectProperty.class);
		Unmarshaller unmarshaller = jaxbObj.createUnmarshaller();
		WebObjectProperty wop = (WebObjectProperty) unmarshaller.unmarshal(new File("D:\\Soft\\workspace\\plugin\\other\\WebObjectSample.xml"));
		System.out.println(wop.getName());
		System.out.println(wop.getValue());
		System.out.println(wop.isUsed());
		System.out.println(wop.isRegex());
	}
	/*- Multiple Object ========================================================================== */

	public static void multipleObjectMarshaller() throws FileNotFoundException, JAXBException {
		JAXBContext jaxbObj = JAXBContext.newInstance(WebObject.class);
		Marshaller marshaller = jaxbObj.createMarshaller();

		WebObject webObject = new WebObject();
		webObject.getAllWebObjectProperty().add(new WebObjectProperty("id", "login_button", true, false));
		webObject.getAllWebObjectProperty().add(new WebObjectProperty("class", "login_class", true, false));
		webObject.getAllWebObjectProperty().add(new WebObjectProperty("xpath", "//*[@id='login_button']", true, false));

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(webObject, new FileOutputStream("D:\\Soft\\workspace\\plugin\\other\\abc.xml"));
	}

	public static void multipleObjectUnmarshaller() throws FileNotFoundException, JAXBException {
		JAXBContext jaxbObj = JAXBContext.newInstance(WebObject.class);
		Unmarshaller unmarshaller = jaxbObj.createUnmarshaller();
		WebObject webObject = (WebObject) unmarshaller.unmarshal(new File("D:\\Soft\\workspace\\plugin\\other\\abc.xml"));
		System.out.println(webObject.getAllWebObjectProperty());
	}

}
