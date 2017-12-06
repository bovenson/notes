//: TestParcel.java

interface Destination {
    String readLabel();     // 接口中所有成员自动设置为public
}

interface Contents {
    int value();
}

class Parcel {
    private class PContents implements Contents {
        private int i = 0;
        public int value() { return i++; }
    }

    protected class PDestination implements Destination {
        private String label;
        private PDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() { return label; }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }
}

public class TestParcel {
    public static void main(String args[]) {
        Parcel p = new Parcel();
        Contents c = p.contents();
        Destination d = p.destination("Beijing");
        System.out.println(c.value());
        System.out.println(d.readLabel());
    }
} /** Output
0
Beijing
*/