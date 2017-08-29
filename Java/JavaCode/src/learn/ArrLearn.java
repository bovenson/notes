package learn;

import java.util.Arrays;

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

        // 长度
        int length = sArr3.length;

        // 遍历
        for (int i=0; i < sArr3.length; ++i) {
            System.out.println(sArr3[i]);
        }

        for (int aIArr3 : iArr3) {
            System.out.println(aIArr3);
        }

        // int 数组转成 String
        String str = Arrays.toString(iArr3);
        System.out.print(str);
    }
}
