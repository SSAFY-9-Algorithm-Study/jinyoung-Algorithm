package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.azul.crs.runtime.utils.PackedDataEntriesMap.Entry;

public class p2910 {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // LinkedHashMap으로 입력 순서 저장
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (list1, list2) -> {
            // value 기준으로 내림차순 정렬
            if (list2.getValue() - list1.getValue() != 0) {
                return list2.getValue() - list1.getValue();
            } else { // value 같으면 정렬X
                return 0;
            }
        });

        for (Map.Entry<Integer, Integer> i : list) {
            for (int j = 0; j < i.getValue(); j++) {
                System.out.print(i.getKey() + " ");
            }
        }
    }
}
