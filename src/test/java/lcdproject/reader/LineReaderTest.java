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
		assertEquals("LineReader returns the same line if separated with comma.", "2,3", LineReader.checkLine("2,3"));
	}
}
