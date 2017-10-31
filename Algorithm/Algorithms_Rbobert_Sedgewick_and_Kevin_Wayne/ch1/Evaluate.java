package ch1;

import utils.Stack;

/**
 * 算数表达式求值
 * @author bovenson
 */
public class Evaluate {
    /**
     * 判断字符是不是数字(包括点号)
     * @param c 带判断字符
     * @return
     */
    private static boolean isNumber(char c) {
        return c == '.' || ('0' < c && c < '9');
    }

    private static boolean isOpt(char c) {
        return "+-*\\()".contains(String.valueOf(c));
    }

    /**
     * 获取数字
     * @param exp   表达式
     * @param startIndex    开始索引
     * @return  从开始索引的数字
     */
    private static double getNumber(String exp, int startIndex) {
        int endIndex = startIndex + 1;
        while (endIndex < exp.length() && isNumber(exp.charAt(endIndex))) {
            endIndex++;
        }
        return Double.valueOf(exp.substring(startIndex, endIndex));
    }

    /**
     * 计算表达式值
     * @param expression    表达式
     * @return  表达式值
     */
    public static double evaluate(String expression) {
        double res = 0f;
        Stack<Character> ops = new Stack<>();
        Stack<Double> val = new Stack<>();

        for (int i=0; i < expression.length(); ++i) {
            if (isNumber(expression.charAt(i))) {
                val.push(getNumber(expression, i));
            } else if (isOpt(expression.charAt(i))) {
                ops.push(expression.charAt(i));
            }
        }
        return res;
    }

    public static void main(String args[]) {
        evaluate("1+1");

        System.out.println(getNumber("asdf123.32sadf", 5));
    }
}
