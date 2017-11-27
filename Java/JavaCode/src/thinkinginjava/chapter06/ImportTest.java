package thinkinginjava.chapter06;
//! import thinkinginjava.chapter06.a.A;
//! import thinkinginjava.chapter06.b.A;   // thinkinginjava.chapter06.a.A的 single-type-import 已定义具有相同简名的类型
import thinkinginjava.chapter06.a.*;
import thinkinginjava.chapter06.b.*;


public class ImportTest {
    public static void main(String args[]) {
        //! A a = new A();  // 对A的引用不明确
                        //  thinkinginjava.chapter06.b 中的类 thinkinginjava.chapter06.b.A 和 
                        // thinkinginjava.chapter06.a 中的类 thinkinginjava.chapter06.a.A 都匹配
        thinkinginjava.chapter06.a.A aa = new thinkinginjava.chapter06.a.A();
        thinkinginjava.chapter06.b.A ba = new thinkinginjava.chapter06.b.A();
        System.out.println(aa);
        System.out.println(ba);
    }
} /**Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter06/ImportTest.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter06.ImportTest
thinkinginjava.chapter06.a
thinkinginjava.chapter06.b
*/