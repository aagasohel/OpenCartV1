package oc.testcases.com;

import org.testng.annotations.Test;

import junit.framework.Assert;
import oc.pageObjects.com.HomePage;
import oc.pageObjects.com.LoginPage;
import oc.pageObjects.com.MyAccountPage;
import oc.utilities.com.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="loginData",dataProviderClass=DataProviders.class,groups="datadriven") //getting data provider from different package
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("***** starting TC002_LoginTest ******");
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage maccp = new MyAccountPage(driver);
		boolean targetpage =maccp.isMyAccountPageExists();
		
		/* Data is valid  - login success - test pass - logout
		 * 					login failed - test fail
		 * Data is invalid - login success -test fail - logout
		 * 				   - login fail -test pass
		 */
		//Assert.assertTrue(targetpage); //Assert.assertEquals(targetpage,true,"Login failed");
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				Assert.assertTrue(true);
				maccp.clicklogout();
			}
			else
			{
				Assert.assertTrue(false);;
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
				maccp.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC002_LoginTest ******");
	}
}
