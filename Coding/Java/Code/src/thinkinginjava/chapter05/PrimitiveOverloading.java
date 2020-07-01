public class PrimitiveOverloading {
    static void f1(char x) { System.out.print("f1(char) "); }
    static void f1(byte x) { System.out.print("f1(byte) "); }
    static void f1(short x) { System.out.print("f1(short) "); }
    static void f1(int x) { System.out.print("f1(int) "); }
    static void f1(long x) { System.out.print("f1(long) "); }
    static void f1(float x) { System.out.print("f1(float) "); }
    static void f1(double x) { System.out.print("f1(double) "); }

    static void f2(byte x) { System.out.print("f2(byte) "); }
    static void f2(short x) { System.out.print("f2(short) "); }
    static void f2(int x) { System.out.print("f2(int) "); }
    static void f2(long x) { System.out.print("f2(long) "); }
    static void f2(float x) { System.out.print("f2(float) "); }
    static void f2(double x) { System.out.print("f2(double) "); }

    static void f3(short x) { System.out.print("f3(short) "); }
    static void f3(int x) { System.out.print("f3(int) "); }
    static void f3(long x) { System.out.print("f3(long) "); }
    static void f3(float x) { System.out.print("f3(float) "); }
    static void f3(double x) { System.out.print("f3(double) "); }

    static void f4(int x) { System.out.print("f4(int) "); }
    static void f4(long x) { System.out.print("f4(long) "); }
    static void f4(float x) { System.out.print("f4(float) "); }
    static void f4(double x) { System.out.print("f4(double) "); }

    static void f5(long x) { System.out.print("f5(long) "); }
    static void f5(float x) { System.out.print("f5(float) "); }
    static void f5(double x) { System.out.print("f5(double) "); }

    static void f6(float x) { System.out.print("f6(float) "); }
    static void f6(double x) { System.out.print("f6(double) "); }

    static void f7(double x) { System.out.print("f7(double) "); }

    public static void main(String[] args) {
        System.out.print("     5: ");
        f1(5); f2(5); f3(5); f4(5); f5(5); f6(5); f7(5); System.out.println();
        
        char x1 = 'x';
        System.out.print("  char: ");
        f1(x1); f2(x1); f3(x1); f4(x1); f5(x1); f6(x1); f7(x1); System.out.println();

        byte x2 = 0;
        System.out.print("  byte: ");
        f1(x2); f2(x2); f3(x2); f4(x2); f5(x2); f6(x2); f7(x2); System.out.println();

        short x3 = 0;
        System.out.print(" short: ");
        f1(x3); f2(x3); f3(x3); f4(x3); f5(x3); f6(x3); f7(x3); System.out.println();

        int x4 = 0;
        System.out.print("   int: ");
        f1(x4); f2(x4); f3(x4); f4(x4); f5(x4); f6(x4); f7(x4); System.out.println();

        long x5 = 0;
        System.out.print("  long: ");
        f1(x5); f2(x5); f3(x5); f4(x5); f5(x5); f6(x5); f7(x5); System.out.println();

        long x6 = 0;
        System.out.print(" float: ");
        f1(x6); f2(x6); f3(x6); f4(x6); f5(x6); f6(x6); f7(x6); System.out.println();

        double x7 = 0;
        System.out.print("double: ");
        f1(x7); f2(x7); f3(x7); f4(x7); f5(x7); f6(x7); f7(x7); System.out.println();
    }
} /** Output:
     5: f1(int) f2(int) f3(int) f4(int) f5(long) f6(float) f7(double)
  char: f1(char) f2(int) f3(int) f4(int) f5(long) f6(float) f7(double)
  byte: f1(byte) f2(byte) f3(short) f4(int) f5(long) f6(float) f7(double)
 short: f1(short) f2(short) f3(short) f4(int) f5(long) f6(float) f7(double)
   int: f1(int) f2(int) f3(int) f4(int) f5(long) f6(float) f7(double)
  long: f1(long) f2(long) f3(long) f4(long) f5(long) f6(float) f7(double)
 float: f1(long) f2(long) f3(long) f4(long) f5(long) f6(float) f7(double)
double: f1(double) f2(double) f3(double) f4(double) f5(double) f6(double) f7(double)
*/