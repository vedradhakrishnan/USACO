//name: Cow Frisbee
//page: http://www.usaco.org/index.php?page=viewproblem2&cpid=1183
//solves all cases

import java.io.*;
import java.util.*;

public class SilverTwo {
	
	public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
       
        int[] c = new int[n+1]; //array of cows such that c[0] is the position of the shortest, c[n-1] is the position of the tallest
        long ans = 0;
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            c[Integer.parseInt(st.nextToken())] = i;
        }
       
        TreeSet<Integer> u = new TreeSet<Integer>(); //sorted positions of cows which are added one by one from tallest to shortest
        for (int i = c.length - 1; i > 0; i--) {
            u.add(c[i]);
           
            try {
                ans += (c[i] - u.headSet(c[i], false).last() + 1); //adds distance from cow to right taller cow
            } catch (NoSuchElementException e) {}
           
            try {
                ans += (u.tailSet(c[i], false).first() - c[i] + 1); //adds distance from cow to left taller cow
            } catch (NoSuchElementException e) {}
            
            //if exception is caught, 0 is added because cow is the leftmost/rightmost cow w that height or greater
        }
       
        System.out.println(ans);
}
}

