package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10996 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(bf.readLine());

        for (int i = 0; i < input * 2; i++) {
            if (i % 2 == 0) { // 짝수 행(0, 2, ...)
                for (int j = 0; j < input; j++) {
                    if (j % 2 == 0) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();

            } else { // 홀수 행(1, 3, ...)
                for (int j = 0; j < input; j++) {
                    if (j % 2 == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                }
                System.out.println();
            }
        }
    }
}
