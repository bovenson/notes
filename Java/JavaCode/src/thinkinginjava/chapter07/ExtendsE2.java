//: ExtendsE2.java

public class ExtendsE2 extends Extends2 {

    public ExtendsE2() {
        System.out.println("class ExtendsE2 initial");
    }

    public ExtendsE2(int marker) {
        System.out.println("class ExtendsE2 initial with marker " + marker);
    }

    public static void main(String args[]) {
        ExtendsE2 ee = new ExtendsE2();
        ExtendsE2 ee2 = new ExtendsE2(1);
    }
} /**Output
class A initial
class ExtendsE2 initial
class A initial
class ExtendsE2 initial with marker 1
*/

class Extends2 {
    Extends2() {
        System.out.println("class A initial");
    }
}