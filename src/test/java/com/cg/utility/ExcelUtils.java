package com.cg.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static FileInputStream fin;
	static FileOutputStream fout;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public static int getRowCount(String xlfilepath, String sheetName) throws IOException {
		fin = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	public static int getColumnCount(String xlfilepath, String sheetName, int rowNum) throws IOException {
		fin = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		int columnCount = sheet.getRow(rowNum).getLastCellNum();
		return columnCount;
	}

	public static String getCellData(String xlfilepath, String sheetName, int rowNum, int colNum) throws IOException {
		fin = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		CellType celltype = sheet.getRow(rowNum).getCell(colNum).getCellType();
		String cellvalue = "";
		if (celltype.toString() == "STRING") {
			cellvalue = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		} else if (celltype.toString() == "NUMERIC") {
			int value = (int) sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			cellvalue = String.valueOf(value);
		}
		return cellvalue;
	}

	public static void setCellData(String xlfilepath, String sheetName, int rowNum, int colNum, String value)
			throws IOException {
		fin = new FileInputStream(xlfilepath);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		sheet.getRow(rowNum).createCell(colNum).setCellValue(value);
		fout = new FileOutputStream(xlfilepath);
		workbook.write(fout);
	}
}
