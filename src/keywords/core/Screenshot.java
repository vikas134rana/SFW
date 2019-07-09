package keywords.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {

	public static void takeScreenshot(String fileWithPath) throws IOException {

		takeScreenshot(new File(fileWithPath));
	}

	public static void takeScreenshot(File file) throws IOException {

		System.out.println("============== Taking Screenshot ==================");

		// Convert web driver object to TakeScreenshot
		File scrShotFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);

		System.out.println(file.getAbsolutePath());

		// Copy file at destination
		FileUtils.copyFile(scrShotFile, file);
	}

	public static void takeScreenshotInsideTemp() {

		String tmpDir = System.getProperty("java.io.tmpdir");
		String fileName = tmpDir + System.currentTimeMillis() + ".png";

		try {
			Screenshot.takeScreenshot(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
