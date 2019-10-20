package lcdproject.reader;

public class LineReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * checkLine() checks the next invalid line inputs:
	 * 1- String without a comma.
	 */
	
	public static String checkLine(String line) {
		String message = "";
		int indexOfComma = line.indexOf(',');
		if (indexOfComma == -1) {                                                       //check for string without comma.
			message += "Size and number must be separated by a comma.";
		}
		return (message.length() == 0) ? line : "Invalid Line Sintax: " + message;      //returns the original line if no errors.
	}

}
