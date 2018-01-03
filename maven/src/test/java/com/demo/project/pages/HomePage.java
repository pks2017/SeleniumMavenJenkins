package com.demo.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.demo.project.ActionMethods;

public class HomePage {

	    private WebDriver driver;

	    private final By homePageUserName = By.xpath("//table//tr[@class='heading3']");
	    private final By htmlLinks = By.tagName("a");



	    public HomePage(WebDriver _driver){

	        this.driver = _driver;

	    }
	   
	    //Get the User name from Home Page

	    public String getHomePageDashboardUserName()
	    {
	    	ActionMethods newAction = new ActionMethods(driver);
	        return newAction.webElement(homePageUserName).getText();

	    }
	        
	    public void countOfHtmlLinks(String weblinkText)
	    {
        	List <WebElement> weblinks= driver.findElements(htmlLinks);
        	System.out.println(weblinks.size());
        	for (WebElement e: weblinks)
        	{
        		if (e.getText().equals(weblinkText)&&(e.isDisplayed()||e.isEnabled()))
        		{
	        		JavascriptExecutor js = (JavascriptExecutor) driver;
	        		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",e);
	        		e.click();
	        		driver.navigate().back();
        		}
        	}	        	
        }
}
