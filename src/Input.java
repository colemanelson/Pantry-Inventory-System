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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	//This method overwrites Table.csv, and when called, the table on the GUI will need to be refreshed
	public void editItem() throws FileNotFoundException {
		String itemName= JOptionPane.showInputDialog("Enter the item name you would like to edit: ");
		Table ReadingFile = new Table();
		String[][] array = ReadingFile.readTable();
		
		int row = array.length;
		
		for (int i=0; i<row; i++) {
			for (int j=0; j<3; j++) {
				if (itemName.equals(array[i][j])) {
					String newName = JOptionPane.showInputDialog("Enter a new name: ");
					
					Boolean newDateCheck = false;
					String newDateError = "Enter a new expiration/best by date: ";
					String newDateInput = "";
					
					Boolean newQuantityCheck = false;
					String newQuantityError = "Enter a new item qunatity: ";
					String newItemQuantity = "";
										
					while(newQuantityCheck == false) {
						try {
							newItemQuantity = JOptionPane.showInputDialog(null, newQuantityError, "");
							
							String regex = "[+-]?[0-9]+";
							Pattern p = Pattern.compile(regex);
							Matcher m = p.matcher(newItemQuantity);
							
							if(!m.find() && !m.group().equals(newItemQuantity)) {	//this is designed to trigger the catch in the event a non-number is chosen
								newQuantityCheck = true;
							}
							
							newQuantityCheck = true;
						}
						catch( IllegalStateException e ){
							newQuantityError = "ERROR: Invalid Number";
						}
						
					}
					
					while (newDateCheck == false) {
					
						try {
							
							newDateInput = JOptionPane.showInputDialog(null, newDateError, "YYYY/MM/DD");
							DateTimeFormatter f = DateTimeFormatter.ofPattern ( "uuuu/MM/dd" );
							
							LocalDate ld = LocalDate.parse ( newDateInput , f );
											
							newDateCheck = true;
							
						} 
						catch ( DateTimeParseException e ) {
							newDateError = "ERROR: Check your formatting";
						}
					}
					
					array[i][j] = newName;
					j++;
					
					array[i][j] = newItemQuantity;
					j++;
					
					array[i][j] = newDateInput;
					
					String path = "Table.csv";
					File file = new File(path);
	                PrintWriter writer = new PrintWriter(file);
					
					for (int k = 0; k < row; k++) {
						
						for (int l = 0; l < 3; l++) {
							writer.print(array[k][l]+",");
							if (l == 2) {
								writer.print("\n");
							}
							
						}
					}
					
					writer.close();
					break;
				}
			}
		}
	}
	
	//This method overwrites Table.csv, and when called, the table on the GUI will need to be refreshed
	public void deleteItem() throws FileNotFoundException {
		String deleteName= JOptionPane.showInputDialog("Enter the item name you would like to delete: ");
		
		Table ReadingFile = new Table();
		String[][] array = ReadingFile.readTable();
		
		int row = array.length;
		
		String path = "Table.csv";
		File file = new File(path);
        PrintWriter writer = new PrintWriter(file);
        
        Boolean deleteCheck = false;
		
		for (int i=0; i<row; i++) {
			for (int j=0; j<3; j++) {
				if (deleteName.equals(array[i][j])) {
					deleteCheck = true;
					break;
				}
				else {
					writer.print(array[i][j]+",");
				}
				if (j == 2) {
					writer.print("\n");
				}
			}
		}
		writer.close();
		if (deleteCheck == false) {
			JOptionPane.showMessageDialog(frame, "Item not detected, check spelling and try again");
		}
	}
	
    JFrame frame = new JFrame();
}