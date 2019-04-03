package learn.io;
/**
 * Java 标准输入输出
 * @author bovenson
 */
public class StdInOut {
    public static void main(String args[]) {
        double sum = 0.0;
        int cnt = 0;

        while(!StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            cnt++;
        }

        double avg = sum / cnt;

        StdOut.print(String.format("Average is %.5f\n", avg));
    }
}