package week10;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		long answer = solution.solution(2, 7, new int[] { 1, 0, 2, 0, 1, 0, 2 }, new int[] { 0, 2, 0, 1, 0, 2, 0 });
		System.out.println(answer);
	}

	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		int totalDeliveries = 0;
		int totalPickups = 0;
		for (int i = 0; i < n; i++) {
			totalDeliveries += deliveries[i];
			totalPickups += pickups[i];
		}

		int currHome = n - 1;
		// 배달 & 수거해야 하는 개수가 0일 때 까지 반복
		while (totalDeliveries != 0 && totalPickups != 0) {

			int deliveriesSum = 0;
			int pickupsSum = 0;
			boolean flag = false;
			int dist = 0;

			for (int i = currHome; i >= 0; i--) {
				// 현재 집에 배달 & 수거 할 것이 없으면 continue
				if (deliveries[i] == 0 && pickups[i] == 0) {
					continue;
				}

				dist = Math.max(dist, i + 1); // 현재 방문하는 집까지의 거리

				if (deliveries[i] + deliveriesSum <= cap) { // 배달 가능
					deliveriesSum += deliveries[i];
					deliveries[i] = 0;

				} else { // 현재 집에 모든 택배를 배달할 수 없음 
					int currDeliver = cap - deliveriesSum; // 트럭에 택배 상자 실을 수 있는 개수
					if (currDeliver > 0) { // 배달할 수 있는 수량만 하기
						deliveriesSum += currDeliver;
						deliveries[i] -= currDeliver;
					}

					flag = true;
					currHome = i;
				}

				if (pickups[i] + pickupsSum <= cap) { // 수거 가능
					pickupsSum += pickups[i];
					pickups[i] = 0;

				} else {
					int curPickup = cap - pickupsSum;
					if (curPickup > 0) {
						pickupsSum += curPickup;
						pickups[i] -= curPickup;
					}

					flag = true;
					currHome = i;
				}
//				System.out.println(Arrays.toString(deliveries));
				// 현재 집까지 모두 배달 or 수거를 못한 경우 다음 집 볼 필요 없음 
				if (flag)
					break;
			}

			totalDeliveries -= deliveriesSum;
			totalPickups -= pickupsSum;
			System.out.println(dist);
			answer += dist * 2;
		}

		return answer;
	}
}
