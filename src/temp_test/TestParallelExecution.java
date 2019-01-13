package temp_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import keywords.core.Driver;
import keywords.main.WebKW;

public class TestParallelExecution {

	public static void main(String[] args) throws InterruptedException {

		/*- while (true) {
		
			WebKW.openBrowser("chrome", "https://www.amazon.com/");
		
			System.out.println("Title : " + Driver.getDriver().getTitle());
		
			Driver.getDriver().findElement(By.xpath("//*[@class='nav-logo-link']")).click();
		
			Thread.sleep(2000);
		
		}*/

		Runnable task = () -> {

			System.out.println("OpenBrowser : " + Thread.currentThread().getName());

			System.setProperty("webdriver.chrome.driver", "D:\\Soft\\automation\\drivers\\chrome\\2.45\\chromedriver.exe");

			WebDriver driver = new ChromeDriver();
			driver.get("https://www.amazon.com/");

			while (true) {

				System.out.println("Title : " + driver.getTitle());

				driver.findElement(By.xpath("//*[@class='nav-logo-link']")).click();

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		};

		task.run();

		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();
		new Thread(task).start();

		System.out.println("Done!");

	}

}
