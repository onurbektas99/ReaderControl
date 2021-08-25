package latestReader;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class ExcelDataReader {

	public static void main(String[] args) throws Exception {
    	Scanner in = new Scanner(System.in);
		int num = 0;//in.nextInt();
		//FileInputStream fis = new FileInputStream(new File("./src/latestReader/Ekinoks_Test_Excel.xlsx"));
		
		TextWriter writer = new TextWriter();
		    	ExcelApiTest excelapi = new ExcelApiTest("/Users/onurbektas/Ekinoks/Ekinoks_Test_Excel.xlsx");
    	

    	
    	System.out.print(excelapi.getCellData(num,3,12) + " ");
    	System.out.println(excelapi.getCellData(num, 6, 12) + " ");
    	
    	
    	StringBuilder sb = new StringBuilder();

    	StringBuilder col3sb= new StringBuilder();
    	StringBuilder col6sb= new StringBuilder();
		boolean ayniMesaj=true;

    	for(int i=17; i<=93; i++) { // tüm satırları tek tek okumak için döngü yapmıştım. ahmet cevabin olduğu 22.satirdaki karşılık neyse onu gösteriyor
    		String col0 = excelapi.getCellData(num, 0, i); //mesaj adi
    		String col1 = excelapi.getCellData(num, 1, i); // mesaj kodu
    		String col3 = excelapi.getCellData(num,3,i); // mesaj veri içeriği
    		String col6 = excelapi.getCellData(num, 6, i); // veri tipi
    		
    		
    		// mesaj adi null değilse mesaji başlattik
    		// mesaj adi null ise işine ekleyeceğiz
    		
    		if(col0 != null) {
    		
    			// mesaj adi null değilse önceki mesaji yazdir
    			
    			// condition eklememiz lazm
    			
    			if(!sb.toString().equals("")) {
    				
    				
    				// yazdirmadan önce parantezi kapat
    				
    				sb.append("\n}");
    				writer.writeToConsole(sb.toString());
    				writer.writeToFile(sb.toString());
    				// yazdirdiktan sonra string builderi temizle
    				sb = new StringBuilder();
    			}
    			sb.append("message ");
    			sb.append(col0);
    			sb.append(" {\n");
    		}
    		
    		
    		
    		
    		if ((col3 != null || col1 != null)) {
    			
    			// bunu bir kosula kadar yazmaliyiz
    			// o kosulu sonraki mesaj adi gorene kadar yapabiliriz simdilik
        		//col3sb.append(col3);
    			sb.append(MessageStringUtil.buildMessageContent(col1, col3, col6));
    			
    		}
    		
    	}
     	//System.out.println("LISTE ELEMANLARI"+ excelapi.listMaker(col3sb.toString()));

    	in.close();
    	writer.closeFileWriter();
    	
    	
 
	
	}
	
	

}
