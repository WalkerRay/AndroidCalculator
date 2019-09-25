package calculate;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


class noCurlyCalculate extends AppCompatActivity {

    public String Result(String str, String mark) {
        ArrayList<String> ops = getOps(str);
        ArrayList<Double> num = getNum(str, mark);

        String computable = "true";
        // 先乘除再加减
        for (int i = 0; i < ops.size(); i++) {

             if (ops.get(i).contains("^")){
                String op = ops.remove(i);
                if (op.equals("^")) {
                    // 从数字集合取对应和后面一位数字
                    double d1 = num.remove(i);
                    if(num.isEmpty()){
                        return "false";
                    }
                    double d2 = num.remove(i);
                    double number = Double.parseDouble(String.format("%.8f", ArithUtil.pow(d1, d2)));
                    //再加上
                    num.add(i, number);
                }
             }
             if(!ops.isEmpty()) {
                 if (ops.get(i).contains("*") || ops.get(i).contains("/")) {
                     String op = ops.remove(i);
                     if (op.equals("*")) {
                         // 从数字集合取对应和后面一位数字
                         double d1 = num.remove(i);
                         if (num.isEmpty()) {
                             return "false";
                         }
                         double d2 = num.remove(i);
                         double number = ArithUtil.mul(d1, d2);
                         //再加上
                         num.add(i, number);
                     }
                     if (op.equals("/")) {
                         double d1 = num.remove(i);
                         if (num.isEmpty()) {
                             return "false";
                         }
                         double d2 = num.remove(i);
                         if (d2 == 0) {
                             computable = "false";
                             return computable;
                         }
                         double number = ArithUtil.div(d1, d2);
                         num.add(i, number);
                     }
                     i--;    //刚刚移掉两个,却又刚加上一个新数,所以i要--,因为i++,所以才能取到,如果不加那么虽然貌似正常,但是如果如8*3/3,*/连在一起就报错了;因为连着的两个if;
                 }
             }
        }
        //到+-,按顺序的所以就用while()了
        while (ops.size() != 0) {
            String op = ops.remove(0);
            if (op.equals("+")) {
                double d1 = num.remove(0);
                if(num.isEmpty()){
                    return "false";
                }
                double d2 = num.remove(0);
                double number = ArithUtil.add(d1, d2);
                //System.out.println(number);
                //再加入
                num.add(0, number);
            }
            if (op.equals("-")) {
                double d1 = num.remove(0);
                if(num.isEmpty()){
                    return "false";
                }
                double d2 = num.remove(0);
                double number = ArithUtil.sub(d1, d2);
                num.add(0, number);
            }
        }
        int size = num.size();
        Double[] Num = (Double[])num.toArray(new Double[size]);
        String result = Num[0].toString();
        return result;
    }

    /**
     * 获取符号 1.首位 和 * /后面 的-变成@,其他的-不用
     */
    private ArrayList getNum(String str, String mark) {
        // -变成@
        str = change(str);
        ArrayList<Double> list = new ArrayList();

        String[] split = str.split("\\+|\\-|\\*|/|\\^");

        for (int i = 0; i < split.length; i++) { // @3,5,@4,9,@3
            String s = split[i];
            // 再把@变成-,得到负数
            if (s.contains("@")) {
                s = s.replace("@", "-");
            }
            //自然对数
            if(s.contains("e")){
                String S = String.valueOf(Math.E);
                s = s.replace("e", S);
            }
            //π
            if(s.contains("π")){
                String S = String.valueOf(Math.PI);
                s = s.replace("π", S);
            }
            //√
            if(s.contains("√")){
                String S = s.substring(1, s.length());
                if(S.contains("-")){
                    s = "";
                }
                else{
                    double d1 = Double.parseDouble(S);
                    double d2 = 1.0/2.0;
                    double number = ArithUtil.pow(d1, d2);
                    s = String.valueOf(number);
                }
            }
            //得到三角函数运算结果
            if (s.contains("sin")||s.contains("cos")||s.contains("tan")) {
                //Button change = (Button) findViewById(R.id.RAD_DEG);
                //角度制三角函数运算
                if(mark.equals("DEG")) {
                    if (s.contains("sin")) {
                        //分割出数字部分（triNum[1]）
                        String[] triNum = s.split("n");
                        //最终结果number
                        double number = 0;
                        //如果有%(有%的情况是%在sin()之外，所以先算sin结果再添加%运算)
                        if(triNum[1].contains("%")){
                            //计算%数量并提取出不含%的部分
                            int size = countPercent(triNum[1]);
                            triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                            double d = Double.parseDouble(triNum[1]);
                            //计算出sin结果
                            double dRadians = Math.toRadians(d);
                            number = Double.parseDouble(String.format("%.8f", Math.sin(dRadians)));
                            //计算出含%的最终结果
                            for(int j = 0; j < size; j++) {
                                number = ArithUtil.mul(0.01, number);
                            }
                            //判断是否有负号
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                            s = String.valueOf(number);
                        }

                        else {
                            double d = Double.parseDouble(triNum[1]);
                            double dRadians = Math.toRadians(d);
                            number = Double.parseDouble(String.format("%.8f", Math.sin(dRadians)));
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                        }
                        s = String.valueOf(number);
                    }
                    if (s.contains("tan")) {
                        String[] triNum = s.split("n");
                        double number = 0;
                        if(triNum[1].contains("%")){
                            int size = countPercent(triNum[1]);
                            triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                            double d = Double.parseDouble(triNum[1]);
                            double dRadians = Math.toRadians(d);
                            number = Double.parseDouble(String.format("%.8f", Math.tan(dRadians)));
                            for(int j = 0; j < size; j++) {
                                number = ArithUtil.mul(0.01, number);
                            }
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                            s = String.valueOf(number);
                        }
                        else {
                            double d = Double.parseDouble(triNum[1]);
                            double dRadians = Math.toRadians(d);
                            number = Double.parseDouble(String.format("%.8f", Math.tan(dRadians)));
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                        }
                        s = String.valueOf(number);
                    }
                    if (s.contains("cos")) {
                        String[] triNum = s.split("s");
                        double number = 0;
                        if(triNum[1].contains("%")){
                            int size = countPercent(triNum[1]);
                            triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                            double d = Double.parseDouble(triNum[1]);
                            double dRadians = Math.toRadians(d);
                            number = Double.parseDouble(String.format("%.8f", Math.cos(dRadians)));
                            for(int j = 0; j < size; j++) {
                                number = ArithUtil.mul(0.01, number);
                            }
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                            s = String.valueOf(number);
                        }
                        else {
                            double d = Double.parseDouble(triNum[1]);
                            double dRadians = Math.toRadians(d);
                            number = Double.parseDouble(String.format("%.8f", Math.cos(dRadians)));
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                        }
                        s = String.valueOf(number);
                    }
                }
                //弧度制计算
                else{
                    if (s.contains("sin")) {
                        //分割出数字部分（triNum[1]）
                        String[] triNum = s.split("n");
                        //最终结果number
                        double number = 0;
                        //如果有%
                        if(triNum[1].contains("%")){
                            //计算%数量并提取出不含%的部分
                            int size = countPercent(triNum[1]);
                            triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                            double d = Double.parseDouble(triNum[1]);
                            //计算出sin结果
                            number = Double.parseDouble(String.format("%.8f", Math.sin(d)));
                            //计算出含%的最终结果
                            for(int j = 0; j < size; j++) {
                                number = ArithUtil.mul(0.01, number);
                            }
                            //判断是否有负号
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                            s = String.valueOf(number);
                        }
                        //不含%情况
                        else {
                            double d = Double.parseDouble(triNum[1]);
                            number = Double.parseDouble(String.format("%.8f", Math.sin(d)));
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                        }
                        s = String.valueOf(number);
                    }
                    if (s.contains("tan")) {
                        String[] triNum = s.split("n");
                        double number = 0;
                        if(triNum[1].contains("%")){
                            int size = countPercent(triNum[1]);
                            triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                            double d = Double.parseDouble(triNum[1]);
                            number = Double.parseDouble(String.format("%.8f", Math.tan(d)));
                            for(int j = 0; j < size; j++) {
                                number = ArithUtil.mul(0.01, number);
                            }
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                            s = String.valueOf(number);
                        }
                        else {
                            double d = Double.parseDouble(triNum[1]);
                            number = Double.parseDouble(String.format("%.8f", Math.tan(d)));
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                        }
                        s = String.valueOf(number);
                    }
                    if (s.contains("cos")) {
                        String[] triNum = s.split("s");
                        double number = 0;
                        if(triNum[1].contains("%")){
                            int size = countPercent(triNum[1]);
                            triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                            double d = Double.parseDouble(triNum[1]);
                            number = Double.parseDouble(String.format("%.8f", Math.cos(d)));
                            for(int j = 0; j < size; j++) {
                                number = ArithUtil.mul(0.01, number);
                            }
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                            s = String.valueOf(number);
                        }
                        else {
                            double d = Double.parseDouble(triNum[1]);
                            number = Double.parseDouble(String.format("%.8f", Math.cos(d)));
                            if (triNum[0].contains("-")) {
                                number = 0 - number;
                            }
                        }
                        s = String.valueOf(number);
                    }
                }
            }


            //得出ln运算结果
            if (s.contains("ln")) {
                String[] triNum = s.split("n");
                double number = 0;
                if(triNum[1].contains("%")){
                    int size = countPercent(triNum[1]);
                    triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                    double d = Double.parseDouble(triNum[1]);
                    number = Double.parseDouble(String.format("%.8f", Math.log(d)));
                    for(int j = 0; j < size; j++) {
                        number = ArithUtil.mul(0.01, number);
                    }
                    if (triNum[0].contains("-")) {
                        number = 0 - number;
                    }
                    s = String.valueOf(number);
                }
                else {
                    double d = Double.parseDouble(triNum[1]);
                    number = Double.parseDouble(String.format("%.8f", Math.log(d)));
                    if (triNum[0].contains("-")) {
                        number = 0 - number;
                    }
                }
                s = String.valueOf(number);
            }

            //得出ln运算结果
            if (s.contains("log")) {
                String[] triNum = s.split("g");
                double number = 0;
                if(triNum[1].contains("%")){
                    int size = countPercent(triNum[1]);
                    triNum[1] = triNum[1].substring(0, triNum[1].length()-size);
                    double d = Double.parseDouble(triNum[1]);
                    number = Double.parseDouble(String.format("%.8f", Math.log10(d)));
                    for(int j = 0; j < size; j++) {
                        number = ArithUtil.mul(0.01, number);
                    }
                    if (triNum[0].contains("-")) {
                        number = 0 - number;
                    }
                    s = String.valueOf(number);
                }
                else {
                    double d = Double.parseDouble(triNum[1]);
                    number = Double.parseDouble(String.format("%.8f", Math.log10(d)));
                    if (triNum[0].contains("-")) {
                        number = 0 - number;
                    }
                }
                s = String.valueOf(number);
            }

            //得到百分数(只有%的情况)
            if(s.contains("%")){
                int size = countPercent(s);
                s = s.substring(0, s.length()-size);
                double S = Double.parseDouble(s);
                for(int j = 0; j < size; j++) {
                    S = ArithUtil.mul(0.01, S);
                }
                s = String.valueOf(S);
            }

            if(!s.equals("")) {
                list.add(Double.parseDouble(s));
            }
//            else{
//                list.add(0.0);
//            }
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
            if(chars[i] == '*' && i == chars.length-1 || chars[i] == '/' && i == chars.length-1){
                break;
            }
            // @3+5*@4-9/@3
            if (chars[i] == '*' && chars[i + 1] == '-' || chars[i] == '/' && chars[i + 1] == '-' || chars[i] == '^' && chars[i + 1] == '-') {
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
        String[] split = str.split("[0-9|\\.|@|%|sin|cos|tan|e|π|ln|log]");// 表示0-9包括小数和@和%
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("+") || split[i].contains("-") || split[i].contains("*") || split[i].contains("/") || split[i].contains("^")) {
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

