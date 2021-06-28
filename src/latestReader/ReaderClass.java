package latestReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Scanner;
import java.io.FileWriter;

public class ReaderClass {

    public static void main(String[] args) throws IOException {
    	Scanner in = new Scanner(System.in);
    	int num = in.nextInt();
    	while(num != -1) {
        FileInputStream fis = new FileInputStream(new File("./src/latestReader/Ekinoks_Test_Excel.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheetAt(num);

        
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        FileWriter myWriter = new FileWriter("filename.txt");
        
        for (Row row : sheet) {

            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print("|||" + cell.getNumericCellValue() + "|||" + "\t\t");
                        myWriter.write("|||" + cell.getNumericCellValue() + "|||" + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print("|||" + cell.getStringCellValue() + "|||" + "\t\t");
                        myWriter.write("|||" + cell.getStringCellValue() + "|||" + "\t\t");
                        break;
                }

            }
            System.out.println();
        }
        
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
        num = in.nextInt();
        if(num >= 2) {
        	System.out.println("Undefined sheet");
            System.out.println("System closed");
        	workbook.close();
        }
        workbook.close();
    }
        System.out.println("System closed");

    }
    
}