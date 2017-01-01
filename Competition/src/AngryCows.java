import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class AngryCows {
static int position[]=new int[100];
static int N;
static int dist(int pos1, int pos2){

	if(pos1>N||pos1<0||pos2>N||pos2<0) return -1;
	return Math.abs(position[pos1]-position[pos2]);
}

static int blast(int nexttime, int currpos, int currstreak){
	int radius=nexttime;
	int distForward=dist(currpos, currpos+1);
	int distBackward=dist(currpos, currpos-1);
	if(currpos>N||currpos<1||(distForward>radius&&distBackward>radius)){
		return currstreak;
	}
	else{
		for(int i=1;i<N;i++){
			if(dist(currpos, currpos+i)==-1||dist(currpos, currpos+i)>radius){
				break;
			}
			if(dist(currpos, currpos+i)<=radius){
//				cout<<"distance: "<<dist(currpos, currpos+i)<<endl;
//				cout<<"current position: "<<currpos<<endl;
//				cout<<"after blast: "<<currpos+i<<endl;
				blast(radius+1, currpos+i, currstreak+1);
			}
		}
		for(int i=1;i<N;i++){
					if(dist(currpos, currpos-i)==-1||dist(currpos, currpos-i)>radius){
						break;
					}
					if(dist(currpos, currpos-i)<=radius){
						blast(radius+1, currpos-i, currstreak+1);
					}
		}
	}
	return currstreak;
}
		
public static void main(String[] args) throws IOException {
			// initialize file I/O
			BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));

			// read in the first line, store n, m, d, and s
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int maxStreak=0;
			for(int i=0;i<N;i++){
				position[i]=Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<N;i++){
				int streak=blast(1, i, 1);
				maxStreak=Math.max(streak, maxStreak);

			}
			pw.println(maxStreak);
}
}
