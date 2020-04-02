package com.virtusa.isq.vtaf.utils.files;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.File;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader {
	int count;
	Workbook workbook;

	public ExcelReader() {
		this.count = 0;
	}

	public int getRowCount(final String fileName) {
		final String excelFilePath = System.getProperty("user.home")+"\\Downloads\\" + fileName;
		try {
			final FileInputStream inputStream = new FileInputStream(new File(
					excelFilePath));
			if (fileName.split("\\.")[1].equals("xlsx")) {
				this.workbook = (Workbook) new XSSFWorkbook(
						(InputStream) inputStream);
			} else if (fileName.split("\\.")[1].equals("xls")) {
				this.workbook = (Workbook) new HSSFWorkbook(
						(InputStream) inputStream);
			}else if (fileName.split("\\.")[1].equals("csv")) {
				this.workbook = (Workbook) new HSSFWorkbook(
						(InputStream) inputStream);
			}
			final Sheet firstSheet = this.workbook.getSheetAt(0);
			for (final Row nextRow : firstSheet) {
				final Iterator<Cell> cellIterator = (Iterator<Cell>) nextRow
						.cellIterator();
				int flag = 0;
				while (cellIterator.hasNext()) {
					final Cell cell = cellIterator.next();
					if ((cell.getCellType() == 1 || cell.getCellType() == 4 || cell
							.getCellType() == 0) && flag == 0) {
						++this.count;
						flag = 1;
					}
				}
				flag = 0;
			}
			new File(excelFilePath).delete();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.count - 1;
	}
}
