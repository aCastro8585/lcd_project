package lcdproject.writer;

/*
 * LCDprinter prints out each digit by printing the digit 8 first,
 * and then it turns off the unused segments.
 * The segments are numbered like this:
 *    0          -
 *   1 2        | |
 *    3          -
 *   4 5        | |
 *    6          -
 */



public class LCDprinter {
	
	//These are the segments used by each digit.
												//Segments:  1,2,3,4,5,6
	private static final boolean[][] SEGMENTS = {{true,true,true,false,true,true,true},  //0
										          {false,false,true,false,false,true,false},//1
										          {true,false,true,true,true,false,true},//2
										          {true,false,true,true,false,true,true},//3
										          {false,true,true,true,false,true,false},//4
										          {true,true,false,true,false,true,true},//5
										          {true,true,false,true,true,true,true},//6
										          {true,false,true,false,false,true,false},//7
										          {true,true,true,true,true,true,true},	//8
										          {true,true,true,true,false,true,true},//9
									            };

	public static boolean getSegment(int digit, int segment) {
		return SEGMENTS[digit][segment];
	}

	public static void printNumbers(int size, String number) {
		int digit;
		for (int i = 0; i < 2 * size + 3; i++) {  //prints out the number of rows
			for (int j = 0; j < number.length(); j++) {  //iterate over digits
				digit = number.charAt(j) - '0';
				if ((i % (size + 1)) == 0) { // selects the horizontal lines
					System.out.print(" ");

					for (int k = 2; k < size + 2; k++) {   

						if (getSegment(digit, i / (size + 1) * 3))
							System.out.print("-");
						else
							System.out.print(" ");

					}
					System.out.print(" ");
				}
				if (i > 0 && i < (size + 1)) {// selects the vertical upper lines

					if (getSegment(digit, 1))
						System.out.print("|");
					else
						System.out.print(" ");

					for (int k = 2; k < size + 2; k++) {
						System.out.print(" ");

					}
					if (getSegment(digit, 2))
						System.out.print("|");
					else
						System.out.print(" ");
				}

				if (i > (size + 1) && i < (2 * size + 2)) {// selects the vertical bottom lines

					if (getSegment(digit, 4))
						System.out.print("|");
					else
						System.out.print(" ");

					for (int k = 2; k < size + 2; k++) {
						System.out.print(" ");

					}
					if (getSegment(digit, 5))
						System.out.print("|");
					else
						System.out.print(" ");
				}
				System.out.print(" ");
			}
			System.out.print("\n");
		}
			
	}
		
	}

