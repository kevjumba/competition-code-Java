package section2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class preface {
	static HashMap<String, Integer> map2;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("subset.in"));
		PrintWriter pw = new PrintWriter(new File("subset.out"));
		int N = sc.nextInt();
		for(int i = 1;i<N;i++){
			toRoman(i);
		}
		pw.close();
	}
	
	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        map2.put("M", 0);
        map2.put("CM",0 );
        map2.put("D", 0);
        map2.put("CD", 0);
        map2.put("C", 0);
        map2.put("XC", 0);
        map2.put("L", 0);
        map2.put("XL", 0);
        map2.put("X", 0);
        map2.put("IX", 0);
        map2.put("V", 0);
        map2.put("IV", 0);
        map2.put("I", 0);

    }

    public final static void toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            map2.put(map.get(number), map2.get(map.get(number))+1);
        }
        map2.put(map.get(l), map2.get(map.get(l))+1);
        toRoman(number-l);
    }
}
