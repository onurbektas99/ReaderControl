package latestReader;


import java.io.FileWriter;
import java.util.Scanner;



public class ReadExcelData {

	public static void main(String[] args) throws Exception {
    	Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		//FileInputStream fis = new FileInputStream(new File("./src/latestReader/Ekinoks_Test_Excel.xlsx"));
		
		TextWriter writer = new TextWriter();
		
    	ExcelApiTest excelapi = new ExcelApiTest("./src/latestReader/Ekinoks_Test_Excel.xlsx");
    	for(int i=0; i<=83; i++) {
    		
    		String col3 = excelapi.getCellData(num,3,i);
    		String col6 = excelapi.getCellData(num, 6, i);
    		
    		if(col3 != null && col6 != null) {

    			String combined = col3 + " " + col6;
    			
    			writer.writeToFile(combined);
    			writer.writeToConsole(combined);
    		}
    	}
    	
    	in.close();
    	writer.closeFileWriter();
	}

}
