package oc.pageObjects.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver)
	{
		super(driver);// we are providing child class constructor driver to parent class constructor
	}
	
	@FindBy(xpath="//span[text()='My Account']") private WebElement lnkmyAccount;
	@FindBy(xpath="//a[text()='Register']") private WebElement lnkRegister;
	@FindBy(xpath="//a[text()='Login']") private WebElement lnkLogin;
	
	public void clickOnMyAccount()
	{
		lnkmyAccount.click();
	}
	
	public void clickOnRegister()
	{
		lnkRegister.click();
	}
	
	public void clickOnLogin()
	{
		lnkLogin.click();
	}
}
