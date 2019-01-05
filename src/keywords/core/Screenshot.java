package keywords.core;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot
		File scrShotFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);

		// Copy file at destination
		FileUtils.copyFile(scrShotFile, new File(fileWithPath));
	}

}
