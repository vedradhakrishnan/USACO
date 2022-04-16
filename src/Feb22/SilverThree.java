//name: Email Filing
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1208
//solves all cases

import java.util.*;
import java.io.*;

public class SilverThree {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		for (int case_num = 0; case_num < t; case_num++) {
			st = new StringTokenizer(in.readLine());
			int m = Integer.parseInt(st.nextToken()); //num of mailboxes
			int n = Integer.parseInt(st.nextToken()); //num of emails
			int k = Integer.parseInt(st.nextToken()); //num of items on screen at once
			
			int top_folder = 1; //how many items scrolled down
			int top_mail = 0;
			List<Integer> mail = new ArrayList<Integer>();
			int[] occurences = new int[m + 1];
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				int token = Integer.parseInt(st.nextToken());
				mail.add(token);
				occurences[token]++;
			}		

			boolean change;
			while (mail.size() > 0) {
				change = false; //checks if mail can be filed wo scrolling
				
				for (int f = top_mail; f < mail.size() && f < top_mail + k; f++) { //files all mail wo scrolling
					if (mail.get(f) >= top_folder && mail.get(f) < top_folder + k) {
						change = true;
						
						occurences[mail.remove(f)]--;
						f--;
					}			
				}
				
				if (!mail.contains(top_folder)) { //scrolls boxes if no more mail belongs to the top box
					change = true;
					
					top_folder++;
				}
				
				if (top_mail > mail.size() - k) { //scrolls up if the current window is less than k
					change = true;
					
					top_mail = k < mail.size() ? mail.size() - k : 0;
				}

				if (!change) { //scrolls mail down if no more mail can be filed
					if (top_mail < mail.size() - k) {
						top_mail++;
					} else { //impossible if past bottom of mail list
						break;
					}
				}
			}
			
			System.out.println(mail.size() == 0 ? "YES" : "NO");
		}
	}
}
