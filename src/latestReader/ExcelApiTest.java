package latestReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Console;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.regex.Matcher;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.soap.Text;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelApiTest {
	
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	public String converter;
	int num_holder;
	private String result2;
	public ExcelApiTest(String xlFilePath)  {
		try {
			fis = new FileInputStream(xlFilePath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		}
		catch(Exception e) {
			System.out.println("HATA"+e.toString());
		}
	
		
	}
	
	public String getCellData(int num, int colNum, int rowNum)
	{ 
		try
		{
			this.num_holder=num;
			sheet =workbook.getSheetAt(num);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if(cell.getCellType() == Cell.CELL_TYPE_STRING)
			{
				String text = cell.getStringCellValue();
				String text2 =cell.getStringCellValue();
				Pattern pattern = Pattern.compile("[+/'*!^&%=.?-]+");
				Matcher matcher = pattern.matcher(text);
				text = matcher.replaceAll("_");
				text = StringUtils.stripAccents(text);
                if(colNum == 6) {
                    text = convert(text);
                	text = text.toUpperCase();
                	text = StringUtils.stripAccents(text);
    				return text;
                }
                if(colNum == 0) {
                	text = convert(text);
                	text=isUpperCase(text);
                	text = classConvention(text);
                    text=text.replaceAll("??", "i");
                	return text;
                }
                if(colNum == 3)
                {
                	
                if(text.equals("{")) {
                	
                	text = "dynamicList[]" + text.toLowerCase();
                	
                }
                else {
                	
                	text=text.toLowerCase();
                }
                
                text = convert(text);
                text=text.replaceAll("??", "i");
                return text;
                }
                else {
				return text;
                }
			}
			else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			{
				if((colNum = 1) == Cell.CELL_TYPE_BLANK) {
					return " ";
				}
				else
				{
				return null;
				}
			}
			else
			{
				String cellValue = String.valueOf(cell.getNumericCellValue());
				return cellValue;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "No matched value";
		}
		
	}
	public String convert(String s)
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
	            
	            // burda i'den n'e kadar d??n??yosun ama en son d??ng??de i+1 ile dizinin d??????na ????k??yosun
	            
	            if(i != n - 1)
	            {
	            	
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
	
	public String classConvention(String s)
	{
	    int cnt= 0;
	    int n = s.length();
	    char ch[] = s.toCharArray();
	    int res_ind = 0;
	    
	    for (int i = 0; i < n; i++)
	    {

	    	ch[0] = Character.toUpperCase(ch[0]);
	    	if (ch[i] == ' ')
	        {
	            cnt++;
	            
	            
	            // burda i'den n'e kadar d??n??yosun ama en son d??ng??de i+1 ile dizinin d??????na ????k??yosun
	            
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
	
	public String letter(String s)
	{
	    int cnt= 0;
	    int n = s.length();
	    char ch[] = s.toCharArray();
	    
	    ch[n-1] = Character.toLowerCase(ch[n-1]);
	    return String.valueOf(ch, 0, n - cnt);
	}
	
	public String isUpperCase(String str)
	{
		if(str.equals(str.toUpperCase()))
		{
			return str.toLowerCase();
		}
		else
		{
			return str;
		}
	}
	
	public String messages(String x, String y, String z)
	{
		
		
    			
				result2 = "\t" + y + " " + z;
			
		
		return result2;
		
	}
	
	
}