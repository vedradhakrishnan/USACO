//name: Visits
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1230
//solves all cases

import java.util.*;
import java.io.*;

public class SilverOne {
	
	static int n; static long max_moos;
	static Cow[] cows;
	static List<ArrayList<Cow>> cycles;
	static boolean[] checked;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		
		cows = new Cow[n];
		for (int i = 0; i < n; i++) {
			cows[i] = new Cow(i); 
		}
		
		max_moos = 0; //output
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			cows[i].to = a;
			cows[a].visitors.add(cows[i]);
			
			int moos = Integer.parseInt(st.nextToken());
			max_moos += moos; //max moos starts as total moos of all visits
			cows[i].moos = moos;
		}
		
		//"cycles" are guaranteed to form when one farm visits another, forming a chain leading back to the first
		//the answer is the total moos minus the least moos from each cycle
		checked = new boolean[n];
		cycles = new ArrayList<ArrayList<Cow>>(); 
		for (int i = 0; i < n; i++) {
			if (!checked[i]) {
				detect_cycles(cows[i]); 
			}
		}
		
		for (ArrayList<Cow> c : cycles) {
			max_moos -= c.get(0).moos; //subtracts the least amount of moos from each cycle
		}		
		System.out.println(max_moos);
	}
	
	static class Cow {
		int id; int to;
		int moos;
		List<Cow> visitors;
		int cycle_id;
		
		public Cow(int num) {
			id = num; to = num;
			moos = 0;
			visitors = new ArrayList<Cow>();
			cycle_id = -1;
		}
		
		public int compareTo(Cow other) {
			return this.moos < 0 ? -1 : (this.moos == other.moos ? 0 : 1);
		}
		
		public String toString() {
//			String s = "I:" + id + " A:" + to + " M:" + moos + " F:[";
//			if (visitors.size() > 0) {
//				for (Cow c : visitors) {
//					s += c.id + ", ";
//				}
//				s = s.substring(0, s.length() - 2);
//			}
//			s += "]";
//			return s;
			return "" + id;
		}
	}
		
	static void detect_cycles(Cow origin) { 
		List<Cow> iterated = new ArrayList<Cow>(); //all iterated cows, cycle is a subset of this 
		Cow curr = origin;
		int i = 0; //counts end of cycle loop, end of subset of iterated
		boolean met = true;
		while (met) {
			iterated.add(curr);
			checked[curr.id] = true;
			curr.cycle_id = i;
			
			met = !checked[curr.to];
			curr = cows[curr.to];
			i++;
		}
		
		int j = cows[curr.id].cycle_id; //counts start of cycle loop
		ArrayList<Cow> new_cycle = new ArrayList<Cow>();
		for (Cow cow : iterated) { //finds farms that form "branches" and removes them from consideration in future searching
			if (cow.cycle_id >= j) {
				new_cycle.add(cow);
				if (cow.visitors.size() > 1) {
					branch_dfs(cow);
				}
			} else {
				branch_dfs(cow);
			}
		}
		new_cycle.sort(((c1,c2) -> (c1.moos < c2.moos ? -1 : c1.moos > c2.moos ? 1 : 0))); //sorts cycle by number of moos
		cycles.add(new_cycle);

	}

	private static void branch_dfs(Cow cow) { //makes all cows that are not in cycles "checked"
		checked[cow.id] = true;
		for (Cow prev : cow.visitors) {
			if (!checked[prev.id]) {
				branch_dfs(prev);
			}
		}
	}

}
