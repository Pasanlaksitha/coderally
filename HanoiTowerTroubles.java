import java.util.*;

public class HanoiTowerTroubles {

    // Helper function to check if a number is a perfect square
    public static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    // Function to calculate the maximum number of balls that can be placed
    public static int maxBallsOnPegs(int N) {
        // We need to keep track of which balls are placed on which peg
        ArrayList<ArrayList<Integer>> pegs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            pegs.add(new ArrayList<>());
        }

        int ball = 1;
        while (true) {
            boolean placed = false;

            // Try to place the current ball on one of the pegs
            for (ArrayList<Integer> peg : pegs) {
                boolean valid = true;

                // Check if the sum of the current ball and any ball already on this peg is not a perfect square
                for (int existingBall : peg) {
                    if (isPerfectSquare(existingBall + ball)) {
                        valid = false;
                        break;
                    }
                }

                // If valid, place the ball on this peg
                if (valid) {
                    peg.add(ball);
                    placed = true;
                    break;
                }
            }

            // If the ball could not be placed on any peg, stop
            if (!placed) {
                break;
            }

            ball++;
        }

        return ball - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // Number of test cases
        int[] results = new int[T];

        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();  // Number of pegs for this test case
            results[t] = maxBallsOnPegs(N); // Store the result for each test case
        }

        // Output the results for all test cases
        for (int result : results) {
            System.out.println(result);
        }

        sc.close();
    }
}
