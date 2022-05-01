import java.io.IOException;

/* 
 * File Name: Main.java
 * Created: 4-23-22
 * Purpose: To house the main method 
 */

public class Main {
	public static void main(String[] args) throws IOException  { 
		Input input = new Input();
		input.newItem();
		Table ReadingFile = new Table();
		ReadingFile.readTable();
	}
}
