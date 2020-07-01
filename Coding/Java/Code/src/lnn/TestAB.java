package lnn;

public class TestAB extends TestAA {
    static {
        System.out.println("s1");
    }
    {
        System.out.println("s2");
    }
    public TestAB() {
        System.out.println("s3");
    }
    public  static void main(String args[]) {
        new TestAB();
    }
}
