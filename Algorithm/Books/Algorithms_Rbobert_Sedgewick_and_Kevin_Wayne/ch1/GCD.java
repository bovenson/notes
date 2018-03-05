package ch1;
/**
 * GCD
 * @author bovenson
 */
public class GCD {
	public static void main(String args[]) {
        if (args.length == 2) {
            int m = Integer.valueOf(args[0]);
            int n = Integer.valueOf(args[1]);
            System.out.println(gcd(m, n));
            // System.out.println(args[0]);
            // System.out.println(args[1]);
            // System.out.println(args[2]);
        } else {
            System.out.println("Usage: java GCD num1 num2");
        }
	}

	/**
	 * 求最大公约数
	 * m > 0 && n > 0
	 */
	public static int gcd(int m, int n) {
		if (n == 0) {
			return m;
		}
		else {
            m = m % n;
		}
        return gcd(n, m);
	}
}
