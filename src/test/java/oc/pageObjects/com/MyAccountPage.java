package oc.pageObjects.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']") private WebElement msgheading; //my account page heading
	@FindBy(xpath="(//a[text()='Logout'])[2]") private WebElement lnklogout;
	
	public boolean isMyAccountPageExists()
	{
		try
		{
		return (msgheading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clicklogout()
	{
		lnklogout.click();
	}
}
