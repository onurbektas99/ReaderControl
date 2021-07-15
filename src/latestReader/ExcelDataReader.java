package latestReader;


import java.util.Scanner;



public class ExcelDataReader {

	public static void main(String[] args) throws Exception {
    	Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		//FileInputStream fis = new FileInputStream(new File("./src/latestReader/Ekinoks_Test_Excel.xlsx"));
		
		TextWriter writer = new TextWriter();
		
    	ExcelApiTest excelapi = new ExcelApiTest("/Users/onurbektas/Ekinoks/Ekinoks_Test_Excel.xlsx");
    	System.out.print(excelapi.getCellData(num,3,12) + " ");
    	System.out.println(excelapi.getCellData(num, 6, 12) + " ");
    	for(int i=17; i<=83; i++) {
    		String col0 = excelapi.getCellData(num, 0, i);
    		String col1 = excelapi.getCellData(num, 1, i);
    		String col3 = excelapi.getCellData(num,3,i);
    		String col6 = excelapi.getCellData(num, 6, i);
			col0 = "message " + col0 + " {\n";

    		if( col1 != null) {
    		if(col3 != null || col6 != null || col1 != null) {
    	    	col1="\tid = " + col1 + "\n";

    			String combined = col0 + col1 + "\t" + col3 + " " + col6 + "\n }";
    			
    			writer.writeToFile(combined);
    			writer.writeToConsole(combined);
    		}
    	}
    	}
    	in.close();
    	writer.closeFileWriter();
	}

}
