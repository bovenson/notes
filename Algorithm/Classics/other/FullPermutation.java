//: FullPermutation.java
import java.util.*;

public class FullPermutation {

    public static void swap(int[] array, int a, int b) {
        int t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    public static void FullPermutationRecursion(int[] array, int index) {
        if (index >= array.length) {
            System.out.println(Arrays.toString(array));
        } else {
            for (int i=index; i < array.length; ++i) {
                swap(array, i, index);
                FullPermutationRecursion(array, index+1);
                swap(array, i, index);
            }
        }
    }

    public static void main(String args[]) {
        int[] array = {1, 2, 3};
        FullPermutationRecursion(array, 0);
    }
} /*Output
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 2, 1]
[3, 1, 2]
*/
