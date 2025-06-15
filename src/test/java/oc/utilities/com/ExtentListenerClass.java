package oc.utilities.com;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import oc.testcases.com.BaseClass;
//com.mystore.Utilities.ExtentListenerClass
public class ExtentListenerClass implements ITestListener {
	
	ExtentSparkReporter sparkReporter;
	ExtentReports reports;
	ExtentTest test;
	
	String repname;
	
	 //Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
	  // tag and calling all their Configuration methods.
	public void onStart(ITestContext context) {
		
		 System.out.println("On start method invoked");
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
		Date dt = new Date();
		String timestamp =sdf.format(dt); //time stamp
//		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
//		String reportName = "MyStoreTestReport-" + timestamp + ".html";
		repname ="Test-Report-"+ timestamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + repname);//specify location of the report
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		
		//configuration to change look and feel of report
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		//add system info/ environment info to reports
		reports.setSystemInfo("Application","Opencart");
		reports.setSystemInfo("Module","Admin");
		reports.setSystemInfo("Sub Module","Customers");
		reports.setSystemInfo("Environment","QA");
		reports.setSystemInfo("user name",System.getProperty("user.name"));
		
		String os= context.getCurrentXmlTest().getParameter("OS");
		reports.setSystemInfo("OS", os);
		
		String br =context.getCurrentXmlTest().getParameter("Browser");
		reports.setSystemInfo("Browser", br);
		
		List<String> grps = context.getCurrentXmlTest().getIncludedGroups();
		if(!grps.isEmpty())
		{
			reports.setSystemInfo("Groups", grps.toString());
		}
		
	  }

	    //Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
	    //run and all their Configuration methods have been called.
	   
	 public void onFinish(ITestContext context) {
		 System.out.println("On finish method invoked");
		 reports.flush();// it is mandatory to call flush method to ensure information is written to the started reporter
		 
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repname;
		File extentReport=new File(pathofExtentReport);
		try {
		Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	  }
	 
	//Invoked each time a test fails.
	   
	  public void onTestFailure(ITestResult result) {
	    System.out.println("Name of test method failed: "+ result.getName());
	    test =reports.createTest(result.getTestClass().getName()); // create entry in html report
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+result.getName(), ExtentColor.RED));
	    
	    String errorMessage;
	    if (result.getThrowable() != null) {
	         errorMessage = result.getThrowable().getMessage();    
	    } else {
	        errorMessage = "No exception was thrown.";
	    }
	 
	    test.log(Status.INFO, errorMessage);
	    
	    BaseClass bc =new BaseClass();
	  
	   try {
	   String imgpath = bc.captureScreen(result.getName());
	    test.addScreenCaptureFromPath(imgpath);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	    
	  }
	 
	  //Invoked each time before a test will be invoked. 
	  public void onTestStart(ITestResult result) {
	    System.out.println("Name of the test case started: "+result.getName());
	  }
 
	   // Invoked each time a test succeeds.
	   
	  public void onTestSuccess(ITestResult result) {
	    System.out.println("Name of test method successfully executed: "+result.getName());
	    test =reports.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups()); // to display groups in report
	    test.log(Status.PASS, MarkupHelper.createLabel("Name of passed test case is: "+result.getName(), ExtentColor.GREEN));
	    String errorMessage;
	    if (result.getThrowable() != null) {
	         errorMessage = result.getThrowable().getMessage();    
	    } else {
	        errorMessage = "No exception was thrown.";
	    }
	 
	    test.log(Status.INFO, errorMessage);
	  }

	   //Invoked each time a test is skipped.
	   
	  public void onTestSkipped(ITestResult result) {
		  System.out.println("Name of test method skipped: "+result.getName());
		  test =reports.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.SKIP, MarkupHelper.createLabel("Name of skipped test case is: "+result.getName(), ExtentColor.YELLOW));
		  String errorMessage;
		    if (result.getThrowable() != null) {
		         errorMessage = result.getThrowable().getMessage();    
		    } else {
		        errorMessage = "No exception was thrown.";
		    }
		 
		    test.log(Status.INFO, errorMessage);
	  }

	  
	   // Invoked each time a method fails but has been annotated with successPercentage and this failure
	   //still keeps it within the success percentage requested.
	  
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    
	  }

	  
	   //Invoked each time a test fails due to a timeout.
	   
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	 
	  
	

}
