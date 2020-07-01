//: OuterPackage.java
package thinkinginjava.chapter07.outerpackage;

public class OuterPackage {

    public void funPublic() {
        System.out.println("funPublic");
    }

    void funPackage() {
        System.out.println("funPackage");
    }

    protected void funProtected() {
        System.out.println("funProtected");
    }

    private void funPrivate() {
        System.out.println("funPrivate");
    }

    public static void main(String args[]) {
        System.out.println("OuterPackage");
    }
}