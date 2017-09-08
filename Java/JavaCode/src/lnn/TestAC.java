package lnn;

public class TestAC {
    public static void main(String args[]) {
        Other o = new Other();
        new TestAC().addOne(o);
        System.out.println(o.i);
    }

    public void addOne(final Other o) {
        o.i++;
    }
}

class Other {
    public int i;
}
