package temp_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class MultiThread implements Runnable {
	String name;
	Thread t;

	MultiThread(String threadName) {
		name = threadName;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start();
	}

	public void run() {
		try {

			System.out.println("OpenBrowser : " + Thread.currentThread().getName());

			System.setProperty("webdriver.chrome.driver", "D:\\Soft\\automation\\drivers\\chrome\\2.45\\chromedriver.exe");

			WebDriver driver = new ChromeDriver();
			driver.get("https://www.amazon.com/");

			while (true) {

				System.out.println("Title : " + driver.getTitle());

				driver.findElement(By.xpath("//*[@class='nav-logo-link']")).click();

				Thread.sleep(2000);

			}

		} catch (InterruptedException e) {
			System.out.println(name + "Interrupted");
		}
		System.out.println(name + " exiting.");
	}
}

public class MyThread {
	public static void main(String args[]) {
		new MultiThread("One");
		new MultiThread("Two");
		new MultiThread("Three");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		System.out.println("Main thread exiting.");
	}
}
