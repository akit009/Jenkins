package New.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

/**
 * Unit test for simple App.
 */
public class AppTest {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ankit\\Documents\\Selenium\\chromedriver_win32\\chromedriver.exe");
//		driver = new ChromeDriver();

//---------------------------Headless Browser----------------------------------------------
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		WebDriver driver = new ChromeDriver(options);
//-----------------------------------------------------------------------------------------	   
		driver.manage().window().maximize();
		driver.get("https://www.covid19india.org/");
		System.out.println("Test");
		Thread.sleep(5000);

		String Last_Updated = driver.findElement(By.xpath("//div[@class='actions']/h5")).getText();
		System.out.println("Last Updated On " + Last_Updated);

		String Count_today = driver.findElement(By.xpath("//div[@class=\"level-item is-cherry fadeInUp\"]/h4"))
				.getText();
		System.out.println("Today's Count " + Count_today);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		String Total_Count = driver.findElement(By.xpath(
				"//tr[@class=\"state is-total\"]//td//span[@style='color: rgb(255, 7, 58);']/following-sibling::span"))
				.getText();
		System.out.println("Total Count " + Total_Count);
		NexmoClient client = new NexmoClient.Builder().apiKey("7d87a91d").apiSecret("D3eEtPLVKW5CWtfW").build();

		String messageText = "Corona Counts Updates  " + "\n  " + " Today's Count:" + Count_today + " \n "
				+ " Total Count: " + Total_Count + " \n  " + " Last Updated On:" + Last_Updated + " \n  ";
		
		

		TextMessage message = new TextMessage("Vonage SMS API", "919599076899", messageText);
//		TextMessage message1 = new TextMessage("Vonage SMS API", "919650483501", messageText);
//		TextMessage message2 = new TextMessage("Vonage SMS API", "919650407666", messageText);
//		TextMessage message3 = new TextMessage("Vonage SMS API", "919837971813", messageText);

		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
//		SmsSubmissionResponse response1 = client.getSmsClient().submitMessage(message1);
//		SmsSubmissionResponse response2 = client.getSmsClient().submitMessage(message2);
//		SmsSubmissionResponse response3 = client.getSmsClient().submitMessage(message3);

		for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
			System.out.println(responseMessage);

		}

//		for (SmsSubmissionResponseMessage responseMessage : response1.getMessages()) {
//			System.out.println(responseMessage);
//
//		}
//
//		for (SmsSubmissionResponseMessage responseMessage : response2.getMessages()) {
//			System.out.println(responseMessage);
//
//		}

//		for (SmsSubmissionResponseMessage responseMessage : response3.getMessages()) {
//			System.out.println(responseMessage);
//
//		}

	}
}
