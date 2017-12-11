//: Parcel7.java
// returning an instance of an anonymous inner class

public class Parcel7 {
    public Contents contents() {
        return new Contents() { // insert a class definition
            private int i = 11;
            public int value() { return i; }
        };  // 这种情况下分号是必须的
    }

    public static void main(String args[]) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value());
    }
} /** Output
11
*/