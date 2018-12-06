package projects.fraction;

import java.util.Arrays;
import java.util.Scanner;


/*
 *  Read me: hey there, this is a little calculator that add, subtracts, multiplies, and divides fractions.
 *  
 *  Test cases:
 *  
 *  1/2 + 1/3 = 5/6
 *  1_1/2 + 1/4 = 1_3/4
 *  8/4 + 2 = 4
 *  -1 * -1/2 = 1/2
 *  -11/17 + -1/17 = -12/17
 *  1/3 * 3 = 1
 *  32 + 67 = 99
 *  quit
 *  
 */


@SuppressWarnings("unused")
public class Main {
	
	// string arrays of characters that indicate something special
	public final static String[] operations = {"+", "-", "*", "/"};
	public final static String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	
	// ENUM that is used to identify stuff
	public static enum Operator {
		ADD, SUBTRACT, MULTIPLY, DIVIDE;
	}
	
	public static void main(String[] args) {
		
		// scanner for general input
		Scanner in = new Scanner(System.in);
		
		// gets input
		System.out.println("Enter an operation or \"quit\":");
		String equation = in.nextLine();
		
		// scan input tokens
		while (!equation.equals("quit")) {
		
			// scanner of input to find each token in equation
			Scanner parsed = new Scanner(equation);
			
			// array to store all fractions in this problem
			Fraction[] fracs = new Fraction[2];
			
			// variable for operand
			Operator curOp = null;
			
			// number for array index
			int curFrac = 0;
			
			// loop through equation tokens
			while (parsed.hasNext()) {
				
				// store token as String
				String token = parsed.next();
				
				// identify token type
				if (token.contains("/") && token.length() > 1) {
					
					// code execution here indicates that we know this token is some type of fraction
					
					// tokens with "_" are mixed, so we need to do some extra work to them
					if (token.contains("_")) {
						// parse out the mixed part, make a fraction, add back the mixed part
						Scanner findIt = new Scanner(token);
						findIt.useDelimiter("_");
						int outside = findIt.nextInt();
						String frac = findIt.next();
						findIt.close();
						Fraction temp = new Fraction(frac);
						temp.numerator += temp.denominator*outside;
						fracs[curFrac] = temp;
						curFrac++;
						
						//CHECKPOINT 1
						//System.out.println(temp.toString());
						
					} else {
						// if it's not mixed, we can instantly turn the String into a Fraction, which is stored in an array
						Fraction temp = new Fraction(token);
						fracs[curFrac] = temp;
						curFrac++;
						
						//CHECKPOINT 1
						//System.out.println(temp.toString());
						
					}
					
				// if it's not an operand, check if it's a whole number, which is then converted to a n/1 fraction
				} else if (strHasAny(token, nums)) {
				
					Fraction temp = new Fraction(token + "/1");
					fracs[curFrac] = temp;
					curFrac++;
									
					//CHECKPOINT 1
					//System.out.println(temp.toString());		
				
				// if it's not a fraction, check if it's an operand
				} else if (strHasAny(token, operations)) {
					
					// find the type of operand
					if (token.contains("+")) {
						curOp = Operator.ADD;
					} else if (token.contains("-")) {
						curOp = Operator.SUBTRACT;
					} else if (token.contains("*")) {
						curOp = Operator.MULTIPLY;
					} else if (token.contains("/")) {
						curOp = Operator.DIVIDE;
					}
					
					//CHECKPOINT 1
					//System.out.println("OPERATION: " + token + " or " + curOp.toString());
				
				
				}
				
			}
			
			// for debug - lets you see the fractions
			//System.out.println( Arrays.toString(fracs));
			//System.out.println( fracs[0].toStringOld() + " | " + fracs[1].toStringOld());
			
			String answer = "";
			
			// switch to find the answer based on the operation type
			switch (curOp) {
			case ADD:
				answer = Fraction.add(fracs[0], fracs[1]).toString();
				break;
			case DIVIDE:
				answer = Fraction.divide(fracs[0], fracs[1]).toString();
				break;
			case MULTIPLY:
				answer = Fraction.multiply(fracs[0], fracs[1]).toString();
				break;
			case SUBTRACT:
				answer = Fraction.subtract(fracs[0], fracs[1]).toString();
				break;
			default:
				answer = Fraction.add(fracs[0], fracs[1]).toString();
			}
			
			// print answer
			System.out.println(" = "+answer);
			
			// close the scanner for this particular question
			parsed.close();
			
		
		// gets input again, for next loop thru
		System.out.println("\nEnter an operation or \"quit\":");
		equation = in.nextLine();
		
		}
		
	
		in.close();
		
	}
	
	// method for finding if a string has any characters from an array
	public static boolean strHasAny(String str, String[] strs) {
		for (String check: strs) {
			if (str.contains(check))
				return true;
		}
		return false;
	}
	
}
