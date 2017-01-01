/*
 ID: kevin.z3
 LANG: JAVA
 TASK: subset
 */

package section2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class subset {
	static int[] elements;
	static int count, N;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("subset.in"));
		PrintWriter pw = new PrintWriter(new File("subset.out"));
		N = sc.nextInt();
		sc.close();
		elements = new int[N];
		count = 0;
		generate(0);
		pw.println(count/2);
		pw.close();
	}

	private static void generate(int i) {
		if (i >= N) {
			int sum1 = 0;
			int sum2 = 0;
			for (int j = 0; j < N; j++) {
				if(elements[j]==1) sum1+=(j+1);
				if(elements[j]==0) sum2+=(j+1);
			}
			if(sum1==sum2) count++;
			return;
		}
		elements[i] = 0;
		generate(i + 1);
		elements[i] = 1;
		generate(i + 1);

	}
}
