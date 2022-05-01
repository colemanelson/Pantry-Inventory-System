/* 
 * File Name: Input.java
 * Created: 5-1-22
 * Purpose: A class that takes user input
 * and edits the table array with the input 
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalStateException;

public class Input {

	public void newItem() throws IOException {
		
		Boolean dateCheck = false;
		String dateError = "Enter the expiration/best by date: ";
		String dateInput = "";
		
		Boolean quantityCheck = false;
		String quantityError = "Enter the item qunatity: ";
		String itemQuantity = "";
		
		String itemName= JOptionPane.showInputDialog("Enter item name: ");
		
		while(quantityCheck == false) {
			try {
				itemQuantity = JOptionPane.showInputDialog(null, quantityError, "");
				
				String regex = "[+-]?[0-9]+";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(itemQuantity);
				
				if(!m.find() && !m.group().equals(itemQuantity)) {	//this is designed to trigger the catch in the event a non-number is chosen
					quantityCheck = true;
				}
				
				quantityCheck = true;
			}
			catch( IllegalStateException e ){
				quantityError = "ERROR: Invalid Number";
			}
			
		}
		
		while (dateCheck == false) {
		
			try {
				
				dateInput = JOptionPane.showInputDialog(null, dateError, "YYYY/MM/DD");
				DateTimeFormatter f = DateTimeFormatter.ofPattern ( "uuuu/MM/dd" );
				
				LocalDate ld = LocalDate.parse ( dateInput , f );
								
				dateCheck = true;
				
			} 
			catch ( DateTimeParseException e ) {
				dateError = "ERROR: Check your formatting";
			}
		}
		
		FileWriter pw = new FileWriter("Table.csv",true);
		pw.append(itemName);
		pw.append(",");
		pw.append(itemQuantity);
		pw.append(",");
		pw.append(dateInput);
		pw.close();
		
	}
	
    JFrame frame = new JFrame();
}