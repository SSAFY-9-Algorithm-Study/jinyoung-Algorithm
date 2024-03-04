package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3079_입국심사 {
	
	static int N, M;
	static long maxTime;
	static int[] times;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		times = new int[N];
		
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(br.readLine());
			maxTime = Math.max(maxTime, times[i]);
		}
		
		binarySearch();
	}
	
	static void binarySearch() {
		
		long left = 1;
		long right = (long) maxTime * M;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			
			if (checkCount(mid)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(right);
	}
	
	static boolean checkCount(long time) {

        long cnt = 0;

        for (int i = 0; i < N; i++) {
            cnt += time / times[i];
            
            if (cnt >= M) {
            	return true;
            }
        }
        
        return false;
    }

}
