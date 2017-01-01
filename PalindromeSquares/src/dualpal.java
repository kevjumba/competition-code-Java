/*
ID: kevin.z3
LANG: JAVA
TASK: dualpal
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class dualpal {
	public static boolean isPalindrome(String num) {
		for(int i=0;i<num.length()/2;i++){
			if(num.charAt(i)!=num.charAt(num.length()-1-i)) return false;
		}
		
		return true;
	}

	public static String toBaseB(int N, int b) {
		int quotient = N/b;
	    int remainder = N%b;
	    String digit="";
	    if(remainder>=10){
	    	switch(remainder){
	    	case 10: digit = "A"; break;
	    	case 11: digit = "B"; break;
	    	case 12: digit = "C"; break;
	    	case 13: digit = "D"; break;
	    	case 14: digit = "E"; break;
	    	case 15: digit = "F"; break;
	    	case 16: digit = "G"; break;
	    	case 17: digit = "H"; break;
	    	case 18: digit = "I"; break;
	    	case 19: digit = "J"; break;
	    	case 20: digit = "K"; break;
	    	}
	    }
	    else{
	    	digit = Integer.toString(remainder);
	    }
	    if(quotient == 0) // base case
	    {
	        return digit;      
	    }
	    else
	    {
	        return toBaseB(quotient, b) + digit;
	    }    
	}
	
	private static void squareSolve(int b, PrintWriter pw) {
		for (int i = 1; i <= 300; i++) {
			String num = toBaseB(i, b);
			String numS = toBaseB(i*i, b);
			if (isPalindrome(numS)) {
				pw.println(num + " " + numS);
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("dualpal.in"));
		PrintWriter pw = new PrintWriter(new File("dualpal.out"));
		int N = sc.nextInt();
		int S = sc.nextInt();
		dualPalSolve(N, S, pw);
		pw.close();
	}

	private static void dualPalSolve(int n, int s, PrintWriter pw) {
		//n=number required
		//s=starting value
		int good = 0;
		for(int i=s+1;;i++){
			int numPal = 0;
			if(isPalindrome(Integer.toString(i))){
				numPal++;
			}
			for(int j=2;j<10;j++){
				if(isPalindrome(toBaseB(i,j)))
					numPal++;
				if(numPal>=2){
					good++;
					pw.println(i);
					break;
				}
				
			}
			if(good>=n){
				break;
			}
		}
	}

	
}
