package oc.pageObjects.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-email") private WebElement txtemail;
	@FindBy(id="input-password") private WebElement txtpassword;
	@FindBy(xpath="//input[@type='submit']") private WebElement btnlogin;
	
	public void enterEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void enterPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnlogin.click();
	}
	
}
