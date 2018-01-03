package com.demo.project;

import java.io.File;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionMethods {
	
	private WebElement element = null;
	private WebDriver driver = null;
	
	public ActionMethods(WebDriver _driver)
	{
		driver = _driver;
	}
	public WebElement webElement (By by)
	{
		try 
		{
			element = driver.findElement(by);
			if (element.isEnabled()||(element.isDisplayed()))
			{
				return element;
			}
			else 
			{
				throw new NoSuchElementException(" No such element is found");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e.getMessage();
		}
		return element;
	}

	//Method to Click Element i.e. Hyperlink, Button, Radio button, etc.
	
	public void ClickAction (By by)
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element= (wait.until(ExpectedConditions.elementToBeClickable(webElement(by))));
			element.click();	
		}
		catch (StaleElementReferenceException e) 
		{
			System.out.println("Element is not clickable.");
			e.getStackTrace();
		} 
		catch (ElementClickInterceptedException e)
		{
			e.getStackTrace();
		}
		catch(ElementNotVisibleException e)
		{
			e.getStackTrace();
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getStackTrace();
			e.getMessage();
		}
	}		
	
	public void setText (By by, String text)
	{
		try 
		{
			webElement(by).sendKeys(text);	
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementNotVisibleException e)
		{
			e.getStackTrace();			
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void clearText (By by)
	{
		try 
		{
			webElement(by).clear();	
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementNotVisibleException e)
		{
			e.getStackTrace();			
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void comboBoxSelectByIndex(By by, int index)
	{
		try 
		{
			Select dropdown = new Select(webElement(by));
			dropdown.selectByIndex(index);
		}
		catch (ElementNotSelectableException e)
		{
			e.getStackTrace();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementNotVisibleException e)
		{
			e.getStackTrace();			
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void comboBoxSelectByValue(By by, String value)
	{
		try 
		{
			Select dropdown = new Select(webElement(by));
			dropdown.selectByValue(value);
		}
		catch (ElementNotSelectableException e)
		{
			e.getStackTrace();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementNotVisibleException e)
		{
			e.getStackTrace();			
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void customDropdownSelectByIndex(By byComboBoxLocator, By optionListLocator, int index)
	{	
		try 
		{
			Actions action = new Actions(driver);
			WebElement optionsList = webElement(byComboBoxLocator);
			action.moveToElement(optionsList);
			
			List<WebElement> options = driver.findElements(optionListLocator);
			//System.out.println("Total Regions available to be selected: "+options.size());
			
			if (index >= 0 && index < options.size())
			{
				for (WebElement option : options)
				{
					int dropdownIndex = Integer.parseInt(option.getAttribute("data-offset-index").toString());
					if (dropdownIndex == index)
					{
						option.click();
					}
				}
			}
			else 
			{
				Assert.fail("Given index: "+ index +" is out of range (0 <= index < "+ options.size()+")");
			}
		}
		catch (ElementNotSelectableException e)
		{
			e.getStackTrace();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementNotVisibleException e)
		{
			e.getStackTrace();			
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void customDropdownSelectByValue(By byComboBoxLocator, By optionListLocator, String value)
	{
		try 
		{
			Actions action = new Actions(driver);
			WebElement optionsList = webElement(byComboBoxLocator);
			action.moveToElement(optionsList);
			int j =0;
			List<WebElement> options = driver.findElements(optionListLocator);
			
			for (WebElement option : options)
			{
				if (option.getText().toLowerCase().equals(value.toLowerCase()))
				{
					option.click();
				}
				else 
				{
					j++;
					if (j== options.size())
					{
						Assert.fail("Given value: '"+ value +"' is not available in dropdown list.");					
					}
				}
			}
		}
		catch (ElementNotSelectableException e)
		{
			e.getStackTrace();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementNotVisibleException e)
		{
			e.getStackTrace();			
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void selectRadioOrCheckBoxByValue (By by, String userInputValue)
	{
		try 
		{
			List<WebElement> radioButtons = driver.findElements(by);
			if (radioButtons.size() > 0)
			{
			    for(WebElement radioButton: radioButtons) 
			    { 
				    if(radioButton.getAttribute("value").equals(userInputValue))
				    {
				        radioButton.click();
				    }
				    else
				    {
				    	Assert.fail("Given value to select Radio button is not found.");
				    }
			    }
			}
			else if (radioButtons.isEmpty() == true)
			{
				Assert.fail("No Radio buttons found to select.");
			}
		}
		catch (ElementClickInterceptedException e)
		{
			e.getMessage();
		}
		catch(ElementNotVisibleException e)
		{
			e.getMessage();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void mouseHoverAndClick (By MainMenu, By SubMenu)
	{
		try
		{
			Actions action = new Actions(driver);
			action.moveToElement(webElement(MainMenu)).moveToElement(webElement(SubMenu)).click().build().perform();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementClickInterceptedException e)
		{
			e.getStackTrace();
		}
		catch(ElementNotVisibleException e)
		{
			e.getStackTrace();
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void doubleclick(By by)
	{
		try
		{
			Actions action = new Actions(driver);
			action.doubleClick(webElement(by)).perform();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementClickInterceptedException e)
		{
			e.getStackTrace();
		}
		catch(ElementNotVisibleException e)
		{
			e.getStackTrace();
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void rightclick(By by)
	{
		try
		{
			Actions action = new Actions(driver);
			action.contextClick(webElement(by)).perform();
		}
		catch (StaleElementReferenceException e) 
		{
			e.getStackTrace();
		} 
		catch (ElementClickInterceptedException e)
		{
			e.getStackTrace();
		}
		catch(ElementNotVisibleException e)
		{
			e.getStackTrace();
		}
		catch (TimeoutException e)
		{
			e.getStackTrace();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

	public void captureScreenShot (String fileNameAndTypeWithPath)
	{
		try
		{
			// 1). Typecast Driver to TakeScreenshot
			TakesScreenshot captureScr =((TakesScreenshot)driver);
			
			// 2). Call GetScreenShot to create image file.
			File screenShotFile = captureScr.getScreenshotAs(OutputType.FILE);
			
			// 3). Save image file in desired location and format
			FileUtils.copyFile(screenShotFile, new File(fileNameAndTypeWithPath));
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}

}
