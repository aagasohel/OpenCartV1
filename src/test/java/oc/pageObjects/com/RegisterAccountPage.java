package oc.pageObjects.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {

	public RegisterAccountPage(WebDriver driver)
	{
		super(driver);// we are providing child class constructor driver to parent class constructor
	}
	
	@FindBy(id="input-firstname") private WebElement txtFirstName;
	@FindBy(id="input-lastname") private WebElement txtLastName;
	@FindBy(id="input-email") private WebElement txtEmail;
	@FindBy(id="input-telephone") private WebElement txtTel;
	@FindBy(id="input-password") private WebElement txtPwd;
	@FindBy(id="input-confirm") private WebElement txtPwdCfrm;
	@FindBy(xpath="//input[@type='checkbox']") private WebElement chkPrivacyPolicy;
	@FindBy(xpath="//input[@type='submit']") private WebElement btnContinue;
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']") private WebElement msgcfrm;
	
	public void enterFirstName(String fn)
	{
		txtFirstName.sendKeys(fn);
	}
	
	public void enterLastName(String ln)
	{
		txtLastName.sendKeys(ln);
	}
	
	public void enterEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void enterTel(String tel)
	{
		txtTel.sendKeys(tel);
	}
	
	public void enterPassword(String pwd)
	{
		txtPwd.sendKeys(pwd);
	}
	
	public void enterConfirmPassword(String confrmpwd)
	{
		txtPwdCfrm.sendKeys(confrmpwd);
	}
	
	public void clickOnPrivacyPolicy()
	{
		chkPrivacyPolicy.click();
	}
	
	public void clickOnContinue()
	{
		//sol1
		btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		
		//sol3
//		Actions act = new Actions(driver);
//		act.moveToElement(btnContinue).click().perform();
		
		//sol4
//		JavascriptExecutor js =(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", btnContinue);
		
		//sol5
//		btnContinue.sendKeys(Keys.RETURN);
		
		//sol6
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMessage()
	{
		try{
		return(msgcfrm.getText());
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
}
