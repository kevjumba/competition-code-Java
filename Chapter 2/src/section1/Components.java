package section1;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 ID: kevin.z3
 LANG: JAVA
 TASK: castle
 */

class castle {
	static int length, width;
	static int[][] maze;
	static int[][] visited;
	static int largestRoomSize = 0;
	static int now = 0;
	static ArrayList<Component> components;

	public static void main(String args[]) throws IOException {
		castle();
	}

	public static void castle() throws IOException {
		Scanner sc = new Scanner(new File("castle.in"));
		PrintWriter pw = new PrintWriter(new File("castle.out"));
		components = new ArrayList<Component>();
		length = sc.nextInt();
		width = sc.nextInt();
		maze = new int[width][length];
		visited = new int[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				maze[i][j] = sc.nextInt();
				visited[i][j] = -1;
			}
		}
		sc.close();
		int g = 0;
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				if (visited[i][j] != -1)
					continue;
				sizes.add(dfs(i, j, g));
				g++;
			}
		}
		for (int i = 0; i < sizes.size(); i++) {
			largestRoomSize = Math.max(largestRoomSize, sizes.get(i));
		}
		pw.println(sizes.size());
		pw.println(largestRoomSize);
		int bestSize = 0;
		int bestI = 0;
		int bestJ = 0;
		String direction = null;
		for(int j = 0; j < length;j++)		
		{
			for(int i = width-1; i >= 0;i--)
			{
				if(i+1 < width && visited[i][j] != visited[i+1][j]) //adjacent north south are not the same so not in same room
				{
					int combined = sizes.get(visited[i][j])+sizes.get(visited[i+1][j]); //add sizes together
					
					if(combined > bestSize)
					{
						bestSize = combined;
						bestI = i+1;
						bestJ = j;
						direction = "N";
					}
				}

			}
			for(int i = width-1; i >= 0;i--)
			{
				if(j+1 < length && visited[i][j] != visited[i][j+1]) //adjacent west east are not the same (not in same room)
				{
					int combined = sizes.get(visited[i][j])+sizes.get(visited[i][j+1]); //add the sizes together
					if(combined > bestSize)
					{
						bestSize = combined;
						bestI = i;
						bestJ = j;
						direction = "E";
					}
				}
			}
		}
		pw.println(bestSize);
		pw.println((bestI+1)+" "+(bestJ+1)+" "+direction);
		pw.close();

	}

	public static boolean isValid(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < length;
	}

	public static int dfs(int i, int j, int g) {
		if (visited[i][j] != -1)
			return 0;
		visited[i][j] = g;
		int count = 1;

		if (isValid(i - 1, j) && canN(i, j))
			count += dfs(i - 1, j, g);
		if (isValid(i + 1, j) && canS(i, j))
			count += dfs(i + 1, j, g);
		if (isValid(i, j + 1) && canE(i, j))
			count += dfs(i, j + 1, g);
		if (isValid(i, j - 1) && canW(i, j))
			count += dfs(i, j - 1, g);

		return count;
	}

	public static boolean canN(int i, int j) {
		if ((maze[i][j] & 2) == 0)
			return true;
		return false;
	}

	public static boolean canS(int i, int j) {
		if ((maze[i][j] & 8) == 0)
			return true;
		return false;
	}

	public static boolean canW(int i, int j) {
		if ((maze[i][j] & 1) == 0)
			return true;
		return false;
	}

	public static boolean canE(int i, int j) {
		if ((maze[i][j] & 4) == 0)
			return true;
		return false;
	}

	static class Component {
		int row;
		int col;
		int size;

		public Component(int r, int c, int size) {
			this.row = r;
			this.col = c;
			this.size = size;
		}
	}
}
