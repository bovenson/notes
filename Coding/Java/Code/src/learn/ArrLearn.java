package learn;

import java.lang.reflect.Array;
import java.util.*;

public class ArrLearn {
    public static void main(String[] args) {
        String[] sArr;
        int iArr[];

        String[] sArrN = new String[5];
        int iArrN[] = new int[3];

        // iArr = {1, 2};   ERROR
        // iArrN = {1, 2};  ERROR
        iArr = new int[3];
        iArrN = new int[3];

        int iArr2[] = new int[]{1, 2, 3, 4, 5};
        int iArr3[] = {1, 2, 3};
        String sArr2[] = new String[]{"AA", "BB", "CC"};
        String sArr3[] = {"AA", "BB"};

        // Arrays.fill() 填充数组
        Arrays.fill(sArrN, "DD");

        // 长度
        int length = sArr3.length;

        // 遍历
        for (int i=0; i < sArr3.length; ++i) {
            System.out.println(sArr3[i]);
        }

        for (int aIArr3 : iArr3) {
            System.out.println(aIArr3);
        }

        // 数组转成 String
        String str = Arrays.toString(iArr3);
        System.out.println(str);
        System.out.println(str.getClass().getName());

        // 转成 ArrayList
        ArrayList<String> arrList = new ArrayList<String>(Arrays.asList(sArr3));

        // 转成 List
        List<String> list = Arrays.asList(sArr3);

        System.out.println(Arrays.asList(sArr3));
        System.out.println(Arrays.asList(sArr3).getClass().getName());

        boolean res = str instanceof String;

        System.out.println(res);

        // 判断包含某一个值
        System.out.println(Arrays.asList(sArr3).contains("AA"));    // true
        System.out.println(Arrays.asList(sArr3).contains("CC"));    // false

        // 转换成 set
        Set<String> set = new HashSet<String>(Arrays.asList(sArr3));

        // 排序
        int[] iArr4 = {3, 7, 1, 2, 6};
        Arrays.sort(iArr4); // Arrays.sort(iArr4, 2, 4); .sort(arr, fromIndex, toIndex);

        // 复制数组
        int[] iArr5 = Arrays.copyOf(iArr4, 10);
        int[] iArr6 = Arrays.copyOfRange(iArr4, 1, 3);
        for (int i : iArr5) {
            System.out.println(i);
        }

        // 比较数组
        System.out.println(Arrays.equals(iArr4, iArr5));

        // 去重 使用 set
        int[] iArr7 = {1, 2, 3, 2, 2, 6, 5, 3, 4, 5, 9, 8, 0, 7, 4, 3, 2, 1, 9};


    }
}
