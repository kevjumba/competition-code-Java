/*
ID: kevin.z3
LANG: JAVA
TASK: sprime
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class sprime {
	static int N;

	public static void main(String[] args) throws IOException {
		superPrime();
	}

	public static void superPrime() throws IOException {
		Scanner sc = new Scanner(new File("sprime.in"));
		PrintWriter pw = new PrintWriter(new File("sprime.out"));
		number = new ArrayList<Integer>();
		N = sc.nextInt();
		sc.close();
		// for (double i = Math.pow(10, N - 1)+1; i < Math.pow(10, N); i+=2) {
		// int num = (int) i;
		// boolean isSPrime = true;
		// while (num > 0) {
		// if (!isPrime((int) num)) {
		// isSPrime = false;
		// break;
		// }
		// num /= 10;
		// }
		// if (isSPrime)
		// pw.println((int) i);
		// }
		generate(N, pw);
		pw.close();
	}

	static ArrayList<Integer> number;

	private static void generate(int length, PrintWriter pw) {
		if (length == 0) {
			int num = 0;
			for (int i = 0; i < number.size(); i++) {
				num += (int) (Math.pow(10, number.size() - i - 1))
						* number.get(i);
			}
			if(num == 7331) System.out.println("got it");
			boolean isSPrime = true;
			int temp = num;
			while (temp > 0) {
				if (!isPrime(temp)) {
					isSPrime = false;
					break;
				}
				temp /= 10;
			}
			if(isSPrime) pw.println(num);
			return;
		}
		if (length == N) {
			number.add(2);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
			number.add(3);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
			number.add(5);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
			number.add(7);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
		} else {
			number.add(1);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
			number.add(3);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
			number.add(7);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
			number.add(9);
			generate(length - 1, pw);
			number.remove(number.size() - 1);
		}

	}

	public static void numTriangle() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("numtri.in"));
		PrintWriter pw = new PrintWriter(new File("numtri.out"));
		int N = sc.nextInt();
		int size = (N + 1) * N / 2;
		int[] sum = new int[size];
		for (int i = 0; i < size; i++) {
			sum[i] = sc.nextInt();
		}
		sc.close();
		// pseudocode
		// at each sum in the list, check top two and see whether there are
		// larger ones available
		// traverse entire bottom row sums and find the largest
		for (int i = 1; i <= N; i++) { // from row 1 to row N
			for (int j = 1; j <= i; j++) { // index j in row i
				int index = (i - 1) * i / 2 + j - 1;
				// System.out.print("row: "+i+" original: "+sum[index]+" ");
				sum[index] = Math.max(sum[index], check(i, j, index, sum));
				// System.out.print("final: "+sum[index]+"\n");
			}
		}
		int max = 0;
		// System.out.println("row: "+N);
		// for (int i = 1; i <= N; i++) {
		//
		// System.out.println(sum[index]);
		// }
		for (int i = 1; i <= N; i++) {
			int index = (N - 1) * N / 2 + i - 1;
			max = Math.max(sum[index], max);
		}

		pw.println(max);
		pw.close();
	}

	private static int check(int row, int element, int index, int[] sum) {
		int topLeftIndex = index - row;
		int topRightIndex = index - row + 1;
		if (row == 1) {
			return sum[index];
		}
		if (topLeftIndex >= (row - 2) * (row - 1) / 2
				&& topRightIndex <= (row - 2) * (row - 1) / 2 + row - 2) {
			return Math.max(sum[topLeftIndex], sum[topRightIndex]) + sum[index];
		}
		if ((topLeftIndex >= (row - 2) * (row - 1) / 2)) {
			return sum[topLeftIndex] + sum[index];
		}
		if ((topRightIndex <= (row - 2) * (row - 1) / 2 + row - 2)) {
			return sum[topRightIndex] + sum[index];
		}
		return 0;
	}

	private static void palindrome() throws IOException {
		Scanner sc = new Scanner(new File("pprime.in"));
		PrintWriter pw = new PrintWriter(new File("pprime.out"));
		int low = sc.nextInt();
		int high = sc.nextInt();
		palindromes = new ArrayList<Integer>();
		sc.close();
		if (low % 2 == 0)
			low += 1;
		if (high - low > 10000000) {
			generate();
			for (int i = 0; i < palindromes.size(); i++) {
				if (palindromes.get(i) >= low && palindromes.get(i) <= high)
					if (isPrime(palindromes.get(i)))
						pw.println(palindromes.get(i));
			}
		} else {
			for (int num = low; num <= high; num += 2) {
				int temp = num;
				int reverse_num = 0;
				while (temp > 0) {
					int rem = temp % 10;
					temp = temp / 10;
					reverse_num = reverse_num * 10 + rem;
				}
				if (num == reverse_num)
					if (isPrime(num)) {
						pw.println(num);
					}
			}
		}
		pw.close();
	}

	private static boolean isPrime(int N) {
		if (N == 1)
			return false;
		for (int i = 2; i <= (int) Math.sqrt(N); i++) {
			if (N % i == 0)
				return false;
		}
		return true;
	}

	static ArrayList<Integer> palindromes;

	static void generate() {
		oneDigit();
		twoDigit();
		threeDigit();
		fourDigit();
		fiveDigit();
		sixDigit();
		sevenDigit();
		eightDigit();
	}

	static void oneDigit() {
		for (int i = 1; i <= 9; i += 2)
			palindromes.add(i);
	}

	static void twoDigit() {
		for (int i = 1; i <= 9; i += 2)
			palindromes.add(i * 10 + i);
	}

	static void threeDigit() {
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++)
				palindromes.add(100 * i + 10 * j + i);
		}
	}

	static void fourDigit() {
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++)
				palindromes.add(1000 * i + 100 * j + 10 * j + i);
		}
	}

	static void fiveDigit() {
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++)
					palindromes
							.add(10000 * i + 1000 * j + 100 * k + 10 * j + i);
			}
		}
	}

	static void sixDigit() {
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++)
					palindromes.add(100000 * i + 10000 * j + 1000 * k + 100 * k
							+ 10 * j + i);
			}
		}
	}

	static void sevenDigit() {
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					for (int l = 0; l <= 9; l++)
						palindromes.add(1000000 * i + 100000 * j + 10000 * k
								+ 1000 * l + 100 * k + 10 * j + i);
				}
			}
		}
	}

	static void eightDigit() {
		for (int i = 1; i <= 9; i += 2) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					for (int l = 0; l <= 9; l++)
						palindromes.add(10000000 * i + 1000000 * j + 100000 * k
								+ 10000 * l + 1000 * l + 100 * k + 10 * j + i);
				}
			}
		}
	}

}
