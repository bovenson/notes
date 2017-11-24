import java.beans.Statement;
import java.sql.Statement;

import javax.net.ssl.ExtendedSSLSession;

public class T {
    public static void main(String args[]) {
        System.out.println(1 + " is " + 1);
        System.out.println(1 + 1 + " is " + 2);
        int a = 3;
        int b, c;
        System.out.println(b = a);
        System.out.println(c = b = a);
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(2);
        System.out.println(i1 + i2);

        // if
        // if (Boolean-expression)
        //     Statement

        // if (Boolean-expression) 
        //     Statement
        // else
        //     Statement
    }
} /** Output
1 is 1
2 is 2
*/