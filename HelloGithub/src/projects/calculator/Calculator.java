package projects.calculator;

import java.util.Scanner;

public class Calculator {
	
	int x,y;
	String operation;
	Scanner scan;
	
	public static void main(String[] args) {
		
		// fence post algorithm!
		Calculator calc = new Calculator();
		System.out.println("\n"+calc.calc());
		
		// while not quitting, do another calculation
		while (!calc.operation.equals("q")) {
			calc = new Calculator();
			System.out.println("\n"+calc.calc());
		}
		
	}
	
	// constructor
	public Calculator() {
		
		// make instance of scanner
		this.scan = new Scanner(System.in);
		
		// get operand
		setOperandIn();
		
		// if you don't quit, get x and/or y
		if (!(operation.equals("q"))) {
		
			// get x
			setXIn();
			
			// get y, if not a square root
			if (!this.operation.equals("|")) {
				setYIn();
			}
		}

	}
	
	// actually calculate the answer, return as a string that shows the full calculation
	public String calc() {
		
		// shorthand for operation
		String op = this.operation;
		
		// square root special case
		if (op.equals("|")) {
			return "|"+x+" = "+Math.sqrt(x);
		}
		
		// shorthand for the string of the calculation
		String calc = x + " " + op + " " + y + " = ";
		
		// different cases for different operations
		if (op.equals("^"))
			return calc + Math.pow(x, y);
		if (op.equals("*"))
			return calc + (x*y);
		if (op.equals("/"))
			return calc + (x/y);
		if (op.equals("+"))
			return calc + (x+y);
		if (op.equals("-"))
			return calc + (x-y);
		
		// assume you quit because no x or y
		return "quit";
		
	}
	
	// setters that use the scanner
	public void setXIn() {
		System.out.print("\nX: ");
		
		this.x = scan.nextInt();
	}
	public void setYIn() {
		System.out.print("\nY: ");
		
		this.y = scan.nextInt();
	}
	public void setOperandIn() {
		System.out.print("\nOperation: ");
		
		this.operation = scan.next();
	}
	
}
