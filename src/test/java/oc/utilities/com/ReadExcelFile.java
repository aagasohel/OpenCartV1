package oc.utilities.com;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public  FileInputStream fis;
	public  FileOutputStream fos;
	public  XSSFWorkbook workbook;
	public  XSSFSheet excelSheet;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	public  String filePath;
	
	public ReadExcelFile(String filePath)
	{
		this.filePath=filePath;
	}
	
	public  String getCellValue(String sheetName,int rowNo, int cellNo)
	{
		try {
		fis = new FileInputStream(filePath);
		workbook =new XSSFWorkbook(fis);
		excelSheet =workbook.getSheet(sheetName);
		cell =excelSheet.getRow(rowNo).getCell(cellNo);
		
		workbook.close();
		fis.close();
		return cell.getStringCellValue();
		}
		catch(Exception e) {
			return "";
		}
	}
	
	public  int getRowCount(String sheetName)
	{
		try {
		fis = new FileInputStream(filePath);
		//Create XSSFWorkbook class object for excel file manipulation
		workbook =new XSSFWorkbook(fis);
		excelSheet =workbook.getSheet(sheetName);
		
		//get total no. of rows
		int ttlRows =excelSheet.getLastRowNum();
		workbook.close();
		fis.close();
		return ttlRows;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public  int getColCount(String sheetName)
	{
		try {
			fis = new FileInputStream(filePath);
			//Create XSSFWorkbook class object for excel file manipulation
			workbook =new XSSFWorkbook(fis);
			excelSheet =workbook.getSheet(sheetName);
			//get total no. of columns
			int ttlCells =excelSheet.getRow(0).getLastCellNum();
			workbook.close();
			fis.close();
			return ttlCells;
			}
			catch(Exception e) {
				return 0;
			}
	}
}

