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
	
	@Test
	public void canPrintUpperMiddleAndBottomSegments() {
		int size=1;
		int digit=8;
		String segmentDisplayed;

     	ByteArrayOutputStream displayContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(displayContent));

    	int upperSegment=1;
    	int middleSegment=size+2;
    	int bottomSegment=(2 * size) + 3;
    	
    	LCDprinter.printNumbers(size, Integer.toString(digit));

    	    	
		String expectedUpper=buildHorizontalSegment(size,digit,0);
		segmentDisplayed = getHorizontalSegment(displayContent.toString(),upperSegment,size);
	    assertEquals(expectedUpper, segmentDisplayed);

	    String expectedMiddle=buildHorizontalSegment(size,digit,3);
		segmentDisplayed = getHorizontalSegment(displayContent.toString(),middleSegment,size);
	    assertEquals(expectedUpper, segmentDisplayed);
	    
	    String expectedBottom=buildHorizontalSegment(size,digit,6);
		segmentDisplayed = getHorizontalSegment(displayContent.toString(),bottomSegment,size);
	    assertEquals(expectedUpper, segmentDisplayed);
	

	}
private String buildHorizontalSegment(int size,int digit, int segment) {
	
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
	
	String upper=" ";
	
	for (int i=2; i<size+2;i++) {
		if (segments[digit][segment]) upper+="-";
		else upper+=" ";
	}
	upper+="  ";
	return upper;
}

	private String getHorizontalSegment(String output,int segment,int size) {
		Matcher m = Pattern.compile("\r\n|\r|\n").matcher(output);
		int row = 0;
		int colSize=size+3;
		while (row<segment) {
			
			m.find();
			row++;
		}
		
		return output.substring(m.end()-colSize-1, m.end()-1);
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
