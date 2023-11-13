package experiments;

import java.util.Date;

public class Demo {

	public static void main(String args[]) {

//		Date date = new Date();
		// method chaining >> this will reduce no. of lines
//		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		
//		String dateText = date.toString();
//		String dateTextWitoutSpaces = dateText.replace(" ", "_");
//		String dateTextWithoutColon = dateTextWitoutSpaces.replace(":", "_");
		
		// to get all the system properties
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));
	}

}
