package com.example.calculator;

import java.util.ArrayList;
import java.util.Stack;

class noCurlyCalculate {



    public static double Result(String str) {
        ArrayList<String> ops = getOps(str);
        ArrayList<Double> num = getNum(str);
        //System.out.println(num);
        // 先乘除再加减
        for (int i = 0; i < ops.size(); i++) {
            if (ops.get(i).contains("*") || ops.get(i).contains("/")) {
                String op = ops.remove(i);
                if (op.equals("*")) {
                    // 从数字集合取对应和后面一位数字
                    double d1 = num.remove(i);
                    double d2 = num.remove(i);
                    double number = ArithUtil.mul(d1, d2);
                    //再加上
                    num.add(i, number);
                }
                if (op.equals("/")) {
                    double d1 = num.remove(i);
                    double d2 = num.remove(i);
                    double number = ArithUtil.div(d1, d2);
                    num.add(i, number);
                }
                i--;    //刚刚移掉两个,却又刚加上一个新数,所以i要--,因为i++,所以才能取到,如果不加那么虽然貌似正常,但是如果如8*3/3,*/连在一起就报错了;因为连着的两个if;
            }
        }
        //到+-,按顺序的所以就用while()了
        while (ops.size() != 0) {
            String op = ops.remove(0);
            if (op.equals("+")) {
                double d1 = num.remove(0);
                double d2 = num.remove(0);
                double number = ArithUtil.add(d1, d2);
                //System.out.println(number);
                //再加入
                num.add(0, number);
            }
            if (op.equals("-")) {
                double d1 = num.remove(0);
                double d2 = num.remove(0);
                double number = ArithUtil.sub(d1, d2);
                num.add(0, number);
            }
        }
        int size = num.size();
        //System.out.println(num);
        Double[] Num = (Double[])num.toArray(new Double[size]);
        return Num[0];
    }

    /**
     * 获取符号 1.首位 和 * /后面 的-变成@,其他的-不用
     */
    private static ArrayList getNum(String str) {
        // -变成@
        str = change(str);
        ArrayList<Double> list = new ArrayList();

        String[] split = str.split("[\\+\\-\\*/]");
        for (int i = 0; i < split.length; i++) { // @3,5,@4,9,@3
            String s = split[i];
            // 再把@变成-
            if (s.contains("@")) {
                s = '-' + s.substring(1);
            }
            if(s.contains("%")){
                int size = countPercent(s);
                s = s.substring(0, s.length()-size);
                double S = Double.parseDouble(s);
                for(int j = 0; j < size; j++) {
                    S = ArithUtil.mul(0.01, S);
                }
                s = String.valueOf(S);
            }
            //System.out.println(s);
            list.add(Double.parseDouble(s));
        }

        return list;
    }

    private static String change(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // @3+5*-4-9/-3
            if (i == 0 && chars[i] == '-') {
                str = '@' + str.substring(i + 1);
            }
            // @3+5*@4-9/@3
            if (chars[i] == '*' && chars[i + 1] == '-' || chars[i] == '/' && chars[i + 1] == '-') {
                str = str.substring(0, i + 1) + '@' + str.substring(i + 2);
            }
        }
        return str;
    }

    // 获取符号
    private static ArrayList getOps(String str) {
        ArrayList<String> list = new ArrayList();
        // -变@
        str = change(str);
        // @3+5*@4-9/@3
        String[] split = str.split("[0-9\\.@%]");// 表示0-9包括小数和@和%
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("+") || split[i].contains("-") || split[i].contains("*") || split[i].contains("/")) {
                list.add(split[i]);
            }
        }
        return list;
    }

    //统计%的个数
    private static int countPercent(String num){
        int count = 0;
        //将字符串转换为字符数组
        char[] char_array = num.toCharArray();
        for(char s : char_array){
            if(s == '%'){
                count++;
            }
        }
        return count;
    }
}

