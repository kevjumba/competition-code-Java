/*
ID: kevin.z3
LANG: JAVA
TASK: milk2
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class milk2 {
	public static int max(int num1, int num2) {
		return (num1 > num2) ? num1 : num2;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("milk2.in"));
		PrintWriter pw = new PrintWriter(new File("milk2.out"));
		int maxMilking = 0;
		int maxNotMilking = 0;
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> end = new ArrayList<Integer>();
		int N = sc.nextInt();
		Segment[] intervals = new Segment[N];
		for (int i = 0; i < N; i++) {
			intervals[i] = new Segment(sc.nextInt(), sc.nextInt());
		}
		Arrays.sort(intervals, new Comparator<Segment>() {
			public int compare(Segment o1, Segment o2) {
				return o1.getStart() - o2.getStart();
			}

		});
		int cStart = 0;
		int cEnd = 0;
		for (int i = 0; i < N; i++) {
			cStart = intervals[i].getStart();
			cEnd = intervals[i].getEnd();
			for (int j = i + 1; i + j < N; j++) {
				int tStart = intervals[j].getStart();
				int tEnd = intervals[j].getEnd();
				if (tStart > cStart && tStart <= cEnd && tEnd > cEnd)
					cEnd = tEnd;
			}
			maxMilking = max(maxMilking, cEnd - cStart);
		}
		int nMS = 0;
		int nME = 0;
		cStart = intervals[0].getStart();
		cEnd = intervals[0].getEnd();
		for (int j = 1; j < N; j++) {
			int tStart = intervals[j].getStart();
			int tEnd = intervals[j].getEnd();
			if (tStart > cStart && tStart <= cEnd && tEnd > cEnd)
				cEnd = tEnd;
			if (tStart > cEnd) {
				nMS = cEnd;
				nME = tStart;
				maxNotMilking = max(maxNotMilking, nME - nMS);
				cStart = tStart;
				cEnd = tEnd;
			}

		}
		pw.print(maxMilking + " " + maxNotMilking + "\n");
		pw.close();
	}

	private static void display(Segment[] intervals) {
		// TODO Auto-generated method stub
		for(int i=0;i<intervals.length;i++){
			System.out.println(intervals[i].getStart()+" "+intervals[i].getEnd());
		}
	}

	static class Segment {
		private int start;
		private int end;

		public Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}
	}
}
