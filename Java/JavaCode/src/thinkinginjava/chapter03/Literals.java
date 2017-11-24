public class Literals {
    public static void main(String args[]) {
        int i1 = 0x2f;  // 16进制 (小写)
        System.out.println("i1: " + Integer.toBinaryString(i1));    // i1: 101111

        int i2 = 0X2F;  // 16进制 (大写)
        System.out.println("i2: " + Integer.toBinaryString(i2));    // i2: 101111

        int i3 = 0177;  // 8进制 (0开头)
        System.out.println("i3: " + Integer.toBinaryString(i3));    // i3: 1111111

        int c = 0xffff; // char类型16进制最大值
        System.out.println("c: " + Integer.toBinaryString(c));      // c: 1111111111111111

        int b = 0x7f; // byte 类型16进制表示的最大值
        System.out.println("b: " + Integer.toBinaryString(b));      // b: 1111111

        short s = 0x7fff;   // max short hex value
        System.out.println("s: " + Integer.toBinaryString(s));  // s: 111111111111111

        long n1 = 200L; // long suffix
        long n2 = 200l; // long suffix (but can be confusing)
        long n3 = 200;  
        float f1 = 1;
        float f2 = 1F;  // float suffix
        float f3 = 2f;  // float suffix
        double d1 = 1d; // double suffix
        double d2 = 1D; // double suffix
        // Hex and Octal also work with long
    }
} /* Output
i1: 101111
i2: 101111
i3: 1111111
c: 1111111111111111
b: 1111111
s: 111111111111111
*/
