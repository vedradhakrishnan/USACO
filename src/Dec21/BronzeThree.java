import java.io.*;
import java.util.*;

public class BronzeThree {
	
	//name: Walking Home 
	//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1157
	//solves 7/10 cases - changed after comp.

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int trial = 0; trial < t; trial++) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			char[][] bales = new char[n][n];
			List<Segment>[] paths = new List[2*n-1]; //all paths have 2n -1 steps
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				bales[i] = st.nextToken().toCharArray();
			}
			
			//brute force bfs - 7/10 cases
			paths[0] = new ArrayList<Segment>(); //origin - top left corner
			paths[0].add(new Segment());
			for (int i = 1; i < paths.length; i++) {
				paths[i] = new ArrayList<Segment>();
				for (Segment root : paths[i-1]) { //adds all possible paths to the next row in array
					//down branch
					if (root.row + 1 < n && bales[root.row + 1][root.col] == '.') { //checks within bounds + no haybale
						if (root.dir == 'R') {
							if (root.turns < k) { //if able to turn again; max turns not reached
								paths[i].add(new Segment(root.row + 1, root.col, root.turns + 1, 'D'));
							}
						} else {
							paths[i].add(new Segment(root.row + 1, root.col, root.turns, 'D'));
						}
					}
					
					//right branch - similar logic
					if (root.col + 1 < n && bales[root.row][root.col + 1] == '.') {
						if (root.dir == 'D') {
							if (root.turns < k) {
								paths[i].add(new Segment(root.row, root.col + 1, root.turns + 1, 'R'));
							}
						} else {
							paths[i].add(new Segment(root.row, root.col + 1, root.turns, 'R'));
						}
					}

				}
			}

			System.out.println(paths[2*(n - 1)].size()); //number of possibilities that have reached end
		}
		
	}
	
	public static class Segment {
		public int row;
		public int col;
		public int turns;
		public char dir;
		
		public Segment(int r, int c, int t, char d) {
			row = r; col = c;
			turns = t; dir = d;
		}
		
		public Segment() {
			this(0, 0, 0, 'N');
		}
		
		public String toString() {
			return "" + dir + "(" + row + "," + col + ")" + turns;
		}
	}
}
 