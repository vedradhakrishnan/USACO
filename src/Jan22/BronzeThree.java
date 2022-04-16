//name: Drought
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1181
//solves all cases

import java.io.*;
import java.util.*;

public class BronzeThree {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			long[] hunger = new long[n];
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				hunger[j] = (long)(Integer.parseInt(st.nextToken()));
			}
			
			System.out.println(bagsAlgo(hunger));
			

		}
		
	}

	private static long bagsAlgo(long[] h) {  //greedy algorithm
		
		long bags = 0;
		if (h.length > 2) {
			for (int i = 0; i < h.length - 1; i++) {
				if (h[i] < h[i+1]) {
					try {
						long c = h[i+1] - h[i]; //given stalls 1-3, adds to stalls 2 and 3 to make 1 and 2 of equal hunger
						h[i+1] -= c;
						h[i+2] -= c;
						bags += 2*c;
		
					} catch (IndexOutOfBoundsException e) {
						return -1;
					}
			
				} else if (h[i+1] < h[i]) { 
					try {
						long c = h[i] - h[i+1]; //given stalls 1-3, adds to stalls 1 and 2 to make 2 and 3 of equal hunger
						h[i-1] -= c;
						h[i] -= c;
						bags += 2*c;
						
						i -= 3; //backtracks in order to re-establish uniformity
						if (i < 1) {
							i = 0;
						}
		
					} catch (IndexOutOfBoundsException e) { //if algorithm results in going to a negative index, there is no solution
						return -1;
					}
				}
			}
		} else if (h.length == 2) { 
			if (h[0] == h[1]) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return 0;
		}
		
		long[] comp = new long[h.length];
		Arrays.fill(comp, h[0]);
		
		if (h[0] >= 0 && Arrays.equals(h, comp)) { //first item must be non-negative + array must only contain one unique value
			return bags;
		} else {
			return -1;
		}
	}

}
