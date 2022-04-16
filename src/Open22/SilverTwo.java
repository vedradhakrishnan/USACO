//name: Visits
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1231
//solves all cases - changed after comp.

import java.util.*;
import java.io.*;

public class SilverTwo {

	static final int MAX_LENGTH = 'r' - 'a' + 1; //length of a to r, 18
	static boolean[][] pair_match; //grid of whether pair i,j creates a matching subset
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<String> first = Arrays.asList(in.readLine().split(""));
        List<String> second = Arrays.asList(in.readLine().split(""));
        
        pair_match = new boolean[MAX_LENGTH][MAX_LENGTH];
        String[] first_letters = "abcdefghijklmnopqr".split("");
        String[] second_letters = "abcdefghijklmnopqr".split("");
        
        for (int i = 0; i < MAX_LENGTH; i++) { //fills in pair_match grid by checking matches w all possible pairs 
            for (int j = i; j < MAX_LENGTH; j++) {
            	List<String> pair = new ArrayList<>();
            	pair.add(first_letters[i]);
            	pair.add(second_letters[j]);
            	
                List<String> first_reduced = new ArrayList<>(first);
                List<String> second_reduced = new ArrayList<>(second);
                first_reduced.retainAll(pair);
                second_reduced.retainAll(pair);
                
                pair_match[i][j] = second_reduced.equals(first_reduced);
    		}
		}

        st = new StringTokenizer(in.readLine());
        int q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            char[] subset = st.nextToken().toCharArray();
            System.out.print(subset_equality(subset) ? "Y" : "N");
        }
	}

	private static boolean subset_equality(char[] s) {
		for (int i = 0; i < s.length; i++) { //goes through all pairs in query; if all pairs are true in pair_match, then output yes
			for (int j = i; j < s.length; j++) {
				if (!pair_match[s[i] - 'a'][s[j] - 'a']) {
					return false;
				}
			}
		}
		return true;
	}
	
}
