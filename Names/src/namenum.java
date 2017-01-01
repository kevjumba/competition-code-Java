/*
ID: kevin.z3
LANG: JAVA
TASK: namenum
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class namenum {
	public static ArrayList<String> permutations;
	public static String toID(String word){
		String integer = "";
		for(int i=0;i<word.length();i++){
			if(word.charAt(i)=='A' ||
			   word.charAt(i)=='B' ||
			   word.charAt(i)=='C' )
				integer += "2";
			else if(word.charAt(i)=='D' ||
					word.charAt(i)=='E' ||
					word.charAt(i)=='F' )
						integer += "3";
			else if(word.charAt(i)=='G' ||
					word.charAt(i)=='H' ||
					word.charAt(i)=='I' )
						integer += "4";
			else if(word.charAt(i)=='J' ||
					word.charAt(i)=='K' ||
					word.charAt(i)=='L' )
						integer += "5";
			else if(word.charAt(i)=='M' ||
					word.charAt(i)=='N' ||
					word.charAt(i)=='O' )
						integer += "6";
			else if(word.charAt(i)=='P' ||
					word.charAt(i)=='R' ||
					word.charAt(i)=='S' )
						integer += "7";
			else if(word.charAt(i)=='T' ||
					word.charAt(i)=='U' ||
					word.charAt(i)=='V' )
						integer += "8";
			else if(word.charAt(i)=='W' ||
					word.charAt(i)=='X' ||
					word.charAt(i)=='Y' )
						integer += "9";		
		}
		return integer;
	}
	public static void main(String [] args) throws IOException{
		Scanner sc = new Scanner(new File("namenum.in"));
		PrintWriter pw = new PrintWriter(new File("namenum.out"));
		Scanner dict = new Scanner(new File("dict.txt"));
		permutations = new ArrayList<String>();
		long id = sc.nextLong();
		boolean found = false;

		while(dict.hasNext()){
			String str = dict.next();
			if(Long.toString(id).length()==str.length() &&
				Long.toString(id).equals(toID(str))){
				found = true;
				pw.println(str);
			}
		}

		if(!found){
			pw.println("NONE");
		}
		pw.close();
	}
}
