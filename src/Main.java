import java.io.IOException;

/* 
 * File Name: Main.java
 * Created: 4-23-22
 * Purpose: To house the main method 
 */

public class Main {
	public static void main(String[] args) throws IOException  { 
		Table ReadingFile = new Table();
		Input input = new Input();
		input.newItem();
		input.editItem();
		input.deleteItem();
		ReadingFile.readTable();
	}
}
