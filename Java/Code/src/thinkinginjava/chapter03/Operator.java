public class Operator {
    public static void main(String args[]) {
        Integer i1 = new Integer(11);
        Integer i2 = new Integer(11);
        int i3 = 11;
        int i4 = 11;

        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        System.out.println(i3 == i4);
        System.out.println(i1.equals(i3));
        System.out.println(i1 == i3);
        System.out.println(i2 == i3);
        // System.out.println(i3.equals(i4)); 非法：i3是基本类型，没有equals方法
    }
} /* Output:
false
true
true
true
true
true
*/