//: Parcel8.java

public class Parcel8 {
    public Wrapping wrapping(int x) {
        // Base constructor call
        return new Wrapping(x) {    // pass Constructor argument
            public int value() {
                return super.value() * 47;
            }
        };
    }

    public static void main(String args[]) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
        System.out.println(w.value());
    }
} /** Output
470
*/

class Wrapping {
    private int i;
    public Wrapping(int x) { i = x; }
    public int value() { return i; }
}