package com.demo.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



public class DriverSetup {

	private WebDriver driver = null;
	    
	public WebDriver getDriver (String DriverName){
		switch (DriverName.toUpperCase())
		{
			case "FIREFOX":
			case "FIREFOXDRIVER":
				System.setProperty("webdriver.gecko.driver", "D:\\drivers_selenium\\geckodriver.exe");
				this.driver = new FirefoxDriver();
				break;
			case "CHROME":
			case "GOOGLE CHROME":
			case "CHROMEDRIVER":
				System.setProperty("webdriver.chrome.driver", "D:\\drivers_selenium\\chromedriver.exe");
				this.driver = new ChromeDriver();
				break;
			case "IE":
			case "INTERNET EXPLORER":
			case "INTERNETEXPLORER":
				System.setProperty("webdriver.ie.driver", "D:\\drivers_selenium\\IEDriverServer.exe");
				this.driver = new InternetExplorerDriver();
				break;
			default:
				System.setProperty("webdriver.gecko.driver", "D:\\drivers_selenium\\geckodriver.exe");
				this.driver = new FirefoxDriver();
				break;			
		}
		return driver;	
	}			
}
