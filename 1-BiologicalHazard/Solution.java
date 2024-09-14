import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'bioHazard' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY allergic
     *  3. INTEGER_ARRAY poisonous
     */

    public static long bioHazard(int n, List<Integer> allergic, List<Integer> poisonous) {
    List<int[]> allPosibilities = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
        for (int j = i; j <= n; j++) {
            int[] combination = new int[j - i + 1];
            for (int k = 0; k < combination.length; k++) {
                combination[k] = i + k;
            }
            allPosibilities.add(combination);
        }
    }

    List<int[]> reduceList = new ArrayList<>();
    for (int i = 0; i < allergic.size(); i++) {
        reduceList.add(new int[]{allergic.get(i), poisonous.get(i)});
    }

    allPosibilities.removeIf(possibility -> {
        for (int[] reduce : reduceList) {
            if (isSubset(possibility, reduce)) {
                return true;
            }
        }
        return false;
    });

    return allPosibilities.size();

    
}

private static boolean isSubset(int[] array, int[] subset) {
    int i = 0, j = 0;
    while (i < array.length && j < subset.length) {
        if (array[i] == subset[j]) {
            j++;
        }
        i++;
    }
    return j == subset.length;
}

}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int allergicCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> allergic = IntStream.range(0, allergicCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int poisonousCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> poisonous = IntStream.range(0, poisonousCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.bioHazard(n, allergic, poisonous);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
