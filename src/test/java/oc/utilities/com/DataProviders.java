package oc.utilities.com;

import org.testng.annotations.DataProvider;

public class DataProviders  {

	@DataProvider(name ="loginData")
	public String[][] getData()
	{
		String filePath =".//testData//Opencart_LoginData.xlsx"; //taking xl file from testData
		ReadExcelFile xlutil =new ReadExcelFile(filePath); // creating an object for ReadExcelFile
		
		int ttlrows =xlutil.getRowCount("loginData");
		int ttlcol =xlutil.getColCount("loginData");
		
		String loginData[][]= new String[ttlrows][ttlcol]; //created for 2D array which can store 
		
		for(int i=1;i<=ttlrows;i++)  //1 // read the data from xl storing in 2D array
		{
			for(int j=0;j<ttlcol;j++)  //0 //i is rows j is col
			{
				loginData[i-1][j] =xlutil.getCellValue("loginData", i, j); //1,0
			}
		}
		return loginData; //returning 2D array
	}
	
	//DataProvider2
	
	//DataProvider3
	
	//DataProvider4
}
