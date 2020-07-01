public class ST {
    int a = 0;
    String s = "initial value";
    public ST(int a) {
        this.a = a;
        System.out.println("Constructor w/ int arg only, a = " + a);
    }

    public ST(String s) {
        this.s = s;
        System.out.println("Constructor w/ String arg only, s = " + s);
    }

    public ST(int a, String s) {
        this(a);
        //! this(s); // Can't call two
        System.out.println("String & int arg");
    }

    public ST() {
        this(10, "hi");
        System.out.println("Constructor without args");
        //! this(10, "hi"); // 构造器必须置于最起始处
    }

    public void printST() {
        System.out.println("a=" + a + " s=" + s);
    }

    public void f() {
        int i;
        //! i++; // error: variable i might not have been initialized
    }

    public static void main(String args[]) {
        ST x = new ST();
        x.printST();
    }
} /** Output
Constructor w/ int arg only, a = 10
String & int arg
Constructor without args
a=10 s=initial value
*/