package com.demo.project.regression;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demo.project.DriverSetup;
import com.demo.project.pages.HomePage;
import com.demo.project.pages.LoginPage;

public class HomePageTest {

	private WebDriver driver;

	LoginPage objLogin;

    HomePage objHomePage;

  

    @BeforeTest
    public void setup()
    {
    	DriverSetup _driver = new DriverSetup();
        driver = _driver.getDriver("firefox");                
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/V4/");        
    }

    /**
     * This test case will login in http://demo.guru99.com/V4/
     * Verify login page title as guru99 bank
     * Login to application
     * Verify the home page using Dashboard message
     */

    @Test(priority=0)

    public void test_Home_Page_Appear_Correct()
    {
	    //Create Login Page object
		if (!(driver==null))
		{
		    objLogin = new LoginPage(this.driver);
		    //Verify login page title
		    String loginPageTitle = objLogin.getLoginTitle();
		    System.out.println("Page Title: " + loginPageTitle);
		    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
		
		    //login to application
		
		    objLogin.loginToGuru99("mgr123", "mgr!23");
		
		    // go the next page
		
		    objHomePage = new HomePage(this.driver);
		
		    //Verify home page
		    System.out.println("UserName is:- " + objHomePage.getHomePageDashboardUserName());
		    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
		    //objHomePage.countOfHtmlLinks("New Customer");
		}
		else
		{
			System.out.println("No driver found");
		}
    }
    
    @AfterTest
    public void tearDown()
    {
  	   driver.quit();
    }
}
