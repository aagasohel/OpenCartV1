package oc.testcases.com;

import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import junit.framework.Assert;
import oc.pageObjects.com.HomePage;
import oc.pageObjects.com.RegisterAccountPage;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void Verify_AccountRegistration()
	{
		logger.info("******** Account Registration test starts *****");
		
		HomePage hp =new HomePage(driver);
		hp.clickOnMyAccount();
		logger.info("clicked on my account");
		hp.clickOnRegister();
		logger.info("clicked on Register");
		
		RegisterAccountPage rap =new RegisterAccountPage(driver);
		rap.enterFirstName(randomString());
		rap.enterLastName(randomString());
		rap.enterEmail(randomAlphaNumeric()+"@gmail.com");
		rap.enterTel(randomNumber());
		
		String pwd = randomAlphaNumeric();
		rap.enterPassword(pwd);
		rap.enterConfirmPassword(pwd);
		
		logger.info("entered all the deatails");
		rap.clickOnPrivacyPolicy();
		rap.clickOnContinue();
		logger.info("clicked on continue");
		
		String actualResult =rap.getConfirmationMessage();
		String expectedResult ="Your Account Has Been Created!";
		
		if(actualResult.equals(expectedResult))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
		
		logger.info("******** Account Registration test finished *****");
	}

	public void demo(){
		System.out.println("added demo method in remote repo");
	}
}
