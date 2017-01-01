/*
ID: kevin.z3
LANG: JAVA
TASK: friday
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class friday {
	public static boolean isLeapYear(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("friday.in"));
		PrintWriter pw = new PrintWriter(new File("friday.out"));
		int[] week = new int[7];
		int[] lastWeekDay = { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] monthLength = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 };
		int years = sc.nextInt();
		for (int i = 0; i < years; i++) {
			for (int j = 1; j <= 12; j++) {
				int lastMonth = j - 1; // 0 is December
				int change = 0;
				if (isLeapYear(1900 + i) && lastMonth == 2)
					change = 29 % 7 ;
				else
					change = monthLength[lastMonth] % 7;
				int weekDay = (lastWeekDay[lastMonth] + change) % 7;
				week[weekDay]++;
				lastWeekDay[j%12] = weekDay;
			}
		}
		pw.print(week[6] + " ");
		for(int i=0;i<5;i++){
			pw.print(week[i] + " ");
		}
		pw.print(week[5]+"\n");
		pw.close();
	}
}
