package latestReader;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
	
	FileWriter writer;
	
	public TextWriter() {
		
		try {
			
			writer = new FileWriter("filename.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToFile(String txt) throws IOException {
		
		writer.write(txt + "\n");
	}
	
	public void writeToConsole(String txt) {
		
		System.out.println(txt);
	}
	
	public void closeFileWriter() throws IOException {
		
		writer.close();
	}

}
