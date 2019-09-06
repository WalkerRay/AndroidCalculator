package com.example.calculator;

import java.util.ArrayList;
import java.util.Stack;


class Calculate {
//    public static void main(String[] args) {
//        String str = "@3+5.0*@4-9/@3";
//        String[] split = str.split("[0-9\\.@]");
//        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[i]);
//        }
//    }

//    public static void main(String[] args) {
////        String str = "3+12/(2-8)+7*((55+1)/2+0.2*(9-1))/2+10";
////        System.out.println(calcDemo(str));
//        double a = 8;
//        double b = 0;
//        System.out.println(ArithUtil.div(a, b));
//    }

    public static String calcDemo(String str) {
        // 加上括号,这样就能当作最终的表达式并判断，最终求出结果
        str = "(" + str + ")";
        // 括弧:先判断左括号和右括号是否相等,再判断括号是否左右是否匹配
        if (!isPiPei(str)) {
            return "括号不匹配，请重新输入";
        }
        if (str.contains("()")) {
//            System.out.println("包含了空的括号，不符合,请检查重新输入");
            return "包含了空的括号，不符合,请检查重新输入";
        }
        /*--------------集合存单字符,用于随时移除和添加--------------*/

        ArrayList<String> str_list = new ArrayList();

        for (int i = 0; i < str.length(); i++) {
            str_list.add(str.charAt(i)+"");
        }
        /*--------------获取所有的单位括号内容--------------*/
        //关键是获取每一次“对称的括号”，并逐个计算，小到大，所以用栈是最好的方法。
        Stack<Integer> stack = new Stack();//存储左括号的位置信息
        for (int i = 0; i < str_list.size(); i++) {
            if (str_list.get(i).equals("(")) {
                stack.add(i);
            }
            if (str_list.get(i).equals(")")) {
                // 移除栈记录的内容角标（所匹配的左括号在原式中的位置）
                int s = stack.pop();

                // 获取式子内容（从相匹配的左括号到右括号之间的式子）
                StringBuilder part = new StringBuilder();
                for (int j = s; j <= i; j++) {
                    part.append(str_list.get(j));
                }

                int partLength = i - s + 1;// 重点(这部把是最容易出错的了,(part.Length()按照的是字符串,看似满足其实不满足list,因为8123488或者再长的小树，在list只占一位,这里用i-s+1才满足所有))

                for (int k = 0; k < partLength; k++) {
                    str_list.remove(s);// 移除这个位置长度次
                }
                // 获取括号中的式子,计算成结果,在放入集合变成新的式子
                String strCalc = part.substring(1, part.length() - 1);//这里1到part.length()-1是去除了左右两边括号的
                String num = noCurlyCalculate.Result(strCalc);
                if(num.equals("false")){
                    return "错误";
                }
                str_list.add(s, num);

                //System.out.println(str_list);
                i = i - partLength + 1; // 移掉后,占一位,++后要能获取"本来i位置"的下一位
                if (i < -1) {// 防越界
                    break;
                }
            }
        }
        int size = str_list.size();
        String[] Num = (String[])str_list.toArray(new String[0]);
        return Num[0];
    }

    /**
     * 判断字符串里的"左和右"括号是否"相等"
     */
    private static boolean isCountEqual(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int right = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left++;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                right++;
            }
        }
        if (left == right) {

        } else {
            System.out.println("左右括号数量不相等");
        }
        return left == right;
    }

    /**
     * 判断字符串里的"左和右"括号是否"匹配"
     */
    private static boolean isPiPei(String str) {
        boolean isPiPei = false;
        //先判断是否相等
        if (!isCountEqual(str)) {
            return false;
        }
        //定义栈记录左
        Stack<Character> stack = new Stack<>();
        char pop;
        //chs存储分割为单个字符的str
        char[] chs = str.toCharArray();
        fo:
        for (int i = 0; i < chs.length; i++) {
            switch (chs[i]) {
                case '(':
                    stack.add(chs[i]);// 放在前面
                    break;
                case ')':
                    pop = stack.pop();// 获取并移除
                    if (pop == '(') {
                        isPiPei = true;
                    } else {
                        isPiPei = false;
                        // 并停止所有
                        break fo;
                    }
                    break;
            }
        }
        return isPiPei;
    }

}
