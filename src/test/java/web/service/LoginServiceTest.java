package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginServiceTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private void sleep(long sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
		System.out.println("Driver info: " + driver);
		
		// Full path where login.html is located.
		// You can click on html file and copy the path shown in your browser.
        driver.navigate().to("file:///C:/Users/User/Documents/SIT 707 - Software Quality And Testing/Java Projects/pages/login.html");
        //sleep(5);
    }
	
	@After
    public void tearDown() {
		 sleep(5);
            driver.quit();
        
    }
	
   // Functional Test Cases  
	
	private void performLogin(String username, String password, String dob) {
        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys(username);

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys(password);

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dob);

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(5);
    }
	
	@Test
    public void testCorrectUsernameCorrectPasswdCorrectDobLoginSuccess() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("success", loginStatus);
    }

	@Test
    public void testCorrectUsernameWrongPasswdCorrectDobLoginFail() {
        performLogin("ahsan", "wrong_pass", "07/27/1996");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }

    @Test
    public void testWrongUsernameCorrectPasswdCorrectDobLoginFail() {
        performLogin("samadhi", "ahsan_pass", "07/27/1996");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }

    @Test
    public void testCorrectUsernameCorrectPasswdWrongDobLoginFail() {
        performLogin("ahsan", "ahsan_pass", "02/20/1990");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testWrongUsernameWrongPasswdCorrectDobLoginFail() {
        performLogin("samadhi", "wrong_pass", "07/27/1996");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testWrongUsernameCorrectPasswdWrongDobLoginFail() {
        performLogin("samadhi", "ahsan_pass", "02/20/1990");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testCorrectUsernameWrongPasswdWrongDobLoginFail() {
        performLogin("ahsan", "wrong_pass", "02/20/1990");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testWrongUsernameWrongPasswdWrongDobLoginFail() {
        performLogin("ahsan", "ahsan_pass", "02/20/1990");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testNullUsernameCorrectPasswdCorrectDobLoginFail() {
    	thrown.expect(IllegalArgumentException.class);
        performLogin(null, "ahsan_pass", "02/20/1990");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testCorrectUsernameNullPasswdCorrectDobLoginFail() {
    	thrown.expect(IllegalArgumentException.class);
        performLogin("ahsan", null, "02/20/1990");
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
    
    @Test
    public void testCorrectUsernameCorrectPasswdNullDobLoginFail() {
    	thrown.expect(IllegalArgumentException.class);
        performLogin("ahsan", "ahsan_pass", null);
        WebElement loginStatusElement = driver.findElement(By.tagName("p"));
        String loginStatus = loginStatusElement.getText();
        Assert.assertEquals("fail", loginStatus);
    }
   
}
