import java.util.*;

public class SwitchExample {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i=0; i < 100; ++i) {
            int c = rand.nextInt(26) + 'a';
            System.out.print((char)c + ", " + (int)c + ": ");
            switch(c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    System.out.println("vowel");
                    break;
                case 'y':
                case 'w':
                    System.out.println("Sometimes a vowel");
                    break;
                default: System.out.println("consonant");
            }
        }
    }
} /** Output
y, 121: Sometimes a vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
n, 110: consonant
y, 121: Sometimes a vowel
g, 103: consonant
...
*/