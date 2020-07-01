//: ExtendsA.java

public class ExtendsA extends SuperA {
    ExtendsA(int marker) {
        super(marker);
        System.out.println("Initialize ExtendsA with marker " + marker);
    }

    public static void main(String args[]) {
        ExtendsA ea = new ExtendsA(10);
    }
} /**Output
Initialize SuperA with marker 10
Initialize ExtendsA with marker 10
*/

class SuperA {
    SuperA(int marker) {
        System.out.println("Initialize SuperA with marker " + marker);
    }
}