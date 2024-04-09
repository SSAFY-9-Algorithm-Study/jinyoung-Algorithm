package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P20061_모노미노도미노2 {

	static final int LENGTH_1 = 6;
	static final int LENGTH_2 = 4;
	static int N, score;
	static boolean[][] greenBoard, blueBoard;
	static int[] blockX = { 0, 0, 1 }; // t = 1, t = 2, t = 3
	static int[] blockY = { 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		greenBoard = new boolean[LENGTH_1][LENGTH_2];
		blueBoard = new boolean[LENGTH_2][LENGTH_1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			playGame(t, x, y);
		}

		System.out.println(score);
		System.out.println(checkBlockCount());
	}

	private static void print() {

		System.out.println("GREEN");
		for (int i = 0; i < LENGTH_1; i++) {
			System.out.println(Arrays.toString(greenBoard[i]));
		}

		System.out.println("BLUE");
		for (int i = 0; i < LENGTH_2; i++) {
			System.out.println(Arrays.toString(blueBoard[i]));
		}
	}

	private static int checkBlockCount() {
		int blockCount = 0;

		for (int i = 0; i < LENGTH_1; i++) {
			for (int j = 0; j < LENGTH_2; j++) {

				if (greenBoard[i][j]) {
					blockCount++;
				}
				if (blueBoard[j][i]) {
					blockCount++;
				}
			}
		}
		return blockCount;
	}

	private static void playGame(int t, int x, int y) {

		// 초록색 보드, 파란색 보드에 블록 두기
		setBlockInGreenBoard(t, x, y);
		setBlockInBlueBoard(t, x, y);

		// 보드에 블록이 한 줄 채워졌는지 확인하기
		checkDeleteBlock();

		// 연한 색상 칸에 블록이 있다면 그 줄의 수만큼 블록 삭제하기
		checkLightGreenBoard();
		checkLightBlueBoard();

//		print();
	}

	private static void checkLightGreenBoard() {

		// 연한 칸에 몇 줄이 있는지 확인하기
		int greenFlag = 0;
		for (int i = 1; i >= 0; i--) {
			for (int j = 0; j < LENGTH_2; j++) {

				if (greenBoard[i][j]) {
					greenFlag++;
					break;
				}
			}
		}

		for (int i = 0; i < greenFlag; i++) {
			deleteGreenBlock(LENGTH_1 - 1);
		}
	}

	private static void checkLightBlueBoard() {

		// 연한 칸에 몇 줄이 있는지 확인하기
		int blueFlag = 0;
		for (int i = 1; i >= 0; i--) {
			for (int j = 0; j < LENGTH_2; j++) {

				if (blueBoard[j][i]) {
					blueFlag++;
					break;
				}
			}
		}

//		System.out.println(blueFlag);
		for (int i = 0; i < blueFlag; i++) {
			deleteBlueBlock(LENGTH_1 - 1);
		}
	}

	private static void checkDeleteBlock() {

		for (int i = LENGTH_1 - 1; i >= 2; i--) {
			boolean greenFlag = true;
			boolean blueFlag = true;

			for (int j = 0; j < LENGTH_2; j++) {
				if (!greenBoard[i][j]) {
					greenFlag = false;
				}
				if (!blueBoard[j][i]) {
					blueFlag = false;
				}
			}

			// 한 줄이 채워져있다면 블록 지워주기
			if (greenFlag) {
				deleteGreenBlock(i);
				score++;
			}

			if (blueFlag) {
				deleteBlueBlock(i);
				score++;
			}
			
			if (blueFlag || greenFlag) {
				i++;
			}
		}

	}

	private static void deleteGreenBlock(int idx) {

		for (int i = 0; i < LENGTH_2; i++) {
			greenBoard[idx][i] = false;
		}

		downGreenBlock(idx);
	}

	private static void deleteBlueBlock(int idx) {

		for (int i = 0; i < LENGTH_2; i++) {
			blueBoard[i][idx] = false;
		}

		downBlueBlock(idx);
	}

	private static void downBlueBlock(int idx) {

		for (int i = idx - 1; i >= 0; i--) {
			for (int j = 0; j < LENGTH_2; j++) {
				blueBoard[j][i + 1] = blueBoard[j][i];
				blueBoard[j][i] = false;
			}
		}
	}

	private static void downGreenBlock(int idx) {

		for (int i = idx - 1; i >= 0; i--) {
			for (int j = 0; j < LENGTH_2; j++) {
				greenBoard[i + 1][j] = greenBoard[i][j];
				greenBoard[i][j] = false;
			}
		}
	}

	private static void setBlockInBlueBoard(int t, int x, int y) {

		int possibleIdx = 0;
		boolean flag = false;

		// 맨 오른쪽 열부터 확인하기
		for (int i = LENGTH_1 - 1 - blockY[t]; i >= 0; i--) {

			boolean firstBlock = blueBoard[x][i];
			boolean secondBlock = blueBoard[x + blockX[t]][i + blockY[t]];

			// 현재 위치에 블록을 놓을 수 있는지 확인하기
			if (flag == false && !firstBlock && !secondBlock) {
				flag = true;
				possibleIdx = i;
			}

			// 블록을 놓을 수 없네?
			if (flag == true && (firstBlock || secondBlock)) {
				flag = false;
			}
		}

		blueBoard[x][possibleIdx] = true;
		blueBoard[x + blockX[t]][possibleIdx + blockY[t]] = true;

	}

	private static void setBlockInGreenBoard(int t, int x, int y) {

		int possibleIdx = 0;
		boolean flag = false;

		// 맨 아래 행부터 확인하기
		for (int i = LENGTH_1 - 1 - blockX[t]; i >= 0; i--) {

			boolean firstBlock = greenBoard[i][y];
			boolean secondBlock = greenBoard[i + blockX[t]][y + blockY[t]];

			// 현재 위치에 블록을 놓을 수 있는지 확인하기
			if (flag == false && !firstBlock && !secondBlock) {
				flag = true;
				possibleIdx = i;
			}

			// 블록을 놓을 수 없네?
			if (flag == true && (firstBlock || secondBlock)) {
				flag = false;
			}

		}

		greenBoard[possibleIdx][y] = true;
		greenBoard[possibleIdx + blockX[t]][y + blockY[t]] = true;
	}
}
