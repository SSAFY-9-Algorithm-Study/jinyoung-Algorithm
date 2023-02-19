package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class p11478 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("");
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            String str = "";
            for (int j = i; j < arr.length; j++) {
                str += arr[j];
                set.add(str);
            }
        }
        System.out.println(set.size());
    }
}
