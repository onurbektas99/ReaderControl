package latestReader;
/*
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


// küçük not class isimleri büyük harfle başlar
public class ReaderClass {

	public static void main(String[] args) throws Exception {

	File file = new File("kitap1.xlsx");
	FileInputStream fis = new FileInputStream(file);
	
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	
	XSSFSheet sheet = workbook.getSheetAt(0);
	
	String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
	
	System.out.println(cellValue);
	
	workbook.close();
	fis.close();
	

}
}*/
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReaderClass {

	public static void main(String[] args) throws IOException {

	FileInputStream fis = new FileInputStream(new File("/Users/onurbektas/Library/Containers/com.microsoft.Excel/Data/Downloads/Ekinoks_Test_Excel.xlsx"));
	
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	
	XSSFSheet sheet = workbook.getSheetAt(1);
	
		
	FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
	
	for(Row row : sheet) {
		
		for(Cell cell : row) {
			switch(formulaEvaluator.evaluateInCell(cell).getCellType()) {
			
			case Cell.CELL_TYPE_NUMERIC:
				System.out.print("|||" + cell.getNumericCellValue()+"|||" + "\t\t");
				break;
			case Cell.CELL_TYPE_STRING:
				System.out.print("|||" + cell.getStringCellValue()+"|||" + "\t\t");
				break;
			}
			
		}
		System.out.println();
	}
	}

}