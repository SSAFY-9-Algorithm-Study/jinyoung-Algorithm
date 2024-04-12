package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 왕실의기사대결 {

	static class Knight {
		int r, c, h, w, k, damage;
		boolean isGone, isDamaged;

		public Knight(int r, int c, int h, int w, int k) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.isGone = false;
			this.isDamaged = false;
			this.damage = 0;
		}
	}

	static class Order {
		int i, d;

		public Order(int i, int d) {
			this.i = i;
			this.d = d;
		}
	}

	static int L, N, Q;
	static int[][] map, knightMap;
	static Knight[] knights;
	static Order[] orders;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		init();
		playGame();
		System.out.println(checkDamage());
	}
	
	private static int checkDamage() {
		int damageSum = 0;

		for (int i = 1; i < knights.length; i++) {
			if (knights[i].isGone == false) {
				damageSum += knights[i].damage;
			}
		}

		return damageSum;
	}

	private static void playGame() {

		for (Order order : orders) {

			// 사라진 기사에게 명령을 내리면 아무런 반응이 없다.
			if (knights[order.i].isGone) {
				continue;
			}

			// 이동할 수 있는지 확인 후 이동하기.
			if (canMove(order.i, order.d)) {
				move(order.i, order.d);
			}

			knights[order.i].isDamaged = false;
			checkTrap();

		}

	}

	private static void checkTrap() {

		for (int i = 1; i < knights.length; i++) {
			if (!knights[i].isDamaged) {
				continue;
			}

			int damage = getDamage(i);
			knights[i].damage += damage;
			knights[i].k -= damage;
			knights[i].isDamaged = false;
			
			if (knights[i].k <= 0) {
				knights[i].isGone = true;
				deleteKnights(i);
			}
		}

	}

	private static void deleteKnights(int idx) {
		Knight knight = knights[idx];
		
		for (int r = knight.r; r < knight.r + knight.h; r++) {
			for (int c = knight.c; c < knight.c + knight.w; c++) {
				knightMap[r][c] = 0;
			}
		}
		
	}

	private static int getDamage(int idx) {
		Knight knight = knights[idx];
		int damageSum = 0;

		for (int r = knight.r; r < knight.r + knight.h; r++) {
			for (int c = knight.c; c < knight.c + knight.w; c++) {

				if (map[r][c] == 1) {
					damageSum++;
				}

			}
		}
		
		return damageSum;
	}

	private static void move(int idx, int d) {
		Knight knight = knights[idx];

		for (int r = knight.r; r < knight.r + knight.h; r++) {
			for (int c = knight.c; c < knight.c + knight.w; c++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (knightMap[nr][nc] != 0 && knightMap[nr][nc] != idx) {
					move(knightMap[nr][nc], d);
				}
			}
		}
		
		updateMap(idx, d);
		knights[idx].isDamaged = true;
		
	}

	private static void updateMap(int idx, int d) {
		
		Knight knight = knights[idx];
		
		for (int r = knight.r; r < knight.r + knight.h; r++) {
			for (int c = knight.c; c < knight.c + knight.w; c++) {
				knightMap[r][c] = 0;
			}
		}
		
		knights[idx].r += dr[d];
		knights[idx].c += dc[d];
		
		
		for (int r = knights[idx].r; r < knights[idx].r + knights[idx].h; r++) {
			for (int c = knights[idx].c; c < knights[idx].c + knights[idx].w; c++) {
				knightMap[r][c] = idx;
			}
		}
		
	}

	private static boolean canMove(int idx, int d) {
		Knight knight = knights[idx];

		for (int r = knight.r; r < knight.r + knight.h; r++) {
			for (int c = knight.c; c < knight.c + knight.w; c++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= L || nc < 0 || nc >= L) {
					return false;
				}

				if (map[nr][nc] == 2) {
					return false;
				}

				if (knightMap[nr][nc] != 0 && knightMap[nr][nc] != idx) {
					if (!canMove(knightMap[nr][nc], d)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		map = new int[L][L];
		knightMap = new int[L][L];
		knights = new Knight[N + 1];
		orders = new Order[Q];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < L; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			knights[i] = new Knight(r, c, h, w, k);

			for (int row = r; row < r + h; row++) {
				for (int col = c; col < c + w; col++) {
					knightMap[row][col] = i;
				}
			}
		}

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			orders[q] = new Order(i, d);
		}

	}

}
