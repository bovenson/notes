public class Demotion {
    static void f1(char x) { System.out.print("f1(char) "); }
    static void f1(byte x) { System.out.print("f1(byte) "); }
    static void f1(short x) { System.out.print("f1(short) "); }
    static void f1(int x) { System.out.print("f1(int) "); }
    static void f1(long x) { System.out.print("f1(long) "); }
    static void f1(float x) { System.out.print("f1(float) "); }
    static void f1(double x) { System.out.print("f1(double) "); }

    static void f2(char x) { System.out.print("f2(char) "); }
    static void f2(byte x) { System.out.print("f2(byte) "); }
    static void f2(short x) { System.out.print("f2(short) "); }
    static void f2(int x) { System.out.print("f2(int) "); }
    static void f2(long x) { System.out.print("f2(long) "); }
    static void f2(float x) { System.out.print("f2(float) "); }

    static void f3(char x) { System.out.print("f3(char) "); }
    static void f3(byte x) { System.out.print("f3(byte) "); }
    static void f3(short x) { System.out.print("f3(short) "); }
    static void f3(int x) { System.out.print("f3(int) "); }
    static void f3(long x) { System.out.print("f3(long) "); }

    static void f4(char x) { System.out.print("f4(char) "); }
    static void f4(byte x) { System.out.print("f4(byte) "); }
    static void f4(short x) { System.out.print("f4(short) "); }
    static void f4(int x) { System.out.print("f4(int) "); }

    static void f5(char x) { System.out.print("f5(char) "); }
    static void f5(byte x) { System.out.print("f5(byte) "); }
    static void f5(short x) { System.out.print("f5(short) "); }

    static void f6(char x) { System.out.print("f6(char) "); }
    static void f6(byte x) { System.out.print("f6(byte) "); }

    static void f7(char x) { System.out.print("f7(char) "); }

    public static void main(String args[]) {
        System.out.print("Double: ");
        double x = 0;
        // f1(x); f2(x); f3(x); f4(x); f5(x); f6(x); f7(x); error: no suitable method found for \
        // f2(double) f3(double) f4(double) f5double) f6(double)
        f1(x); f2((float)x); f3((long)x); f4((int)x); f5((short)x); f6((byte)x); f7((char)x);
    }
} /** Output 
Double: f1(double) f2(float) f3(long) f4(int) f5(short) f6(byte) f7(char)
*/