package com.cg.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelData {
	public static FileInputStream fin;
	public static FileOutputStream fout;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	public static int i = 0;

	// it reads the data from the excel sheet
	@DataProvider
	public Object[][] readData() throws IOException {
		int rows = ExcelUtils.getRowCount("login.xlsx", "Sheet1");
		int cols = ExcelUtils.getColumnCount("login.xlsx", "Sheet1", 0);
		System.out.println(rows + "----" + cols);
		Object[][] data = new Object[rows][cols - 1];
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j < cols; j++) {
				System.out.print(ExcelUtils.getCellData("login.xlsx", "Sheet1", i, j) + "   ");
				data[i - 1][j - 1] = ExcelUtils.getCellData("login.xlsx", "Sheet1", i, j);
			}
			System.out.println();
		}
		return data;
	}

	// it writes the login status into the excel sheet like pass or fail
	public static void writeData(String status) throws IOException {
		fin = new FileInputStream("login.xlsx");
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet("Sheet1");
		sheet.getRow(i).createCell(3).setCellValue(status);
		fout = new FileOutputStream("login.xlsx");
		workbook.write(fout);
		workbook.close();
	}

}
