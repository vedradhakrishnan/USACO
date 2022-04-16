//name: Searching for Soulmates
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1182
//solves all cases

import java.io.*;
import java.util.*;

public class SilverOne {

	public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());   
        int n = Integer.parseInt(st.nextToken());  
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(in.readLine());
        	long a = Long.parseLong(st.nextToken());
        	long b = Long.parseLong(st.nextToken());
        	
        	System.out.println(compare(a, b));
		}
	}

	private static long compare(long c, long t) { //c - current; t - target
		if (c == t) { //base case
			return 0;
		} else if (c < t) {
			if (t/2 >= c) {
				return 1 + compare(c, t/2) + t%2;
			} else if (c%2 == 1) {
				return 1 + compare(c+1, t);
			} else if ((t-c) - t%2 > 2 + (t-c)/2) {
				return 2 + compare(c/2, t/2) + t%2;
			} else {
				return t-c;
			}
		} else {
			return 1 + compare((c + c%2)/2, t) + c%2;
		}
	}
}
