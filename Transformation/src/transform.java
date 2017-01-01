/*
ID: kevin.z3
LANG: JAVA
TASK: transform
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class transform {
	public static boolean compare(char[][] m1, char[][] m2) {
		if (m1.length != m2.length || m1[0].length != m2[0].length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[0].length; j++) {
				if (m1[i][j] != m2[i][j])
					return false;
			}
		}
		return true;
	}

	public static void swap(char[][] m1, int i1, int j1, int i2, int j2) {
		if (i1 >= m1.length || i1 < 0 || j1 >= m1[0].length || j1 < 0
				|| i2 >= m1.length || i2 < 0 || j2 >= m1[0].length || j2 < 0) {
			System.out.println("Error Swap Out of Bounds");
			return;
		}

		char temp = m1[i1][j1];
		m1[i1][j1] = m1[i2][j2];
		m1[i2][j2] = temp;

	}
	
	public static char [][] rotate(char [][] matrix){
		char [][] result = new char [matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; ++i) {
	        for (int j = 0; j < matrix[0].length; ++j) {
	        	result[i][j]= matrix[matrix.length-j-1][i];
	        }
	    }
		return result;
	}
	
	public static void display(char [][] matrix){
		for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	        	System.out.print(matrix[i][j]);
	        }
	        System.out.print("\n");
	    }
	}

	public static char[][] mirror(int size, char[][] in) {
	    char[][] out = new char[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            out[i][size-j-1] = in[i][j];
	        }
	    }
	    return out;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("transform.in"));
		PrintWriter pw = new PrintWriter(new File("transform.out"));
		int size = sc.nextInt();
		char [][]matrix = new char[size][size];
		char [][]resultMatrix = new char[size][size];
		for (int i = 0; i < size; i++) {
			String str = sc.next();
	        for (int j = 0; j < size; j++) {
	        	matrix[i][j]=str.charAt(j);
	        }
	    }
		for (int i = 0; i < size; i++) {
			String str = sc.next();
	        for (int j = 0; j < size; j++) {
	        	resultMatrix[i][j]=str.charAt(j);
	        }
	    }
		char [][] tempMatrix = new char [size][size];
		
		tempMatrix = rotate(matrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("1");
			pw.close();
			return;
		}
		tempMatrix = rotate(tempMatrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("2");
			pw.close();
			return;
		}
		tempMatrix = rotate(tempMatrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("3");
			pw.close();
			return;
		}
		tempMatrix = mirror(size, matrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("4");
			pw.close();
			return;
		}
		tempMatrix = rotate(tempMatrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("5");
			pw.close();
			return;
		}
		tempMatrix = rotate(tempMatrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("5");
			pw.close();
			return;
		}
		tempMatrix = rotate(tempMatrix);
		if(compare(tempMatrix, resultMatrix)){
			pw.println("5");
			pw.close();
			return;
		}
		if(compare(matrix, resultMatrix)){
			pw.println("6");
			pw.close();
			return;
		}
		pw.println("7");
		pw.close();
	}
}
