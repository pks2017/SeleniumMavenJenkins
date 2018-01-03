package com.demo.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.demo.project.ActionMethods;


public class LoginPage {

    private WebDriver driver;

    ActionMethods newAction;

    private final By userName = By.name("uid");

    private final By password = By.name("password");

    private final By titleText = By.xpath("/html/body/div[1]/h2"); //By.className("barone");

    private final By login = By.name("btnLogin");


    public LoginPage(WebDriver _driver){
        this.driver = _driver;               
    }


    //Set user name in textbox

    public void setUserName(String strUserName){
    	newAction = new ActionMethods(this.driver);
        //driver.findElement(user99GuruName).sendKeys(strUserName);
    	newAction.clearText(userName);
    	newAction.setText(userName, strUserName);    	
    }

    

    //Set password in password textbox

    public void setPassword(String strPassword){
    	newAction = new ActionMethods(this.driver);
         //driver.findElement(password99Guru).sendKeys(strPassword);
    	newAction.clearText(password);
    	newAction.setText(password, strPassword);    
    }

    

    //Click on login button

    public void clickLogin(){
    	newAction = new ActionMethods(this.driver);
           // driver.findElement(login).click();
    	newAction.ClickAction(login);
    }

    

    //Get the title of Login Page

    public String getLoginTitle(){
    	newAction = new ActionMethods(this.driver);
    	/// return driver.findElement(titleText).getText();
    	return newAction.webElement(titleText).getText(); 
    }

    // This POM method will be exposed in test case to login in the application
    public void loginToGuru99(String strUserName,String strPasword)
    {

        //Fill user name

        this.setUserName(strUserName);

        //Fill password

        this.setPassword(strPasword);

        //Click Login button

        this.clickLogin();        
       
    }

}

