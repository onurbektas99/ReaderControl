package latestReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelApiTest {
	
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	int num_holder;
	public ExcelApiTest(String xlFilePath) throws Exception {
		
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
		
	}
	
	public String getCellData(int num, int colNum, int rowNum) {

		try {
			this.num_holder=num;
			sheet =workbook.getSheetAt(num);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
				String text = cell.getStringCellValue();
				text=text.replaceAll("[-]", " ");
                text=text.replaceAll("[.]", "_");
                text=text.replaceAll("ı", "i");
                text = StringUtils.stripAccents(text);
                text = convert(text);
                if(colNum == 6) {
                	text = text.toUpperCase();
                	text = StringUtils.stripAccents(text);
    				return text;
    				
                }
                else
				return text;
			}
			else if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return null;
			}
			else{
				String cellValue = String.valueOf(cell.getNumericCellValue());
				return cellValue;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "No matched value";
		}
		
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
