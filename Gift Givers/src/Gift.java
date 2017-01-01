/*
ID: kevin.z3
LANG: JAVA
TASK: gift1
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class gift1 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("input.txt"));
		PrintWriter pw = new PrintWriter(new File("output.txt"));

		int numPersons = sc.nextInt();
		String person;
		HashMap<String, Integer> received = new HashMap<String, Integer>();
		ArrayList<String> people = new ArrayList<String>();
		for (int i = 0; i < numPersons; i++) {
			person = sc.next();
			people.add(person);
			received.put(person, 0);
		}
		HashMap<String, Integer> given = new HashMap<String, Integer>();
		for (int i = 0; i < numPersons; i++) {
			person = sc.next();
			int amount = sc.nextInt();
			int recipients = sc.nextInt();
			int gift = 0;
			given.put(person, amount);
			if (recipients > 0) {
				gift = amount / recipients; // integer division
				received.put(person, received.get(person) + amount % recipients);
			}
			for (int j = 0; j < recipients; j++) {
				String recipient = sc.next();
				received.put(recipient, received.get(recipient) + gift);
			}
		}
		for (int i = 0; i < numPersons; i++) {
			person = people.get(i);
			pw.println(person + " " + (received.get(person) - given.get(person)));
		}
		pw.close();
	}
}
