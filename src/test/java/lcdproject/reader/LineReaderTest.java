package lcdproject.reader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class LineReaderTest {

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the line doesn't have a comma or the
	 * same line if it does.
	 */
	@Test
	public void canCheckCommaSeparatedStrings() {
		String errorMessage = "Invalid Line Sintax: Size and number must be separated by a comma.";
		assertEquals("LineReader returns error message for line with missing comma.", errorMessage,
				LineReader.checkLine("232"));
		assertEquals("LineReader returns the original line if separated with comma.", "2,3",
				LineReader.checkLine("2,3"));
	}

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the line has more than one comma.
	 * 
	 */
	@Test
	public void canCheckTwoOrMoreCommasInString() {
		String errorMessage = "Invalid Line Sintax: Two or more commas.";
		assertEquals("LineReader returns error message for line with two or more commas.", errorMessage,
				LineReader.checkLine("2,3,2,,"));
		assertEquals("LineReader returns the original line if separated with one comma.", "2,3",
				LineReader.checkLine("2,3"));
	}

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the size is not specified in the
	 * line.
	 * 
	 */
	@Test
	public void canCheckIfSizeIsSpecifiedInString() {
		String errorMessage = "Invalid Line Sintax: Specify a Size. ";
		assertEquals("LineReader returns error message for line with no specified size.", errorMessage,
				LineReader.checkLine(",2"));
		assertEquals("LineReader returns the original line if size is specified.", "2,3", LineReader.checkLine("2,3"));
	}

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the specified size is not a number.
	 * 
	 */
	@Test
	public void canCheckIfSizeIsaValidNumber() {
		String errorMessage = "Invalid Line Sintax: Size not a Number. ";
		assertEquals("LineReader returns error message if specified size is not a number.", errorMessage,
				LineReader.checkLine("e,2"));
		assertEquals("LineReader returns the original line if specified size is a number.", "2,3",
				LineReader.checkLine("2,3"));
	}

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the specified size is not the range
	 * [1-10].
	 * 
	 */
	@Test
	public void canCheckIfSizeIsInRange() {
		String errorMessage = "Invalid Line Sintax: Size out of range [1-10]. ";
		assertEquals("LineReader returns error message if specified size is not in ranger.", errorMessage,
				LineReader.checkLine("13,2"));
		assertEquals("LineReader returns the orignal line if specified size is in range.", "2,3",
				LineReader.checkLine("2,3"));
	}

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the number to display is not
	 * specified.
	 * 
	 */
	@Test
	public void canCheckIfNumberIsSpecifiedInString() {
		String errorMessage = "Invalid Line Sintax: Specify a number to display. ";
		assertEquals("LineReader returns error message for line with no specified number.", errorMessage,
				LineReader.checkLine("2,"));
		assertEquals("LineReader returns the original line if number is specified.", "2,3",
				LineReader.checkLine("2,3"));
	}

	/*
	 * This test checks the functionality of the checkLine method in LineReader. The
	 * method should return an error message if the number to display is not valid
	 * number.
	 * 
	 */
	@Test
	public void canCheckIfNumberToDisplayIsaValidNumber() {
		String errorMessage = "Invalid Line Sintax: Invalid number to display. ";
		assertEquals("LineReader returns error message if specified number is not a number.", errorMessage,
				LineReader.checkLine("2,e"));
		assertEquals("LineReader returns the original line if specified number is a number.", "2,3",
				LineReader.checkLine("2,3"));
	}

	@Test
	public void canReadInputLinesAndCallLcdPrintMethod() throws Exception {
		String testCases =    "2,2\n"
							+ ",1\n"
							+ "1,3\n"
							+ "e,2\n"
							+ "3,"
							+ "\n\n";
		InputStream inputStream = new ByteArrayInputStream(testCases.getBytes());

		String correctMessage = "Call LCD.print() with arguments: (2,2)(1,3)";
		assertEquals("LineReader reads input stream and calls LCD.print() with the valid line inputs found.",
				correctMessage, LineReader.readInputStream(inputStream));

	}
}
