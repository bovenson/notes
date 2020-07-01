import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class T1 {

    static class LR {
        int l, r;
        LR (int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int m = scanner.nextInt();
        List<LR> times = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i=0; i < m; ++i) {
            times.add(new LR(scanner.nextInt(), scanner.nextInt()));
        }
        times.sort((o1, o2) -> o1.l - o2.l);

        boolean flag = false;
        for (int i=0; i < times.size(); ++i) {
            if (i > 0 && times.get(i-1).r > times.get(i).l) {
                flag = true;
                break;
            }
        }
        if (flag) {

        }
    }
}
