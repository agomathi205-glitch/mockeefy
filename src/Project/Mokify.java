package Project;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mokify {
	WebDriver dr;
	WebDriverWait wait;
	WebElement login,getStarted,email,password,signInBtn,loginBtn,mentor,mySess;
	boolean findMentors,mySessions;
	
	@BeforeTest
	public void setUp() {
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		wait = new WebDriverWait(dr, Duration.ofSeconds(10));
		dr.get("https://www.mockeefy.com/");
	}
	
	// HOMEPAGE MODULE
	@Test (priority=1)
	public void testHomePage() {
		
		Assert.assertEquals(dr.getCurrentUrl().contains("mockeefy"), true);
	}
	
	@Test(priority=2)
	public void testHomePageElements() {
		
		 findMentors = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Find Mentors')]"))).isDisplayed();
		 mySessions = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'My Sessions')]"))).isDisplayed();
		 login = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Log in')]"))); 
		 
	     Assert.assertEquals(login.isDisplayed(),true);

	     System.out.println("Home page elements verified");
	
	}
	
	//REGISTRATION MODULE
	@Test(priority=3)
	public void testRegistrationPage() {
		dr.get("https://www.mockeefy.com/");
		getStarted = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Get started')]")));
		getStarted.click();
		Assert.assertEquals(dr.getCurrentUrl ().contains("signup"), true,"Registration page did not open");
		System.out.println("Registration page opened");
		email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		email.sendKeys("testuser@example.com");
               
	}
	
	//LOGIN MODULE

    @Test(priority = 4)
    public void testLoginPage() {

        dr.get("https://www.mockeefy.com/");
        login = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
        login.click();
        Assert.assertEquals(dr.getCurrentUrl().contains("signin"), true,"Login page did not open");
        System.out.println("Login page opened");
    }  
    
    @Test(priority=5)
    public void testLoginFields() {
         
    	dr.get("https://www.mockeefy.com/");
    	login = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
    	login.click();
        email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        
        password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        
        email.sendKeys("testuser@example.com");
        password.sendKeys("Test@1234");
        signInBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

        signInBtn.click();
        Assert.assertEquals(dr.getCurrentUrl().contains("signin"), true, "Sign-in URL validation failed");
        System.out.println("SignIn form submitted");
        
    }
    
    @Test(priority=6) 
    public void invalidLoginTest() throws InterruptedException {
    	
    	dr.get("https://www.mockeefy.com/");
    	login = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
    	login.click();
    	
    	email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        
        password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password")));

    	
    	email.sendKeys("wrong@gmail.com");
    	password.sendKeys("wrong123");
    	
    	loginBtn = wait.until(
    			ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    	
    	loginBtn.click();
    	Thread.sleep(2000);
    
    	
    	System.out.println("Current Url: "+dr.getCurrentUrl());
    	Assert.assertEquals(dr.getCurrentUrl().contains("signin"), true,  "Invalid login validation failed");
    	System.out.println("Invalid login test completed");
    	
   
    }
    
    //FIND MENTORS MODULE
    @Test(priority=7)
    public void testfindMentors() {
          
    	dr.get("https://www.mockeefy.com/");
    	mentor = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Find Mentors')]")));
    	
	    mentor.click();
	    Assert.assertEquals(dr.getCurrentUrl().contains("mockeefy"), true, "Mock Interview navigation failed");
	    System.out.println("Find Mentors module opened");
    	
    }
    
    //MY SESSIONS MODULE
    @Test(priority=8)
    public void testMySessionsWithoutLogin() {
        
  	    dr.get("https://www.mockeefy.com/");
  	    mySess = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'My Sessions')]")));
	    mySess.click();

	    System.out.println("My Sessions Clicked");
	    System.out.println("Login page displayed for My Sessions");
	    Assert.assertEquals(dr.getCurrentUrl().contains("signin"), true, "My Sessions did not redirect to login");

    }
    
    @Test(priority=9)
    public void testMySessionsRequireLogin() {
    	
    	dr.get("https://www.mockeefy.com/");

	       mySess = wait.until(
	               ExpectedConditions.elementToBeClickable(
	                       By.xpath("//a[contains(text(), 'My Sessions')]")));

	       mySess.click();
	       wait.until(ExpectedConditions.urlContains("signin"));

	       email = wait.until(
	               ExpectedConditions.visibilityOfElementLocated(
	                       By.xpath("//input[@type='email']")));

	       password = wait.until(
	               ExpectedConditions.visibilityOfElementLocated(
	                       By.xpath("//input[@type='password']")));
	       Assert.assertEquals(dr.getCurrentUrl().contains("signin"), true, "Sign-in page was not displayed");
	       System.out.println("My Sessions requires login");

    }
    
    @AfterTest
    public void tearDown() {
    	dr.quit();
    	
    }


}