/**
 * 内部类
 */
//: Parcell.java

 public class Parcell {
     class Contents {
         private int i = 11;
         public int value() { return i; }
     }

     class Destination {
         private String label;
         Destination(String whereTo) {
             label = whereTo;
         }
         String readLine() { return label; }
     }

     public void ship(String dest) {
         Contents c = new Contents();
         Destination d = new Destination(dest);
         System.out.println(d.readLine());
     }

     public static void main(String[] args) {
         Parcell p = new Parcell();
         p.ship("Tasmania");
     }
 } /** Output
Tasmania
*/