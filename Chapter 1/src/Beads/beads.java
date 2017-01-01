package Beads;
/*
ID: kevin.z3
LANG: JAVA
TASK: beads
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class beads {
	public static int max(int num1, int num2) {
		return (num1 > num2) ? num1 : num2;
	}
	public static int numBeads;
	public static int solve(ArrayList<Segment> segments) {
		int maxLength = 0;
		int curr = 0;
		for (int i = 0; i < segments.size(); i++) {
			char currColor = segments.get(i).getColor();

			curr += segments.get(i).getLength();
			if (currColor == 'w') {
				currColor = segments.get(i + 1).getColor();
				curr+=segments.get(i+1).getLength();
				i++;
			}
			for (int j = i + 1; j < segments.size(); j++) {
				char c = segments.get(j).getColor();
				if (c == currColor)
					break;
				curr += segments.get(j).getLength();
			}
			maxLength = max(curr, maxLength);
			curr = 0;
		}
		return (maxLength>numBeads)?numBeads:maxLength;
	}

	public static ArrayList<Segment> process(ArrayList<Segment> segments) {
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).getColor() == 'w') {
				if (i == 0) {
					segments.get(1).increaseLength(segments.get(0).getLength());
					segments.remove(0);
					i--;
				} else if (i == segments.size() - 1) {
					segments.get(segments.size() - 2).increaseLength(
							segments.get(segments.size() - 1).getLength());
					segments.remove(segments.size() - 1);
					i--;
				} else if (segments.get(i - 1).getColor() == segments
						.get(i + 1).getColor()) {
					segments.get(i - 1).increaseLength(
							segments.get(i).getLength()
									+ segments.get(i + 1).getLength());
					segments.remove(i);
					segments.remove(i);
					i--;
				}
			}
		}
		return segments;
	}

	public static void display(ArrayList<Segment> segments) {
		for (int i = 0; i < segments.size(); i++) {
			for (int j = 0; j < segments.get(i).getLength(); j++) {
				
				System.out.print(segments.get(i).getColor());
			}
		}

		System.out.println("\n");
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("beads.in"));
		PrintWriter pw = new PrintWriter(new File("beads.out"));
		ArrayList<Segment> segments = new ArrayList<Segment>();
		numBeads = sc.nextInt();
		String str = sc.next();
		str = str + str;
		char c = str.charAt(0);
		segments.add(new Segment(c));
		for (int i = 1; i < str.length(); i++) {
			char nextC = str.charAt(i);
			if (nextC != c) {
				segments.add(new Segment(nextC));
				c = nextC;
			} else
				segments.get(segments.size() - 1).increaseLength(1);
		}
		if(segments.size()==1){
			pw.println(numBeads);
			pw.close();
			return;
		}
		segments = process(segments);
		pw.println(solve(segments));
		display(segments);
//		for (Segment s : segments) {
//			System.out.println(s.getColor() + " " + s.getLength());
//		}
		pw.close();
	}
}

class Segment {
	private char beadColor; // 0=white, 1=red, 2=blue
	private int length;

	public Segment(char color) {
		this.beadColor = color;
		length = 1;
	}

	public void increaseLength(int change) {
		length += change;
	}

	public int getLength() {
		return length;
	}

	public char getColor() {
		return beadColor;
	}

	public void setColor(char color) {
		beadColor = color;
	}
}