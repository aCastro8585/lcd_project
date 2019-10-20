package lcdproject.reader;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void canCheckTwoOrMoreCommasInString() {
		String errorMessage = "Invalid Line Sintax: Two or more commas.";
		assertEquals("LineReader returns error message for line with two or more commas.", errorMessage,
				LineReader.checkLine("2,3,2,,"));
		assertEquals("LineReader returns the original line if separated with one comma.", "2,3",
				LineReader.checkLine("2,3"));
	}

	@Test
	public void canCheckIfSizeIsSpecifiedInString() {
		String errorMessage = "Invalid Line Sintax: Specify a Size. ";
		assertEquals("LineReader returns error message for line with no specified size.", errorMessage,
				LineReader.checkLine(",2"));
		assertEquals("LineReader returns the original line if size is specified.", "2,3", LineReader.checkLine("2,3"));
	}

	@Test
	public void canCheckIfSizeIsaValidNumber() {
		String errorMessage = "Invalid Line Sintax: Size not a Number. ";
		assertEquals("LineReader returns error message if specified size is not a number.", errorMessage,
				LineReader.checkLine("e,2"));
		assertEquals("LineReader returns the original line if specified size is a number.", "2,3",
				LineReader.checkLine("2,3"));
	}

	@Test
	public void canCheckIfSizeIsInRange() {
		String errorMessage = "Invalid Line Sintax: Size out of range [1-10]. ";
		assertEquals("LineReader returns error message if specified size is not in ranger.", errorMessage,
				LineReader.checkLine("13,2"));
		assertEquals("LineReader returns the orignal line if specified size is in range.", "2,3",
				LineReader.checkLine("2,3"));
	}
}
