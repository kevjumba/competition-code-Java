import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class SickCows {
	/*
	 * For record i of a person drinking milk, we store data as follows:
	 * personDrink[i]: the person who drank the milk
	 * milkDrink[i]: the type of milk that the person drank
	 * timeDrink[i]: the time the person drank the milk
	 */
	static int[] personDrink;
	static int[] milkDrink;
	static int[] timeDrink;
	
	HashMap<Integer, HashMap<Integer, Array[]>> sick=new HashMap<Integer, HashMap<Integer, Array[]>>();
	static int[] data;
	
	/*
	 * For record i of a person being sick, we store data as follows:
	 * personSick[i]: the person who got sick
	 * timeSick[i]: the time that person got sick
	 */
	static int[] personSick;
	static int[] timeSick;
	
	public static void main(String[] args) throws IOException {
		// initialize file I/O
		BufferedReader br = new BufferedReader(new FileReader("badmilk.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));

		// read in the first line, store n, m, d, and s
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		// initialize the arrays about drinking milk
		personDrink = new int[d];
		milkDrink = new int[d];
		timeDrink = new int[d];
		for(int i = 0; i < d; i++) {
			// read in record i of someone drinking milk
			st = new StringTokenizer(br.readLine());
			personDrink[i] = Integer.parseInt(st.nextToken());
			milkDrink[i] = Integer.parseInt(st.nextToken());
			timeDrink[i] = Integer.parseInt(st.nextToken());
		}
		
		// initialize the arrays about people getting sick
		personSick = new int[s];
		timeSick = new int[s];
		for(int i = 0; i < s; i++) {
			// read in record i of someone drinking
			st = new StringTokenizer(br.readLine());
			personSick[i] = Integer.parseInt(st.nextToken());
			timeSick[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxCanGetSick = 0;
		
		for(int i = 1; i <= m; i++) {
			// check if milk type i can be bad
			if(milkTypeCanBeBad(i)) {
				// the milk type can be bad, now count how many people drank that type of milk
				int numDrank = numPeopleDrink(i);
				if(numDrank > maxCanGetSick) {
					// update the maximum number of people that could be sick
					maxCanGetSick = numDrank;
				}
			}
		}
		
		// print the answer!
		pw.println(maxCanGetSick);
		
		// close output stream
		pw.close();
	}
	
	/*
	 * Given a type of milk, this method returns true if and only if
	 * the given milk type could be bad. This is true if and only if
	 * for each person who got sick, this person consumed the given
	 * type of milk before that person got sick.
	 */
	private static boolean milkTypeCanBeBad(int milkType) {
		// loop over all records of people being sick
		for(int i = 0; i < personSick.length; i++) {
			// check for record i of sickness if that person drank that milk before they got sick
			if(!personDrankMilkBefore(personSick[i], milkType, timeSick[i])) {
				// that person did not, this milk type cannot be bad then
				return false;
			}
		}
		// all the records checked out, so return true
		return true;
	}
	
	/*
	 * Given a person, a type of milk, and a time, this method returns
	 * true if and only if the given person drank the given type of milk
	 * before the given time.
	 */
	private static boolean personDrankMilkBefore(int person, int milkType, int time) {
		// loop over all records of drinking
		for(int i = 0; i < personDrink.length; i++) {
			// check if record i of drinking if that person did drink that type of milk before the given time
			if(personDrink[i] == person && milkDrink[i] == milkType && timeDrink[i] < time) {
				// all conditions are satisfised, return true
				return true;
			}
		}
		// none of the records satisfied the conditions, return false
		return false;
	}

	private static int numPeopleDrink(int milkType) {
		// didDrink[i] will be true if and only if person i did drink the given type of milk
		boolean[] didDrink = new boolean[51];
		// loop over all records of drinking
		for(int i = 0; i < personDrink.length; i++) {
			// check if this record corresponds to the given type of milk
			if(milkDrink[i] == milkType) {
				// it does, mark that this person did drink that milk
				didDrink[personDrink[i]] = true;
			}
		}
		// now that we've looped over all records, just count the number of people
		// who drank that milk
		int numPeopleDrink = 0;
		for(int i = 1; i < didDrink.length; i++) {
			if(didDrink[i]) {
				numPeopleDrink++;
			}
		}
		return numPeopleDrink;
	}
	
}