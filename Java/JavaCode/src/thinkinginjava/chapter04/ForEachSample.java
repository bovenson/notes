import java.util.*;
public class ForEachSample {
    public static void main(String args[]) {
        Random rand = new Random(47);
        float f[] = new float[10];

        for (int i=0; i < f.length; ++i) {
            f[i] = rand.nextFloat();
        }

        for (float x : f) {
            System.out.println(x);
        }

        for (char c : "Hello, World!".toCharArray()) {
            System.out.print(c + " ");
        }
    }
} /**Output
0.72711575
0.39982635
0.5309454
0.0534122
0.16020656
0.57799757
0.18847865
0.4170137
0.51660204
0.73734957
H e l l o ,   W o r l d !
*/