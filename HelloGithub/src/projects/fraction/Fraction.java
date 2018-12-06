package projects.fraction;

import java.math.BigInteger;
import java.util.Scanner;

public class Fraction {

	// holders for fraction parts
	int numerator = 0;
	int denominator = 1;
	int whole = 0;
	
	
	// constructor that takes a string as an argument, then parses it into doubles
	public Fraction(String src) {
		Scanner tokenize = new Scanner(src);
		tokenize.useDelimiter("/");
		
		numerator = tokenize.nextInt();
		denominator = tokenize.nextInt();
		
		// CHECKPOINT 2:
		System.out.println("\tFraction "+this.hashCode()+"\n\tNumerator:"+numerator+"\n\tDenominator:"+denominator+"\n");
		
		tokenize.close();
	}
	
	
	/*
	 *  Static fraction math methods
	 */
	
	// adding method
	public static Fraction add(Fraction a, Fraction b) {
		
		// multiplying the denominators makes them common - necessary for addition
		int max = Math.max(a.denominator, b.denominator) * Math.min(a.denominator, b.denominator);
		a.setDenominator(max);
		b.setDenominator(max);
		
		// make a string, then a fraction from the sum
		String newFrac = a.numerator+b.numerator + "/" + max;
		Fraction temp = new Fraction(newFrac);
		
		// simplify and return
		temp.simplify();
		return temp;
		
	}
	
	// subtraction method
	public static Fraction subtract(Fraction a, Fraction b) {
		// subtraction is just addition with a negative, so we multiply the second fraction by -1
		return add(a, multiply(b, new Fraction("-1/1")));
	}
	
	// multiplication method
	public static Fraction multiply(Fraction a, Fraction b) {
		// pretty straight forward - multiply numerator and denominator, return simplified product
		String template = a.numerator * b.numerator + "/" + a.denominator * b.denominator;
		Fraction temp = new Fraction(template);
		temp.simplify();
		return temp;
	}
	
	// division method
	public static Fraction divide(Fraction a, Fraction b) {
		// make the reciprocal of the second fraction then multiply the two
		String inverse = b.denominator + "/" + b.numerator;
		return multiply(a, new Fraction(inverse));
	}
	
	// simplification method
	public void simplify() {
		// check if 0/n (because that's 0/0)
		if (this.numerator == 0) {
			this.setDenominator(0);
		} else {
			// create 3 BigInteger objects
			BigInteger bi1, bi2, bi3;

			// assign values to bi1, bi2 based on numerator and denominator
			//System.out.println(Integer.toString(this.numerator) + " " + Integer.toString(this.denominator));
			bi1 = new BigInteger(Integer.toString((int) this.numerator));
			bi2 = new BigInteger(Integer.toString((int) this.denominator));

			// assign GCD of bi1, bi2 to bi3
			bi3 = bi1.gcd(bi2);
			
			// divide the numerator and denominator of this fraction to simplify  
			this.denominator /= Integer.parseInt(bi3.toString());
			this.numerator /= Integer.parseInt(bi3.toString());
			
			// deal with whole numbers
			while (numerator >= denominator) {
				numerator -= denominator;
				whole ++;
			}
		}
	}
	
	// getters. not super necessary but whatever.
	public double getNumerator() {
		return this.numerator;
	}
	public double getDenominator() {
		return this.denominator;
	}
	
	// special denominator setter, accounts for fractions
	public void setDenominator(int denominator) {
		int multiplier = denominator/this.denominator;
		this.numerator *= multiplier;
		this.denominator *= multiplier;
	}
	
	// to string, which get used a lot for printing answers and debugging
	public String toString(boolean full) {
		return this.numerator + "/" + this.denominator;
	}
	
	// to string with mixed fractions
	public String toString() {
		if (! (whole==0)) {
			if ( numerator == 0 )
				return Integer.toString(this.whole);
			return this.whole + "_" + toString(true);
		}
		return toString(true);
	}
	
}
