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
    	for(int i=17; i<=85; i++) { // tüm satırları tek tek okumak için döngü yapmıştım. ahmet cevabın olduğu 22.satırdaki karşılık neyse onu gösteriyor
    		String col0 = excelapi.getCellData(num, 0, i); //mesaj adı
    		String col1 = excelapi.getCellData(num, 1, i); // mesaj kodu
    		String col3 = excelapi.getCellData(num,3,i); // mesaj veri içeriği
    		String col6 = excelapi.getCellData(num, 6, i); // veri tipi
    		
    		
    		String result = "message " + col0 + " {\n";
    		String result2 = null;
    		if(col0 != null) 
    		{

    			if(col1 != null)
    			{
    			
    				result += "\tid = " + col1 + "\n";
    			}
    		
    			if(col3 != null && col6 != null)
    			{
    			
    				result += "\t" + col3 + " " + col6;
    			}
    			
    			result += "\n}";
    			
    			writer.writeToFile(result);
    			writer.writeToConsole(result);
    			StringBuffer sb= new StringBuffer(result);  
   			 sb.deleteCharAt(sb.length()-2);  
    		}
    		
    		
    		 if(col0==null && col3!=null)
    		 {
    			 StringBuffer sb= new StringBuffer(result);  
    			 sb.deleteCharAt(sb.length()-1);  
    			 result2 = excelapi.messages(col0, col3, col6);
    			writer.writeToFile(result2);
    			writer.writeToConsole(result2);
    		}
    	
    	}

    	in.close();
    	writer.closeFileWriter();
	}

}
