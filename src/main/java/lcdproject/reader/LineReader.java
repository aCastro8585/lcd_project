package lcdproject.reader;

import java.util.regex.Pattern;

public class LineReader {
	private static final Pattern isNumber = Pattern.compile("\\d+");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * checkLine() checks the next invalid line inputs: 1- String without a comma.
	 */

	public static String checkLine(String line) {
		String message = "";
		int indexOfComma = line.indexOf(',');
		int checkMoreThanOneComma = line.lastIndexOf(',');

		if (indexOfComma == -1) { // check for string without comma.
			message += "Size and number must be separated by a comma.";
		} else {
			if (indexOfComma != checkMoreThanOneComma) { // check for two or more commas.

				message += "Two or more commas.";
			} else {
				String[] lineArray = line.split(",");
				if (lineArray[0].contentEquals("")) { // check for not specified size.
					message += "Specify a Size. ";
				} else {
					if (!isNumber.matcher(lineArray[0]).matches()) { // check if size is a number
						message += "Size not a Number. ";
					} else {
						int size = Integer.parseInt(lineArray[0]); // check if size is in the range

						if (1 > size || size > 10)
							message += "Size out of range [1-10]. ";
					}

				}
			}

		}
		return (message.length() == 0) ? line : "Invalid Line Sintax: " + message; // returns the original line if no
																					// errors found.
	}

}
