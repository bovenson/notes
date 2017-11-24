class A { }

public class VarArgs {
    static void printArray(Object[] args) {
        for (Object obj: args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        printArray(new Object[] {
            new Integer(47), new Float(3.14), new Double(11.11)
        });
        printArray(new Object[] {"one", "two", "three"});
        printArray(new Object[] {new A(), new A(), new A()});
    }
} /** Output
47 3.14 11.11
one two three
A@4aa298b7 A@7d4991ad A@28d93b30
*/
