package oc.testcases.com;

import org.testng.annotations.Test;

import junit.framework.Assert;
import oc.pageObjects.com.HomePage;
import oc.pageObjects.com.LoginPage;
import oc.pageObjects.com.MyAccountPage;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("***** starting TC002_LoginTest ******");
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(prop.getProperty("email"));
		lp.enterPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage maccp = new MyAccountPage(driver);
		boolean targetpage =maccp.isMyAccountPageExists();
		Assert.assertTrue(targetpage); //Assert.assertEquals(targetpage,true,"Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC002_LoginTest ******");
	}
}
