package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class p10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> cardNum = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            cardNum.put(key, 1);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        LinkedHashMap<Integer, Integer> predicNum = new LinkedHashMap<>();
        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());
            // key가 이미 저장된 값이면 1, 저장되지 않은 값이면 0
            predicNum.put(key, cardNum.getOrDefault(key, 0));
        }

        for (int i : predicNum.values()) {
            System.out.print(i + " ");
        }
    }
}
