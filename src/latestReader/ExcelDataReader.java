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
    	
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i=17; i<=85; i++) { // tüm satırları tek tek okumak için döngü yapmıştım. ahmet cevabın olduğu 22.satırdaki karşılık neyse onu gösteriyor
    		String col0 = excelapi.getCellData(num, 0, i); //mesaj adı
    		String col1 = excelapi.getCellData(num, 1, i); // mesaj kodu
    		String col3 = excelapi.getCellData(num,3,i); // mesaj veri içeriği
    		String col6 = excelapi.getCellData(num, 6, i); // veri tipi
    		
    		
    		// mesaj adı null değilse mesajı başlattık
    		// mesaj adı null ise içine ekleyeceğiz
    		
    		
    		if(col0 != null) {
    		
    			// mesaj adı null değilse önceki mesajı yazdır
    			
    			// condition eklememiz lazm
    			
    			if(!sb.toString().equals("")) {
    				
    				
    				// yazdırmadan önce parantezi kapat
    				
    				sb.append("\n}");
    				writer.writeToConsole(sb.toString());
    				
    				// yazdırdıktan sonra string builderi temizle
    				sb = new StringBuilder();
    			}
    			
    			sb.append("message ");
    			sb.append(col0);
    			sb.append(" {\n");
    		}
    		
    		if (col3 != null || col1 != null) {
    			
    			// bunu bir koşula kadar yazmalıyız
    			// o koşulu sonraki mesaj adı görene kadar yapabiliriz şimdilik
    			sb.append(MessageStringUtil.buildMessageContent(col1, col3, col6));
    		}
    	}

    	in.close();
    	writer.closeFileWriter();
	}
	
	

}
