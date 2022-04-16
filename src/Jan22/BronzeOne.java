//name: Herdle 
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1179
//solves all cases

import java.io.*;
import java.util.*;

public class BronzeOne {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		char[] corr = new char[9];
		char[] guess = new char[9];
		
		for (int r = 0; r < 3; r++) { //reads in answer
			st = new StringTokenizer(in.readLine());
			String row = st.nextToken();
			for (int c = 0; c < 3; c++) {
				corr[3*r + c] = row.charAt(c);
			}
		}
		
		for (int r = 0; r < 3; r++) { //reads in guess
			st = new StringTokenizer(in.readLine());
			String row = st.nextToken();
			for (int c = 0; c < 3; c++) {
				guess[3*r + c] = row.charAt(c);
			}
		}
		
		int g = 0;
		int y = 0;
		for (int i = 0; i < 9; i++) {
			if (corr[i] == guess[i]) { //counts greens and replaces characters for non-double counting
				g++;
				corr[i] = '+';
				guess[i] = '-';
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (guess[i] != '-') {
				for (int j = 0; j < corr.length; j++) {
					if (corr[j] == guess[i] && i != j) { //checks if answer contains each guess character
						corr[j] = '*'; //replaces for non-double counting
						y++;
						j = corr.length;
					}
				}
			}
		}
		
		System.out.println(g);
		System.out.println(y);
	}
}
