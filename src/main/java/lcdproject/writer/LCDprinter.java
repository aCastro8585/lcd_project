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
	
	
	public static void printNumbers(int size, String number) {
		int digit;
		for (int i = 0; i < 2 * size + 3; i++) {
			for (int j = 0; j < number.length(); j++) {
				digit=number.charAt(j) - '0';
				if ((i % (size + 1)) == 0) {
					System.out.print(" ");

					for (int k=2;k<size+2;k++) { 
						
						if (getSegment(digit,i / (size + 1)*3)) System.out.print("-");
						else System.out.print(" ");

					}
					System.out.print(" ");
					}
				
			
				else {
				
				for (int k = 0; k < size + 2; k++) {

					System.out.print(" ");

				}
				
			}
				System.out.print(" ");
		}
			System.out.print("\n");
	}
		
	}
}
