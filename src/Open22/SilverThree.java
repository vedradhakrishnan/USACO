//name: COW Operations
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1232
//solves all cases

import java.util.*;
import java.io.*;

public class SilverThree {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>(); //map of each character to an int
		map.put('C', 0); map.put('O', 1); map.put('W', 2);
		
		st = new StringTokenizer(in.readLine());
		String main = st.nextToken();
		int n = main.length();
		
		boolean[][] parity = new boolean[3][n + 1]; //array for all 3 characters storing parity of the frequency of each character at each substring
		parity[map.get(main.charAt(0))][0] = true;
		for (int i = 1; i < n + 1; i++) {
			parity[0][i] = parity[0][i - 1];
			parity[1][i] = parity[1][i - 1];
			parity[2][i] = parity[2][i - 1];	
			parity[map.get(main.charAt(i - 1))][i] = !parity[map.get(main.charAt(i - 1))][i];
		}

		st = new StringTokenizer(in.readLine());
		int q = Integer.parseInt(st.nextToken());
		String out = "";
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());
			int begin = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken());
			
			boolean c = parity[0][end] == parity[0][begin];
			boolean o = parity[1][end] == parity[1][begin];
			boolean w = parity[2][end] == parity[2][begin];
			
			System.out.print((c != o && o == w) ? "Y" : "N"); //can be reduced if parities are the same for W and O, anf different from C
		}
	}
}
