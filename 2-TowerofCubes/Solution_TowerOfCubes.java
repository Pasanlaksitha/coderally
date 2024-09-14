import java.io.*;
import java.util.*;

public class Solution_TowerOfCubes {

    static class Cube {
        int[] faces;

        public Cube(int[] faces) {
            this.faces = faces;
        }

        public int getFace(int face) {
            return faces[face];
        }
    }

    static int oppositeFace(int face) {
        switch (face) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            case 3: return 2;
            case 4: return 5;
            case 5: return 4;
            default: return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int testCase = 1;

        while (true) {
            line = br.readLine();
            int N = Integer.parseInt(line);
            if (N == 0) break;

            Cube[] cubes = new Cube[N];

            // Reading the cubes
            for (int i = 0; i < N; i++) {
                String[] faces = br.readLine().split(" ");
                int[] intFaces = new int[6];
                for (int j = 0; j < 6; j++) {
                    intFaces[j] = Integer.parseInt(faces[j]);
                }
                cubes[i] = new Cube(intFaces);
            }


            int[][] dp = new int[N][6];
            String[][] path = new String[N][6];


            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], 1);
                for (int j = 0; j < 6; j++) {
                    path[i][j] = (i + 1) + " " + faceName(j);
                }
            }


            for (int i = 1; i < N; i++) {
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < i; k++) {
                        for (int l = 0; l < 6; l++) {
                            if (cubes[k].getFace(l) == cubes[i].getFace(oppositeFace(j)) && dp[i][j] < dp[k][l] + 1) {
                                dp[i][j] = dp[k][l] + 1;
                                path[i][j] = path[k][l] + "\n" + (i + 1) + " " + faceName(j);
                            }
                        }
                    }
                }
            }


            int maxLength = 0;
            String result = "";

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 6; j++) {
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        result = path[i][j];
                    }
                }
            }


            if (testCase > 1) System.out.println();
            System.out.println("Case #" + testCase);
            System.out.println(maxLength);
            System.out.println(result);

            testCase++;
        }
    }


    static String faceName(int face) {
        switch (face) {
            case 0: return "front";
            case 1: return "back";
            case 2: return "left";
            case 3: return "right";
            case 4: return "top";
            case 5: return "bottom";
            default: return "";
        }
    }
}
