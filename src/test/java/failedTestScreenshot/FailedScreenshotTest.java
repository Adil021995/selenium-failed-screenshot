package failedTestScreenshot;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class FailedScreenshotTest 
{
	public WebDriver driver;
	public String baseUrl = "https://www.facebook.com/";
	
    @BeforeClass
    public void openBrowser()
    {
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	driver.get(baseUrl);
    }
    
    @Test
    public void testMeth1()
    {
    	String exp = "Faceboo";
    	String act = driver.getTitle();
    	
    	Assert.assertEquals(act, exp);
    	System.out.println("Browser Verified Successfully");
    }
    
    @AfterMethod()
    public void screenShot(ITestResult result) throws Exception
    {
    	if(ITestResult.FAILURE == result.getStatus())
    	{
    		
    	    WebElement ele = driver.findElement(By.xpath("//form[@id = 'login_form']"));
    		
    		File src = ele.getScreenshotAs(OutputType.FILE);
    		
    		Files.copy(src, new File("I:\\ScreenshotPractice\\loginform1.jpeg"));
    		
    		System.out.println("Browser not verified");
    		System.out.println("Screenshot has been taken");
    	}
    	Thread.sleep(3000);
		driver.quit();
    }
}
