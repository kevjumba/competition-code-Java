package section1;
/*
ID: kevin.z3
LANG: JAVA
TASK: hamming
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class hamming {
	static int N;
	static int numWrong;

	// holstein problem globals
	static int[] required;
	static int[] elements;
	static int[][] feed;
	static int numVit, G;
	static int minFeed = Integer.MAX_VALUE;
	static ArrayList<Integer> answer;

	public static void main(String args[]) throws IOException {
		hamming();
	}

	public static void holstein() throws IOException {
		Scanner sc = new Scanner(new File("holstein.in"));
		PrintWriter pw = new PrintWriter(new File("holstein.out"));
		numVit = sc.nextInt();
		required = new int[numVit];
		for (int i = 0; i < numVit; i++) {
			required[i] = sc.nextInt();
		}
		G = sc.nextInt();
		elements = new int[G];
		feed = new int[G][numVit];
		for (int i = 0; i < G; i++) {
			for (int j = 0; j < numVit; j++) {
				feed[i][j] = sc.nextInt();
			}
		}
		sc.close();
		solve(0);
		Collections.sort(answer);
		pw.print(minFeed);
		for (int i = 0; i < answer.size(); i++) {
			pw.print(" " + answer.get(i));
		}
		pw.print("\n");
		pw.close();
	}

	public static void hamming() throws IOException {
		Scanner sc = new Scanner(new File("hamming.in"));
		PrintWriter pw = new PrintWriter(new File("hamming.out"));
		int N = sc.nextInt();
		int B = sc.nextInt();
		int D = sc.nextInt();
		sc.close();
		ArrayList<Integer> codes = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < (int) Math.pow(2, B); i++) {
			if (codes.size() == 0) {
				codes.add(i);
				count++;
			} else {
				boolean good = true;
				for (int j = 0; j < codes.size(); j++) {
					int codeword = codes.get(j);
					if (hammingDistance(i, codeword) < D) {
						good = false;
					}
				}
				if (good) {
					codes.add(i);
					count++;
				}
			}
			if (count >= N)
				break;
		}
		pw.print(codes.get(0));
		for (int i = 1; i < N; i++) {
			if (i % 10 == 0)
				pw.print("\n");
			if (i % 10 == 0) {
				pw.print(codes.get(i));
			} else {
				pw.print(" " + codes.get(i));
			}
		}
		pw.print("\n");
		pw.close();
	}

	public static int hammingDistance(int str1, int str2) {
		int d = 0;
		String A = Integer.toBinaryString(str1), B = Integer
				.toBinaryString(str2);
		if (A.length() > B.length()) {
			while (A.length() > B.length()) {
				B = "0" + B;
			}
		} else if (A.length() < B.length()) {
			while (A.length() < B.length()) {
				A = "0" + A;
			}
		}
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != B.charAt(i))
				d++;
		}
		return d;
	}

	private static void solve(int i) {
		// TODO Auto-generated method stub
		if (i >= G) {
			int[] arr = new int[numVit];
			int numFeeds = 0;
			ArrayList<Integer> vit = new ArrayList<Integer>();
			for (int j = 0; j < G; j++) {
				if (elements[j] == 1) {
					numFeeds++;
					vit.add(j + 1);
					for (int k = 0; k < numVit; k++) {
						arr[k] += feed[j][k];
					}
				}
			}
			if (isValid(arr)) {
				if (minFeed > numFeeds) {
					minFeed = numFeeds;
					answer = vit;
					// display(vit);
				}
				if (minFeed == numFeeds) {
					for (int j = 0; j < vit.size(); j++) {
						if (vit.get(j) > answer.get(j))
							return;
					}
					answer = vit;
				}
			}
			return;
		}
		elements[i] = 0;
		solve(i + 1);
		elements[i] = 1;
		solve(i + 1);
	}

	private static boolean isValid(int[] arr) {
		for (int i = 0; i < numVit; i++) {
			if (arr[i] < required[i])
				return false;
		}
		return true;
	}

	public static void display(ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i) + " ");

		}
		System.out.print("\n");
	}

	public static void displayArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");

		}
		System.out.print("\n");
	}

	public static void sort3() throws IOException {
		Scanner sc = new Scanner(new File("sort3.in"));
		PrintWriter pw = new PrintWriter(new File("sort3.out"));
		N = sc.nextInt();
		int[] arr = new int[N];
		int[] sorted = new int[N];
		int div1 = N, div2 = N;
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			sorted[i] = arr[i];
		}
		sc.close();
		Arrays.sort(sorted);
		int curr = 1;
		for (int i = 0; i < N; i++) {
			if (curr == 1 && sorted[i] != curr) {
				div1 = i;
				curr = 2;
			}

			if (curr == 2 && sorted[i] != curr) {
				div2 = i;
				curr = 3;
			}
			if (sorted[i] != arr[i])
				numWrong++;
		}
		System.out.println(numWrong + " " + div1 + " " + div2);
		int s1 = swap1(arr, sorted, div1, div2), s2 = swap2(arr, sorted, div1,
				div2), s3 = swap3(arr, sorted, div1, div2);
		System.out.println(s1 + " " + s2 + " " + s3);
		int sum = s1 + s2 + s3 + countRest(numWrong);
		System.out.println(numWrong);
		display(arr);
		System.out.println();
		display(sorted);
		pw.println(sum);
		pw.close();
	}

	private static void display(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

	}

	private static int swap3(int[] arr, int[] sorted, int div1, int div2) {
		int sum = 0;
		for (int i = 0; i < div1; i++) {
			if (arr[i] == 3) {
				for (int j = div2; j < N; j++) {
					if (arr[j] == 1) {
						sum++;
						swap(arr, i, j);
						numWrong -= 2;
						break;
					}
				}
			}
		}
		return sum;
	}

	private static int countRest(int numWrong) {
		return (numWrong / 3) * 2;
	}

	private static int swap2(int[] arr, int[] sorted, int div1, int div2) {
		// div1 index of first 2
		if (div2 == N)
			return 0;
		int sum = 0;
		for (int i = div1; i < div2; i++) {
			if (arr[i] == 3) {
				for (int j = div2; j < N; j++) {
					if (arr[j] == 2) {
						sum++;
						swap(arr, i, j);
						numWrong -= 2;
						break;
					}
				}
			}
		}
		return sum;
	}

	private static int swap1(int[] arr, int[] sorted, int div1, int div2) {
		// div2 = first 3
		if (div1 == N)
			return 0;
		int sum = 0;
		for (int i = 0; i < div1; i++) {
			if (arr[i] == 2) {
				for (int j = div1; j < div2; j++) {
					if (arr[j] == 1) {
						sum++;
						swap(arr, i, j);
						numWrong -= 2;
						break;
					}
				}
			}
		}
		return sum;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void fractions() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("frac1.in"));
		PrintWriter pw = new PrintWriter(new File("frac1.out"));
		N = sc.nextInt();
		ArrayList<Fraction> fractions = new ArrayList<Fraction>();
		for (int i = N; i > 0; i--) { // denominator
			for (int j = 0; j < i; j++) {
				fractions.add(new Fraction(j, i));
			}
		}
		sc.close();
		Collections.sort(fractions, new Comparator<Fraction>() {

			@Override
			public int compare(Fraction o1, Fraction o2) {
				// TODO Auto-generated method stub
				if (o1.getFraction() > o2.getFraction())
					return 1;
				if (o1.getFraction() < o2.getFraction())
					return -1;
				return 0;
			}

		});

		double curr = -1;
		pw.println("0/1");
		for (int i = 0; i < fractions.size(); i++) {
			if (fractions.get(i).getFraction() == 0)
				continue;
			if (fractions.get(i).getFraction() != curr) {
				pw.println(fractions.get(i).toString());
				curr = fractions.get(i).getFraction();
			}
		}
		pw.println("1/1");
		pw.close();
	}

	static class Fraction {
		private int numer;
		private int denum;

		public Fraction(int num, int denum) {
			this.numer = num;
			this.denum = denum;
		}

		public double getFraction() {
			return (double) numer / denum;
		}

		public static long gcm(long a, long b) {
			return b == 0 ? a : gcm(b, a % b); // Not bad for one line of code
												// :)
		}

		public static String asFraction(long a, long b) {
			long gcm = gcm(a, b);
			return (a / gcm) + "/" + (b / gcm);
		}

		public String toString() {
			return asFraction(numer, denum);
		}
	}
}
