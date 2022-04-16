//name: Non-Transitive Dice 
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1180
//solves all cases

import java.io.*;
import java.util.*;

public class BronzeTwo {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < t; i++) {
			
			byte[] a = new byte[4];
			byte[] b = new byte[4];
			st = new StringTokenizer(in.readLine());
			
			for (int j = 0; j < a.length; j++) {
				a[j] = Byte.parseByte(st.nextToken());
			}
			Arrays.sort(a);
			
			for (int j = 0; j < b.length; j++) {
				b[j] = Byte.parseByte(st.nextToken());
			}
			Arrays.sort(b);
			
			output(a,b);
			
		}
	}

	private static void output(byte[] a, byte[] b) {
		
		if (score(a,b) < 0) {
			byte[] temp = Arrays.copyOf(a,4);
			a = Arrays.copyOf(b, 4);
			b = temp;
		}
		
		Set<Byte>[] c = new HashSet[4]; //every possible number for each side
		for (int i = 0; i < 4; i++) {
			c[i] = new HashSet<Byte>();
			for (byte j = 1; j <= 10; j++) {
				c[i].add(j);
			}
		}
		
		//brute force checks every dice permutation to see if one results in non-transitive
		
		boolean ans = false;
		for (byte c1 : c[0]) {
			for (byte c2 : c[1]) {
				for (byte c3 : c[2]) {
					for (byte c4 : c[3]) {
						if (!ans) {
							byte[] cc = {c1, c2, c3, c4};
							if (score(cc, a) > 0 && score(cc, b) < 0) {
								ans = true;
							}
						}
					}
				}
			}
		}	

		
		if (ans) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
	}

	private static int score(byte[] x, byte[] y) { //sign of score indicates wheter x beats y
		int score = 0;
		for (byte bx : x) {
			for (byte by : y) {
				if (bx > by) {
					score++;
				} else if (by > bx){
					score--;
				}
			}
		}
		return score;
	}

}
