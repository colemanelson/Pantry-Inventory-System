/* File Name: Table.java
 * Created: 4-23-22
 * Purpose: To read and write the CSV file that will
 * store the information for our pantry's table
 */

import java.io.*;
import java.util.*;


public class Table {  
	
	public String[][] readTable() {
		String line = "";  
		String splitBy = ",";
		String[][] array = null;
		
		try   {  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader ReadingFile = new BufferedReader( new InputStreamReader(new FileInputStream("Table.csv"), "UTF-8")); //the "UTF-8" portion of this code is to eliminate BOM
			
			ReadingFile.mark(1);
			if (ReadingFile.read() != 0xFEFF) //BOM is now U+FEFF, so we skip the first character if it's U+FEFF
				ReadingFile.reset();
			
			List<String[]> lines = new ArrayList<String[]>();
			while ((line = ReadingFile.readLine()) != null) {
			     lines.add(line.split(splitBy));
			}

			// convert our list to a String array.
			array = new String[lines.size()][0];
			lines.toArray(array);			
		}   
		
		catch (IOException e)   {  
			e.printStackTrace();  
		}
		return array;  
	}
}
