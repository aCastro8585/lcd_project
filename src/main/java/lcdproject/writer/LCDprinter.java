package lcdproject.writer;

public class LCDprinter {
	private static final boolean[][] SEGMENTS = {{true,true,true,false,true,true,true},
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
	public static boolean getSegment (int digit, int segment) {
		return SEGMENTS[digit][segment];
	}

}
