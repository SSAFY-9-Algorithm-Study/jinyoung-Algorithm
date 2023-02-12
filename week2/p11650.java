package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] coordinate = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            coordinate[i][0] = Integer.parseInt(str[0]);
            coordinate[i][1] = Integer.parseInt(str[1]);
        }

        Arrays.sort(coordinate, (coor1, coor2) -> {
            if (coor1[0] == coor2[0]) {
                return coor1[1] - coor2[1];
            } else {
                return coor1[0] - coor2[0];
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.print(coordinate[i][0] + " ");
            System.out.print(coordinate[i][1] + "\n");
        }
    }
}
