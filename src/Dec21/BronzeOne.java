import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BronzeOne {
	
	//name: Lonely Photo 
	//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1155
	//solves 10/11 cases
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();
		
		ArrayList<Integer> cws = new ArrayList<Integer>();
		char[] c = sc.nextLine().toCharArray();
		
		sc.close();
		
		ArrayList<Integer> l = new ArrayList<Integer>();
		int count = 1;
		for (int i = 1; i < c.length; i++) {			
			if ((int)(c[i]) - 71 != (int)(c[i-1]) - 71) { //adds the amount of same-type cows at a stretch
				l.add(count);
				count = 1;
			} else {
				count++;
			}
			
		}
		l.add(count);
		
		long del = 0;
		
		for (int i = 1; i < l.size(); i++) {
			del += l.get(i) - 1;
			del += l.get(i-1) - 1;
			
			if (l.get(i) == 1 && i < l.size() - 1) { //adds to number of deleted folders for each stretch
				del += l.get(i-1) * l.get(i+1);
			}

		}
		
		System.out.println(del);
		
	}

	private static int sum(List<Integer> list) {
		return list.stream().mapToInt(Integer::intValue).sum();
	}

}
