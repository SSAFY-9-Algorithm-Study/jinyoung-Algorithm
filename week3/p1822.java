package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class p1822 {
    public static void main(String[] args) throws IOException {
        TreeSet<Integer> setA = new TreeSet<>();
        TreeSet<Integer> setB = new TreeSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lenA = Integer.parseInt(st.nextToken());
        int lenB = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lenA; i++) {
            setA.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lenB; i++) {
            setB.add(Integer.parseInt(st.nextToken()));
        }

        setA.removeAll(setB);

        System.out.println(setA.size());
        if (setA.size() > 0) {
            for (int i : setA) {
                System.out.print(i + " ");
            }
        }
    }
}
