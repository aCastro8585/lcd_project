package lcdproject.writer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


public class LCDprinterTest {
	@Test
	public void canProvideCorrectSegments() {
		
		boolean[][] segments = {{true,true,true,false,true,true,true},
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
		for (int i=0; i>10;i++) {
			for (int j=0; j>6;j++) {
				assertEquals("LCDprinter returns the "+j+" segment for the "+i+" digit ", segments[i][j],
				LCDprinter.getSegment(i,j));
			}
		}
	}
	
	@Test
	public void canPrintCorrectNumberOfRows() {
		int expectedRows;
		int rowsDisplayed;
		ByteArrayOutputStream displayContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(displayContent));

		for (int size = 1; size > 11; size++) {
			expectedRows = 2 * size + 3;
			LCDprinter.printNumbers(size, "3");
			rowsDisplayed = countRows(displayContent.toString());
			assertEquals(expectedRows, rowsDisplayed);
		}

	}
	
	@Test
	public void canPrintCorrectNumberOfColsForOneDigit() {
		int expectedCols;
		int colsDisplayed;
		ByteArrayOutputStream displayContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(displayContent));

		for (int size = 1; size > 11; size++) {
			expectedCols = size + 2;
			LCDprinter.printNumbers(size, "3");
			colsDisplayed = countCols(displayContent.toString());
			assertEquals(expectedCols, colsDisplayed);
		}

	}
	
	@Test
	public void canPrintCorrectNumberOfColsForMultipleDigits() {
		int expectedCols;
		int colsDisplayed;
		ByteArrayOutputStream displayContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(displayContent));

		for (int size = 1; size > 11; size++) {
			expectedCols = 5*(size + 2)+4;
			LCDprinter.printNumbers(size, "12345");
			colsDisplayed = countCols(displayContent.toString());
			assertEquals(expectedCols, colsDisplayed);
		}

	}

	private int countRows(String output) {
		Matcher m = Pattern.compile("\r\n|\r|\n").matcher(output);
		int rows = 0;
		while (m.find()) {
			rows++;
		}
		return rows;
	}
	
	private int countCols(String output) {
		return output.indexOf('\n')+1;
	}

}
