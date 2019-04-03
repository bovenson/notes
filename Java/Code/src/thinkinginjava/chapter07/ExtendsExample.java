//: ExtendsExample.java
public class ExtendsExample extends A { // 继承
    public static void main(String args[]) {
        System.out.println("ExtendsExample");
    }
}

class A {
    public static void main(String args[]) {
        System.out.println("A");
    }
} /**
Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src/thinkinginjava/chapter07$ javac ExtendsExample.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src/thinkinginjava/chapter07$ java A
A
*/