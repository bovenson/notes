import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void find(Integer [][]matrix, int key) {
        
    }

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer matrix[][] = new Integer[n][];
        for (int i = 0; i < n; ++i) {
            String line = br.readLine().trim();
            String splits[] = line.split("\\s+");
            matrix[i] = Arrays.stream(splits).map(Integer::parseInt).toArray(Integer[]::new);
        }
        System.out.println(Arrays.deepToString(matrix));
    }
}
