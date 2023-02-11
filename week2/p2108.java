package week2;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class p2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        // 산술평균
        System.out.println(Math.round(sum / n));
        // 중앙값
        System.out.println(arr[n / 2]);
        // 최빈값
        System.out.println(mode(arr));
        // 범위
        System.out.println(arr[n - 1] - arr[0]);
    }

    static int mode(int[] arr) {
        int[] cnt = new int[8001];

        for (int i : arr) {
            if (i < 0) {
                cnt[Math.abs(i) + 4000]++;
            } else {
                cnt[i]++;
            }
        }

        int max = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] != 0 && cnt[i] > max) {
                max = cnt[i];
            }
        }

        ArrayList<Integer> modeList = new ArrayList<>();
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == max) {
                if (i > 4000) {
                    modeList.add((i - 4000) * -1);
                } else {
                    modeList.add(i);
                }
            }
        }
        Collections.sort(modeList);

        if (modeList.size() > 1) { // 최빈값 여러 개면 두 번째로 작은 값을 출력
            return modeList.get(1);
        } else {
            return modeList.get(0);
        }
    }
}
