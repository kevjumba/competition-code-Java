/*
ID: kevin.z3
LANG: JAVA
TASK: wormhole
 */

import java.io.*;
import java.util.*;

class wormhole{
	// Global Variables
	static int maxDial = 0;
	static int[] finalHeight;
	static int[] partner = { 0, 3, 4, 1, 2 };
	static int[] next = { 0, 2, 0, 0, 3 };
	static int N = 4;

	public static void main(String[] args) throws IOException {
		 wormhole();

		System.out.println(hasLoop());
	}

	public static void wormhole() throws IOException {
		Scanner sc = new Scanner(new File("wormhole.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("wormhole.out"));
		N = sc.nextInt();
		Wormhole[] wormholes = new Wormhole[N + 1];
		partner = new int[N + 1];
		next = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			wormholes[i] = new Wormhole(sc.nextInt(), sc.nextInt());
		}
		// generate all next on rights
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (wormholes[i].getX() < wormholes[j].getX()
						&& wormholes[i].getY() == wormholes[j].getY())
					if (next[i] == 0
							|| wormholes[j].getX() - wormholes[i].getX() < wormholes[next[i]]
									.getX() - wormholes[i].getX())
						next[i] = j;

			}
		}
		display(next);
		sc.close();
		pw.println(solve());
		pw.close();

	}

	private static int solve() {
		// check for first unpaired wormhole
		// using 1-N pairing because 0 index will mess up the code
		int total = 0;
		int i;
		for (i = 1; i <= N; i++) {
			if (partner[i] == 0) // unpaired
				break;
		}
		if (i > N) {
			return (hasLoop()) ? 1 : 0;
		}
		// else find 2nd unpaired wormhole and pair them then recurse all of the
		// subsets with this subset
		for (int j = i + 1; j <= N; j++) {
			if (partner[j] == 0) {
				partner[j] = i;
				partner[i] = j;
				total += solve();
				partner[i] = partner[j] = 0;
			}
		}
		return total;
	}

	private static boolean hasLoop() {
		for (int i = 1; i <= N; i++) {
			int pos = i;
			for (int j = 1; j <= N; j++)
				pos = next[partner[pos]];
			if (pos != 0)
				return true; // has no more on the right
		}
		return false;
	}

	static class Wormhole {
		private int x, y;

		public Wormhole(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}
	}

	public static int skiDesign2() throws IOException {
		Scanner sc = new Scanner(new File("skidesign.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("skidesign.out"));

		int N = sc.nextInt();
		int[] initHeights = new int[N];
		for (int i = 0; i < N; i++) {
			sc.nextLine();
			initHeights[i] = sc.nextInt();
		}
		int mincost = Integer.MAX_VALUE;
		int intervalMin = 0;
		for (int i = 0; i <= 83; i++) {
			int cost = 0, x;
			for (int j = 0; j < N; j++) {
				if (initHeights[j] < i)
					x = i - initHeights[j];
				else if (initHeights[j] > i + 17)
					x = initHeights[j] - (i + 17);
				else
					x = 0;
				cost += Math.pow(x, 2);
			}
			// update the minimum cost
			if (mincost > cost) {
				intervalMin = i;
				mincost = cost;
			}

		}
		pw.println(mincost);
		sc.close();
		pw.close();
		return intervalMin;
	}

	public static void skiDesign() throws IOException {
		Scanner sc = new Scanner(new File("skidesign.in"));
		PrintWriter pw = new PrintWriter(new File("skidesign.out"));
		int N = sc.nextInt();
		int[] initHeight = new int[N];
		for (int i = 0; i < N; i++) {
			initHeight[i] = sc.nextInt();
		}
		Arrays.sort(initHeight);
		finalHeight = new int[N];
		System.arraycopy(initHeight, 0, finalHeight, 0, N);
		sc.close();
		boolean head = true; // true increment front, false increment back
		if (computeD(finalHeight.length - 1, finalHeight.length - 2) > computeD(
				1, 0)) {
			head = !head;
		}
		display(finalHeight);
		while (computeD(finalHeight.length - 1, 0) > 17) {
			head = changeHeight(head);
		}
		int cost = computeCost(initHeight, finalHeight);
		pw.println(cost);
		pw.close();
	}

	private static boolean changeHeight(boolean head) {
		if (head) {
			int start = finalHeight[0];
			int index = 0;
			for (int i = 0;; i++) {
				if (finalHeight[i] != start) {
					index = i - 1;
					break;
				} else
					finalHeight[i]++;
			}
			display(finalHeight);
			if (index < finalHeight.length
					&& finalHeight[index] == finalHeight[index + 1])
				head = !head;
		} else {
			int start = finalHeight[finalHeight.length - 1];
			int index = 0;
			for (int i = finalHeight.length - 1;; i--) {

				if (finalHeight[i] != start) {
					index = i + 1;
					break;
				} else
					finalHeight[i]--;
			}
			display(finalHeight);
			if (index > 0 && finalHeight[index] == finalHeight[index - 1])
				head = !head;
		}
		return head;
	}

	private static int computeD(int i1, int i2) {
		return finalHeight[i1] - finalHeight[i2];
	}

	private static void display(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
	}

	private static int computeCost(int[] initHeight, int[] finalHeight) {
		int cost = 0;
		for (int i = 0; i < initHeight.length; i++) {
			cost += Math.pow(Math.abs(initHeight[i] - finalHeight[i]), 2);
		}
		return cost;
	}

	public static void crypt1() throws IOException {
		Scanner sc = new Scanner(new File("crypt1.in"));
		PrintWriter pw = new PrintWriter(new File("crypt1.out"));
		int N = sc.nextInt();
		int[] digits = new int[N];
		for (int i = 0; i < N; i++) {
			digits[i] = sc.nextInt();
		}
		sc.close();
		int solutions = 0;
		for (int i = 100; i < 999; i++) {
			for (int j = 10; j < 99; j++) {
				if (!isValid(i, digits))
					continue;
				if (!isValid(j, digits))
					continue;
				int ones = j % 10;
				int tens = j / 10;
				if (i * ones > 1000)
					continue;
				if (i * tens > 1000)
					continue;
				if (!isValid(i * ones, digits))
					continue;
				if (!isValid(i * tens, digits))
					continue;
				if (!isValid(i * j, digits))
					continue;
				solutions++;
			}
		}
		pw.println(solutions);
		pw.close();

	}

	private static boolean isValid(int i, int[] digits) {
		while (i > 0) {
			int digit = i % 10;
			if (!isInDigits(digit, digits)) {
				return false;
			}
			i /= 10;
		}
		return true;
	}

	private static boolean isInDigits(int digit, int[] digits) {
		for (int i = 0; i < digits.length; i++) {
			if (digit == digits[i])
				return true;
		}
		return false;
	}

	public static void combo() throws IOException {
		Scanner sc = new Scanner(new File("combo.in"));
		PrintWriter pw = new PrintWriter(new File("combo.out"));
		maxDial = sc.nextInt();
		int n1, n2, n3;
		int m1, m2, m3;
		n1 = sc.nextInt();
		n2 = sc.nextInt();
		n3 = sc.nextInt();
		m1 = sc.nextInt();
		m2 = sc.nextInt();
		m3 = sc.nextInt();
		sc.close();
		int result = 0;
		for (int i = 1; i <= maxDial; i++) {
			for (int j = 1; j <= maxDial; j++) {
				for (int k = 1; k <= maxDial; k++) {
					if (closeEnough(i, j, k, n1, n2, n3)
							|| closeEnough(i, j, k, m1, m2, m3))
						result++;
				}
			}
		}
		pw.println(result);
		pw.close();
	}

	private static boolean closeEnough(int i, int j, int k, int l, int m, int n) {
		return close(i, l) && close(j, m) && close(k, n);
	}

	private static boolean close(int k, int n) {
		if (Math.abs(k - n) <= 2)
			return true;
		return (Math.abs(k - n) >= maxDial - 2);
	}

	public static void barnRepair() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("barn1.in"));
		PrintWriter pw = new PrintWriter(new File("barn1.out"));
		int numBoards = sc.nextInt();
		sc.nextInt();
		int numCows = sc.nextInt();
		int totalBlocked = numCows;
		int[] cowLoc = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			cowLoc[i] = sc.nextInt();
		}
		sc.close();
		Arrays.sort(cowLoc);
		totalBlocked = cowLoc[numCows - 1] - cowLoc[0] + 1;
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		for (int i = 1; i < numCows; i++) {
			if (cowLoc[i] - cowLoc[i - 1] > 1) {
				gaps.add(cowLoc[i] - cowLoc[i - 1] - 1);
			}
		}
		Collections.sort(gaps);
		int boards = 1;
		while (gaps.size() > 0 && boards < numBoards) {
			int g = gaps.remove(gaps.size() - 1);
			totalBlocked -= g;
			boards++;
		}
		pw.println(totalBlocked);
		pw.close();
	}

	public static void milkingCows(Scanner sc, PrintWriter pw) {
		int units = sc.nextInt();
		int farmers = sc.nextInt();
		Pair[] arr = new Pair[farmers];
		for (int i = 0; i < farmers; i++) {
			Pair pair = new Pair(sc.nextInt(), sc.nextInt());
			arr[i] = pair;
		}

		Arrays.sort(arr, new Comparator<Pair>() {
			public int compare(Pair o1, Pair o2) {
				return (int) (o1.getPrice() - o2.getPrice());
			}

		});
		int cost = 0;
		int i = 0;
		while (units > 0) {
			int price = arr[i].getPrice();
			int num = arr[i].getUnits();
			if (num < units) {
				cost += price * num;
				units -= num;
			} else {
				cost += price * units;
				units = 0;
			}
			i++;
		}
		pw.println(cost);
	}

	private static class Pair {

		private int price;
		private int units;

		public Pair(int price, int units) {
			this.price = price;
			this.units = units;
		}

		public int getPrice() {
			return price;
		}

		public int getUnits() {
			return units;
		}
	}

	public static void barnRepair2() throws FileNotFoundException {

		Scanner sc = new Scanner(new File("barn1.in"));
		PrintWriter pw = new PrintWriter(new File("barn1.out"));
		int numBoards = sc.nextInt();
		sc.nextInt();
		int numCows = sc.nextInt();
		int totalBlocked = numCows;
		int[] cowLoc = new int[numCows];
		for (int i = 0; i < numCows; i++) {
			cowLoc[i] = sc.nextInt();
		}
		Arrays.sort(cowLoc);
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		for (int i = 1; i < numCows; i++) {
			if (cowLoc[i] - cowLoc[i - 1] > 1) {
				gaps.add(cowLoc[i] - cowLoc[i - 1] - 1);
			}
		}
		sc.close();
		Collections.sort(gaps);
		int boards = gaps.size() + 1;
		while (boards > numBoards) {
			int g = gaps.remove(0);
			totalBlocked += g;
			boards--;
		}
		pw.println(totalBlocked);
		pw.close();
	}
}
