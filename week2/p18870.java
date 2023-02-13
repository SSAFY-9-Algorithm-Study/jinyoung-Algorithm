package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class p18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Integer[] coordinate = new Integer[n];
        for (int i = 0; i < n; i++) {
            coordinate[i] = Integer.parseInt(st.nextToken());
        }
        // 중복 제거 + 정렬
        Set<Integer> set = new HashSet<>(Arrays.asList(coordinate));
        Integer[] sortCoord = new Integer[set.size()];
        set.toArray(sortCoord);
        Arrays.sort(sortCoord);
        // 좌표값, index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sortCoord.length; i++) {
            map.put(sortCoord[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coordinate.length; i++) {
            sb.append(map.get(coordinate[i])).append(" ");
        }
        System.out.println(sb);
    }
}
