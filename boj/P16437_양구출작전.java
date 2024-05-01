package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16437_양구출작전 {

	static class Info {
		boolean type;
		long num;
		int to;

		Info(boolean type, long num, int to) {
			this.type = type;
			this.num = num;
			this.to = to;
		}
	}

	static int N;
	static List<List<Integer>> tree;
	static Info[] infos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		tree = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}

		infos = new Info[N + 1];
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (type.equals("S")) {
				infos[i] = new Info(true, num, to);
			} else {
				infos[i] = new Info(false, -1 * num, to);
			}
			tree.get(to).add(i);
		}

		System.out.println(bfs());
	}

	static long bfs() {

		long answer = 0;
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {

			if (tree.get(i).size() == 0) {
				queue.offer(i);
			}

		}

		while (!queue.isEmpty()) {

			int curr = queue.poll();
			int nextN = infos[curr].to;

			tree.get(nextN).remove(Integer.valueOf(curr));

			// 늑대의 부모가 양이면, 큐에 넣어주기
			if (infos[curr].type == false) {

				if (tree.get(nextN).size() == 0 && nextN != 1) {
					queue.offer(nextN);
				}

				continue;
			}

			// 구출 끝
			if (nextN == 1) {
				answer += (long) infos[curr].num;
				continue;
			}

			if (infos[nextN].type == false) { // 부모 노드가 늑대인 경우
				infos[nextN].num += (long) infos[curr].num;

				// 모든 늑대가 양을 잡아먹으면, 양으로 변경
				if (infos[nextN].num >= 0) {
					infos[nextN].type = true;
				}
			} else {
				infos[nextN].num += (long) infos[curr].num;
			}

			if (tree.get(nextN).size() == 0) {
				queue.offer(nextN);
			}
		}

		return answer;
	}
}
