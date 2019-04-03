class A {}

public class NewVarArgs {
    static void printArray(Object... args) {
        for (Object obj: args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        printArray(new Integer(47), new Float(3.14), new Double(11.11));
        printArray(47, 3.14F, 11.11D);
        printArray("one", "two", "three", "four");
        printArray(new A(), new A(), new A(), new A());

        printArray((Object[])new Integer[]{ 1, 2, 3, 4 });
        printArray();
    }
} /** Output
Args
47 3.14 11.11
47 3.14 11.11
one two three four
A@4aa298b7 A@7d4991ad A@28d93b30 A@1b6d3586
1 2 3 4

*/