import java.util.*;
import java.io.*;

public class BronzeTwo {
	
	//name: Lonely Photo 
	//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1155
	//solves 10/10 cases - changed after comp.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tok = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(tok.nextToken());
		
		int[] current = new int[n]; //current temp. in each barn
		tok = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			current[i] = Integer.parseInt(tok.nextToken());
		}
		
		int[] delta = new int[n]; //change in temp. needed calculated for easier processing
		tok = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			delta[i] = current[i] - Integer.parseInt(tok.nextToken());
		}
		
		System.out.println(num_commands(delta)); 
	}

	private static long num_commands(int[] a) {	
		//recursion on the delta list; split into 2 functions for easier reading
		int i = 0; int j = 0;
		long count = 0;
		while (i < a.length) { //splits array into stretches of same sign
			if (j < a.length && signum(a[j]) == signum(a[i])) {
				j++;
			} else {
				int[] subset = Arrays.copyOfRange(a, i, j);
				if (!Arrays.equals(subset, new int[j - i])) {						
					count += reduce(subset);
				}
				i = j;
			}
		}
		return count;
	}
	
	private static long reduce(int[] a) {
		//subtracts min value from entire array and does recursion
		int[] sorted = Arrays.copyOf(a, a.length);
		Arrays.sort(sorted);
		int min_value = sorted[0] > 0 ? sorted[0] : sorted[a.length - 1];
		return num_commands(Arrays.stream(a).map(i -> i - min_value).toArray()) + Math.abs(min_value);
	}
	
	private static int signum(int x) {
		return x == 0 ? x : x / Math.abs(x);
	}

}
