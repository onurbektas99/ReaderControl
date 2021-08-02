package latestReader;

public class MessageStringUtil {

	public static String buildMessageContent(String col1, String col3, String col6) {
	
		StringBuilder sb = new StringBuilder();
		
		if(col1 != null) {
			
			sb.append("\tid = ");
			sb.append(col1);
			sb.append("\n");
		}
	
		if(col3 != null && col6 != null) {
			sb.append("\t");
			sb.append(col3);
			sb.append(" ");
			sb.append(col6);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
