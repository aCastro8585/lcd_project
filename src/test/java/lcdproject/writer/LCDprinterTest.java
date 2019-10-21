package lcdproject.writer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

}
