package latestReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

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
    	String text;
    	int num = in.nextInt();
    	while(num != -1) {
        FileInputStream fis = new FileInputStream(new File("./src/latestReader/Ekinoks_Test_Excel.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheetAt(0);

        
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        FileWriter myWriter = new FileWriter("filename.txt");
        
        for (Row row : sheet) {

            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {

                    case Cell.CELL_TYPE_NUMERIC:
                    	
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        myWriter.write(cell.getNumericCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                    	
                        text = cell.getStringCellValue();
                        text=text.replaceAll("[-]", " ");
                        text=text.replaceAll("[.]", "_");
                        text=text.replaceAll("ı", "i");
                        text = StringUtils.stripAccents(text);
                        System.out.print((convert(text)) + "\t\t");
                        myWriter.write(convert(text) + "\t\t");
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
    

static String convert(String s)
{

    int cnt= 0;
    int n = s.length();
    char ch[] = s.toCharArray();
    int res_ind = 0;

    for (int i = 0; i < n; i++)
    {

        if (ch[i] == ' ')
        {
            cnt++;
            ch[0] = Character.toLowerCase(ch[0]);
            
            // burda i'den n'e kadar dönüyosun ama en son döngüde i+1 ile dizinin dışına çıkıyosun
            
            if(i != n - 1) {
            	
            	ch[i + 1] = Character.toUpperCase(ch[i + 1]);
            }
            continue;
        }

        else {
            ch[res_ind++] = ch[i];
        }
    }
    return String.valueOf(ch, 0, n - cnt);
}
}