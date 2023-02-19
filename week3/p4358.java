package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class p4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Integer> map = new TreeMap<>();
        String tree = "";
        double sum = 0;
        while ((tree = br.readLine()) != null) {
            map.put(tree, map.getOrDefault(tree, 0) + 1);
            sum++;
        }

        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            String percent = String.format("%.4f", map.get(key) / sum * 100);
            sb.append(key + " " + percent + "\n");
        }
        System.out.println(sb);
    }
}
