/*
ID: kevin.z3
LANG: JAVA
TASK: milk3
 */

import java.io.*;
import java.util.*;

class milk3 {
	// global variables
	static int N = 5, M;
	static int A, B, C;
	static boolean[][] visited = new boolean[21][21];
	static boolean[] amount = new boolean[21];
	static int[] bisquares;
	static ArrayList<Integer> result;

	public static void main(String[] args) throws IOException {
		motherMilk();
		// System.out.println(hasProgression(1, 4);
	}

	public static void arithmeticProgression() throws IOException {
		Scanner sc = new Scanner(new File("ariprog.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("ariprog.out"));
		N = sc.nextInt();
		M = sc.nextInt();
		bisquares = new int[125001];
		for (int p = 0; p <= M; p++) {
			for (int q = p; q <= M; q++) {
				bisquares[p * p + q * q] = 1;
			}
		}
		sc.close();
		ArrayList<Progression> progressions = new ArrayList<Progression>();
		for (int step = 1; step < M * M * 2; step++) {
			for (int i = 0; i + step * (N - 1) < bisquares.length; i++) {
				if (bisquares[i] == 0) {
					continue;
				}
				if (i + step > M * M * 2)
					continue;
				if (hasProgression(i, step)) {
					progressions.add(new Progression(i, step));
				}
			}
		}
		if (progressions.size() == 0) {
			pw.println("NONE");
		} else {
			for (int i = 0; i < progressions.size(); i++) {

				pw.println(progressions.get(i).getStart() + " "
						+ progressions.get(i).getStep());
			}
		}
		pw.close();
	}

	private static boolean hasProgression(int start, int step) {
		int num = 0;
		for (int i = start; i < bisquares.length; i++) {
			if (bisquares[i] == 1) {
				num++;
				i += step - 1;
				if (num >= N)
					return true;
			} else {
				return false;
			}
		}
		return false;
		// while (true) {
		// a += step;
		// for (; i < bisquares.size(); i++) {
		// if (bisquares.get(i) == a) {
		// num++;
		// break;
		// }
		// }
		// if (i >= bisquares.size() || a>bisquares.get(bisquares.size()-1))
		// break;
		// if (num >= N)
		// return true;
		// }
	}

	static class Progression {
		private int start;
		private int step;

		public Progression(int start, int step) {
			this.start = start;
			this.step = step;
		}

		public int getStart() {
			return this.start;
		}

		public int getStep() {
			return this.step;
		}
	}

	public static void motherMilk() throws IOException {
		Scanner sc = new Scanner(new File("milk3.in"));
		PrintWriter pw = new PrintWriter(new File("milk3.out"));
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		sc.close();
		search(0, 0, C);
		for (int i = 0; i < C; i++)
			if (amount[i])
				pw.print(i + " ");
		pw.println(C);
		pw.close();
	}

	private static void search(int x, int y, int z) {
		if (visited[x][y])
			return;
		visited[x][y] = true;
		if (x == 0)
			amount[z] = true;
		if (x > 0 && y < B)
			search(Math.max(0, x + y - B), Math.min(B, x + y), z);
		if (x > 0 && z < C)
			search(Math.max(0, x + z - C), y, Math.min(C, x + z));
		if (y > 0 && x < A)
			search(Math.min(A, y + x), Math.max(0, y + x - A), z);
		if (y > 0 && z < C)
			search(x, Math.max(0, y + z - C), Math.min(C, y + z));
		if (z > 0 && x < A)
			search(Math.min(A, z + x), y, Math.max(0, z + x - A));
		if (z > 0 && y < B)
			search(x, Math.min(B, z + y), Math.max(0, z + y - B));
	}

	private static void solve(int a, int b, int c, int n) {
		if (a == 0) {
			result.add(c);
			return;
		}
		if (n > 10) {
			return;
		}
		solve(a + b, 0, c, n + 1);
		solve(a + c, b, 0, n + 1);
		solve(0, a + b, c, n + 1);
		solve(a, b + c, 0, n + 1);
		solve(a, 0, c + b, n + 1);
		solve(0, b, c + a, n + 1);
	}

	private static void display(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
	}
}
