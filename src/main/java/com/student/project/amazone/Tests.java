package com.student.project.amazone;

import java.util.Scanner;

public class Tests {

    static final int binarySearch(int arr[], int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        //        Gọi x2 là độ rộng (chiều ngang của tòa nhà)
        //        Gọi y2 là chiều dài (chiều cao của tòa nhà)
        //        Gọi x1 là tạo độ x ban đầu
        //        Gọi y1 là tạo độ y ban đầu
        // game loop
        int x1 = 0;
        int y1 = 0;

        // x + y = 0;
        // 2x0 + 5y0 = 0;
        int x2 = W - 1;
        int y2 = H - 1;
        int turnCountDown = 0;
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D,
            // DL, L or UL)
            switch (bombDir) {
                case "U":
                    break;
                case "UR":
                    H = Y0;
                    W = X0;
                    H = H - ((H + 1) / 2);
                    W = W + ((W + 1) / 2);
                    System.out.println("" + W + " " + H + "");
                    break;
                case "R":
                    System.out.println("D");
                    break;
                case "DR":
                    break;
                case "D":
                    break;
                case "DL":
                    break;
                case "L":
                    break;
                case "UL":
                    break;

            }
        }
    }
}
