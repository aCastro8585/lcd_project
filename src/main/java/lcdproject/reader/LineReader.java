package lcdproject.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import lcdproject.writer.LCDprinter;

public class LineReader {
	public static final Pattern isNumber = Pattern.compile("\\d+");
	/*
	 * readInputStream() reads an input stream of strings with the format (size,number),
	 * checks for each line and discards invalid format strings, otherwise it calls
	 * LCD.print() just with the valid lines found.
	 */
	

	public static String readInputStream(InputStream inputStream) throws Exception {
		String line, checkedLine, number;
		String[] lineVector;
		int size;
		String msj = "Call LCD.print() with arguments: ";
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		line = reader.readLine();

		while (!line.contentEquals("")) {  //checks for empty line to finish reading input stream.
			checkedLine = checkLine(line);
			if (checkedLine.charAt(0) == 'I') { //if a error is found, the line is discarded.
				System.out.println(checkedLine);
				line = reader.readLine();
				continue;
			}
			lineVector= line.split(",");
			size=Integer.parseInt(lineVector[0]);
			number=lineVector[1];
			msj += "(" + line + ")";
			LCDprinter.printNumbers(size, number); //Calls printer on valid lines;
			line = reader.readLine();

		}
		reader.close();
		return msj;
	}

	/*
	 * checkLine() checks the next invalid line inputs: 1- String without a comma.
	 * 2- String with more than one comma. 3- String with no specified size. 4- Size
	 * is a valid number. 5- Size is in range. 6- Number to display is specified. 7-
	 * Number to display is a valid number.
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
						int size = Integer.parseInt(lineArray[0]);
						if (1 > size || size > 10)// check if size is in the range
							message += "Size out of range [1-10]. ";
					}
				}
				if (lineArray.length == 1) { // check if number is specified
					message += "Specify a number to display. ";
				} else {
					if (!isNumber.matcher(lineArray[1]).matches()) { // check if number to display is a number
						message += "Invalid number to display. ";
					}
				}
			}

		}
		return (message.length() == 0) ? line : "Invalid Line Sintax: " + message; // returns the original line if no
																					// errors found.
	}

}
