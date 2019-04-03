import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int distance(int p1[], int p2[]) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private static void swap(int p[][], int a, int b) {
        int t[] = p[a];
        p[a] = p[b];
        p[b] = t;
    }

    private static int solve(int points[][], int curPos, int dist) {
        if (curPos >= points.length) {
            return dist;
        }
        int res1 = solve(points, curPos+1, dist + distance(points[curPos], points[curPos-1]));
        if (curPos + 1 < points.length) {
            swap(points, curPos, curPos + 1);
            int res2 = solve(points, curPos+1, dist + distance(points[curPos], points[curPos-1]));
            swap(points, curPos, curPos + 1);
            return Math.min(res1, res2);
        }
        return res1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int points[][] = new int[n+1][2];
        points[0][0] = 0;
        points[0][1] = 0;
        for (int i=1; i <= n; ++i) {
            String line = br.readLine();
            String []nums = line.split(",");
            points[i][0] = Integer.parseInt(nums[0]);
            points[i][1] = Integer.parseInt(nums[1]);
        }
        System.out.println(solve(points, 1, 0));
    }
}
