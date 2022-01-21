package org.demo.Orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class Order_count {

	public static class GetTimeStamp {
		public static void Time() {

			Date date = new Date();

			long time = date.getTime();
//			System.out.println("Time in Milliseconds: " + time);

			Timestamp ts = new Timestamp(time);
			System.out.println("Current Time Stamp: " + ts);
		}
	}

	static WebDriver driver;

	@Test
	public static void Demo() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ankit\\Documents\\Selenium\\chromedriver_win32\\chromedriver.exe");
// -----------Headless  Browser--------------------
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		WebDriver driver = new ChromeDriver(options);
// -------------------------------------------------
		driver.manage().window().maximize();
		driver.get("https://app.powerbi.com");
		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(5000);
		driver.get(
				"https://app.powerbi.com/groups/0918d23a-5f9e-4cb7-bf4b-bf6fa5f19dcd/reports/c4280c32-c3ab-44af-abb9-ae0e64a33206/ReportSection");

		Thread.sleep(5000);
		String Desktop_Orders = driver
				.findElement(By.xpath("//div[@title='WEB Desktop']/following-sibling::visual-modern/div")).getText();
		String Msite_Orders = driver
				.findElement(By.xpath("//div[@title='WEB Mobile']/following-sibling::visual-modern/div")).getText();
		String iOS_Orders = driver.findElement(By.xpath("//div[@title='APP iOS']/following-sibling::visual-modern/div"))
				.getText();
		String Andriod_Orders = driver
				.findElement(By.xpath("//div[@title='APP Android']/following-sibling::visual-modern/div")).getText();
		System.out.println("Test");

//		int Desktop_count = Integer.parseInt(Desktop_Orders);
//		int Msite_count = Integer.parseInt(Msite_Orders);
//		int iOS_count = Integer.parseInt(iOS_Orders);
//		int Andriod_count = Integer.parseInt(Andriod_Orders);
//		int Total_orders = (Desktop_count + Msite_count + iOS_count + Andriod_count);

		System.out.println(
				("Total Orders for desktop:" + Desktop_Orders + '\n' + "Total Orders for Msite:" + Msite_Orders + '\n'
						+ "Total Orders for iOS:" + iOS_Orders + '\n' + "Total Orders for Andriod:" + Andriod_Orders));
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR);
		System.out.println(hour);
		if (hour > 8 && hour < 10) {
			System.out.println("9");// run script at 9
		} else if (hour > 10) {
			System.out.println("12");// run script at 12

		} else if (hour > 2 && hour < 4) {
			System.out.println("3");// run script at 3

		} else if (hour > 5 && hour < 7) {
			System.out.println("6");// run script at 6

		}

		String messageText = ("Total Orders for desktop:" + Desktop_Orders + '\n' + "Total Orders for Msite:"
				+ Msite_Orders + '\n' + "Total Orders for iOS:" + iOS_Orders + '\n' + "Total Orders for Andriod:"
				+ Andriod_Orders);

		GetTimeStamp.Time();
		NexmoClient client = new NexmoClient.Builder().apiKey("7d87a91d").apiSecret("D3eEtPLVKW5CWtfW").build();
		TextMessage message = new TextMessage("Vonage SMS API", "919599076899", messageText);
		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
		for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
			System.out.println(responseMessage);
		}

	}

}
