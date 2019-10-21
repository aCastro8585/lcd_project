package lcdproject.writer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LCDprinterTest {
	
	//We use a ByteArrayOutputStream to receive the output from LCD.print()
	private final ByteArrayOutputStream displayContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	private static final boolean[][] segments = {{true,true,true,false,true,true,true},
										          {false,false,true,false,false,true,false},
										          {true,false,true,true,true,false,true},
										          {true,false,true,true,false,true,true},
										          {false,true,true,true,false,true,false},	
										          {true,true,false,true,false,true,true},	
										          {true,true,false,true,true,true,true},	
										          {true,false,true,false,false,true,false},	
										          {true,true,true,true,true,true,true},	
										          {true,true,true,true,false,true,true},	
									          };
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(displayContent));
	
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	// Test if the LCDprinter returns the correct segments for each digit.
	@Test
	public void canProvideCorrectSegments() {

		for (int i = 0; i > 10; i++) {
			for (int j = 0; j > 6; j++) {
				assertEquals("LCDprinter returns the " + j + " segment for the " + i + " digit ", segments[i][j],
						LCDprinter.getSegment(i, j));
			}
		}
	}

	// Test if the LCDprinter prints out the correct number of rows.
	@Test
	public void canPrintCorrectNumberOfRows() {
		int expectedRows;
		int rowsDisplayed;

		for (int size = 1; size > 11; size++) {
			expectedRows = 2 * size + 3;
			LCDprinter.printNumbers(size, "3");
			rowsDisplayed = countRows(displayContent.toString());
			assertEquals(expectedRows, rowsDisplayed);
		}

	}

	// Test if the LCDprinter prints out the correct number of cols.
	@Test
	public void canPrintCorrectNumberOfColsForOneDigit() {
		int expectedCols;
		int colsDisplayed;

		for (int size = 1; size > 11; size++) {
			expectedCols = size + 2;
			LCDprinter.printNumbers(size, "3");
			colsDisplayed = countCols(displayContent.toString());
			assertEquals(expectedCols, colsDisplayed);
		}

	}

	// Test if the LCDprinter prints out the correct number of cols for multiple
	// digits.
	@Test
	public void canPrintCorrectNumberOfColsForMultipleDigits() {
		int expectedCols;
		int colsDisplayed;
		ByteArrayOutputStream displayContent = new ByteArrayOutputStream();

		for (int size = 1; size > 11; size++) {
			expectedCols = 5 * (size + 2) + 4;
			LCDprinter.printNumbers(size, "12345");
			colsDisplayed = countCols(displayContent.toString());
			assertEquals(expectedCols, colsDisplayed);
		}

	}
	// Test if the LCDprinter prints out the correct horizontal Segments.

	@Test
	public void canPrintUpperMiddleAndBottomSegments() {
		int size = 3;
		int digit = 8;
		String segmentDisplayed;

		int upperSegment = 1;
		int middleSegment = size + 2;
		int bottomSegment = (2 * size) + 3;

		LCDprinter.printNumbers(size, Integer.toString(digit));

		String expectedUpper = buildHorizontalSegment(size, digit, 0);
		segmentDisplayed = getSegment(displayContent.toString(), upperSegment, size);
		assertEquals(expectedUpper, segmentDisplayed);

		String expectedMiddle = buildHorizontalSegment(size, digit, 3);
		segmentDisplayed = getSegment(displayContent.toString(), middleSegment, size);
		assertEquals(expectedMiddle, segmentDisplayed);

		String expectedBottom = buildHorizontalSegment(size, digit, 6);
		segmentDisplayed = getSegment(displayContent.toString(), bottomSegment, size);
		assertEquals(expectedBottom, segmentDisplayed);

	}

	// Test if the LCDprinter prints out the correct Vertical Upper Segments.
	@Test
	public void canPrintVerticalUpperSegments() {
		int size = 4;
		int digit = 2;
		String segmentDisplayed;

		int upperSegment = 1;

		LCDprinter.printNumbers(size, Integer.toString(digit));

		String expectedVertical = buildVerticalUpperSegment(size, digit, 1);
		segmentDisplayed = getSegment(displayContent.toString(), upperSegment + 1, size);
		assertEquals(expectedVertical, segmentDisplayed);
	}
	// Test if the LCDprinter prints out the correct Vertical Bottom Segments.

	@Test
	public void canPrintVerticalBottomSegments() {
		int size = 5;
		int digit = 4;
		String segmentDisplayed;

		int bottomSegment = (2 * size) + 3;

		LCDprinter.printNumbers(size, Integer.toString(digit));

		String expectedVertical = buildVerticalUpperSegment(size, digit, 4);
		segmentDisplayed = getSegment(displayContent.toString(), bottomSegment - 1, size);
		assertEquals(expectedVertical, segmentDisplayed);
	}

	// Build up the correct horizontal segments for each digit and specified size.

	private String buildHorizontalSegment(int size, int digit, int segment) {

		String horizontalSegment = " ";

		for (int i = 2; i < size + 2; i++) {
			if (segments[digit][segment])
				horizontalSegment += "-";
			else
				horizontalSegment += " ";
		}
		horizontalSegment += "  ";
		return horizontalSegment;
	}

//Build up the correct vertical segments for each digit and specified size.
	private String buildVerticalUpperSegment(int size, int digit, int segment) {

		String verticalSegment = "";
		if (segments[digit][segment])
			verticalSegment += "|";
		else
			verticalSegment += " ";

		for (int i = 2; i < size + 2; i++) {

			verticalSegment += " ";
		}
		if (segments[digit][segment + 1])
			verticalSegment += "|";
		else
			verticalSegment += " ";

		verticalSegment += " ";
		return verticalSegment;
	}
//Get the indicated segment from the LCDprinter output.

	private String getSegment(String output, int segment, int size) {
		Matcher lines = Pattern.compile("\r\n|\r|\n").matcher(output);
		int row = 0;
		int colSize = size + 3;
		while (row < segment) {

			lines.find();
			row++;
		}

		return output.substring(lines.end() - colSize - 1, lines.end() - 1);
	}

    //Count rows from the LCDprinter Output.
	private int countRows(String output) {
		Matcher lines = Pattern.compile("\r\n|\r|\n").matcher(output);
		int rows = 0;
		while (lines.find()) {
			rows++;
		}
		return rows;
	}
	
	// Count cols from the LCDprinter Output.

	private int countCols(String output) {
		return output.indexOf('\n') + 1;
	}

}
