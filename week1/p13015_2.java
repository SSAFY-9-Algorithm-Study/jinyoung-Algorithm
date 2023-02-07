package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p13015_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int a = 0;
        int b = n - 1;
        int c = 3 * n - 3;
        int d = 4 * n - 4;

        for (int i = 1; i <= n * 2 - 1; i++) {
            // top, bottom
            if (i == 1 || i == (n * 2 - 1)) {
                for (int j = a; j <= b; j++) {
                    System.out.print("*");
                }
                for (int j = b + 1; j < c; j++) {
                    System.out.print(" ");
                }

                for (int j = c; j <= d; j++) {
                    System.out.print("*");
                }

            } else {
                for (int j = 0; j <= d; j++) {
                    if (j == a || j == b || j == c || j == d) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();

            if (i < n) { // n번째 줄까지 a, b 증가 / c, d 감소
                a++;
                b++;
                c--;
                d--;
            } else { // n번째 줄 이후 a, b 감소 / c, d 증가
                a--;
                b--;
                c++;
                d++;
            }
        }
    }
}
