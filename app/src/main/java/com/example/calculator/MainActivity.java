package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.widget.Toast;
import android.util.Log;
import android.content.res.Configuration;

public class MainActivity extends AppCompatActivity {

    protected String TAG = "buder";
    protected TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            PORTRAIT();
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            LANDSCAPE();
        }

        //横竖屏切换时防止TextView被重置
        mTextView = findViewById(R.id.result);
        //方式一：数据恢复，从K-V中获取销毁之前的存储值
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("hehe");
            Log.e(TAG, "onCreateRestore" + test);
            mTextView.setText(test);
        }


    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            LANDSCAPE();
        }
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            PORTRAIT();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        String change = mTextView.getText().toString();
        outState.putString("hehe", change);

    }

    public void PORTRAIT(){
        Button clear = (Button)findViewById(R.id.Clear);
        Button divide = (Button)findViewById(R.id.Divide);
        Button multiply = (Button)findViewById(R.id.Multiply);
        Button delete = (Button)findViewById(R.id.Delete);
        Button subtract = (Button)findViewById(R.id.Subtract);
        Button plus = (Button)findViewById(R.id.Plus);
        Button equal = (Button)findViewById(R.id.Equal);
        Button percent = (Button)findViewById(R.id.Percent);
        Button dot = (Button)findViewById(R.id.Dot);
        Button btn1 = (Button)findViewById(R.id.num1);
        Button btn2 = (Button)findViewById(R.id.num2);
        Button btn3 = (Button)findViewById(R.id.num3);
        Button btn4 = (Button)findViewById(R.id.num4);
        Button btn5 = (Button)findViewById(R.id.num5);
        Button btn6 = (Button)findViewById(R.id.num6);
        Button btn7 = (Button)findViewById(R.id.num7);
        Button btn8 = (Button)findViewById(R.id.num8);
        Button btn9 = (Button)findViewById(R.id.num9);
        Button btn0 = (Button)findViewById(R.id.num0);
        Button left = (Button)findViewById(R.id.openCurly);
        Button right = (Button)findViewById(R.id.closeCurly);

        btn1.setOnClickListener(new Listener1());
        btn2.setOnClickListener(new Listener2());
        btn3.setOnClickListener(new Listener3());
        btn4.setOnClickListener(new Listener4());
        btn5.setOnClickListener(new Listener5());
        btn6.setOnClickListener(new Listener6());
        btn7.setOnClickListener(new Listener7());
        btn8.setOnClickListener(new Listener8());
        btn9.setOnClickListener(new Listener9());
        btn0.setOnClickListener(new Listener0());
        clear.setOnClickListener(new Clear());
        divide.setOnClickListener(new Divide());
        multiply.setOnClickListener(new Multiply());
        delete.setOnClickListener(new Delete());
        subtract.setOnClickListener(new Subtract());
        plus.setOnClickListener(new Plus());
        percent.setOnClickListener(new Percent());
        dot.setOnClickListener(new Dot());
        left.setOnClickListener(new Left());
        right.setOnClickListener(new Right());
        equal.setOnClickListener(new Equal());
    }

    public void LANDSCAPE(){
        Button clear = (Button)findViewById(R.id.Clear);
        Button divide = (Button)findViewById(R.id.Divide);
        Button multiply = (Button)findViewById(R.id.Multiply);
        Button delete = (Button)findViewById(R.id.Delete);
        Button subtract = (Button)findViewById(R.id.Subtract);
        Button plus = (Button)findViewById(R.id.Plus);
        Button equal = (Button)findViewById(R.id.Equal);
        Button percent = (Button)findViewById(R.id.Percent);
        Button dot = (Button)findViewById(R.id.Dot);
        Button btn1 = (Button)findViewById(R.id.num1);
        Button btn2 = (Button)findViewById(R.id.num2);
        Button btn3 = (Button)findViewById(R.id.num3);
        Button btn4 = (Button)findViewById(R.id.num4);
        Button btn5 = (Button)findViewById(R.id.num5);
        Button btn6 = (Button)findViewById(R.id.num6);
        Button btn7 = (Button)findViewById(R.id.num7);
        Button btn8 = (Button)findViewById(R.id.num8);
        Button btn9 = (Button)findViewById(R.id.num9);
        Button btn0 = (Button)findViewById(R.id.num0);
        Button left = (Button)findViewById(R.id.openCurly);
        Button right = (Button)findViewById(R.id.closeCurly);

        btn1.setOnClickListener(new Listener1());
        btn2.setOnClickListener(new Listener2());
        btn3.setOnClickListener(new Listener3());
        btn4.setOnClickListener(new Listener4());
        btn5.setOnClickListener(new Listener5());
        btn6.setOnClickListener(new Listener6());
        btn7.setOnClickListener(new Listener7());
        btn8.setOnClickListener(new Listener8());
        btn9.setOnClickListener(new Listener9());
        btn0.setOnClickListener(new Listener0());
        clear.setOnClickListener(new Clear());
        divide.setOnClickListener(new Divide());
        multiply.setOnClickListener(new Multiply());
        delete.setOnClickListener(new Delete());
        subtract.setOnClickListener(new Subtract());
        plus.setOnClickListener(new Plus());
        percent.setOnClickListener(new Percent());
        dot.setOnClickListener(new Dot());
        left.setOnClickListener(new Left());
        right.setOnClickListener(new Right());
        equal.setOnClickListener(new Equal());
    }


    class Clear implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            txt.setText("");
        }
    }

    //对开头是0进行约束，比如01就是1，00就是0等
    public boolean zeroLimit(TextView txt){
        String Txt = (String)txt.getText();
        if(Txt.length() == 1 && Txt.equals("0")) {
            return true;
        }
        else if(Txt.length() >= 2){
            String x = Txt.charAt(Txt.length() - 1) + "";
            String y = Txt.charAt(Txt.length() - 2) + "";
            if(x.equals("0") && (y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/"))){
                return true;
            }
        }
        return false;
    }

    //判断乘号是否需要补全，当数字在右侧连接右括号时补全*
    public boolean MulSupJudForNum(TextView txt){
        String Txt = (String)txt.getText();
        if(Txt.length() == 0){
            return false;
        }
        else if(Txt.substring(Txt.length()-1, Txt.length()).equals(")")){
            return true;
        }
        return false;
    }
    //判断乘号是否需要补全，当数字在左侧连接左括号时补全*
    public boolean MulSupJudForLeftCurly(TextView txt){
        String Txt = (String)txt.getText();
        if(Txt.length() == 0){
            return false;
        }
        else{
            String x = Txt.substring(Txt.length()-1, Txt.length());
            if(x.equals("0")||x.equals("1")||x.equals("2")||x.equals("3")||x.equals("4")||x.equals("5")||x.equals("6")||x.equals("7")||x.equals("8")||x.equals("9")) {
                return true;
            }
        }
        return false;
    }

    class Listener1 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"1");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*1");
                }
                else
                    txt.setText(txt.getText() + "1");
            }
        }
    }

    class Listener2 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"2");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*2");
                }
                else
                    txt.setText(txt.getText() + "2");
            }
        }
    }

    class Listener3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"3");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*3");
                }
                else
                    txt.setText(txt.getText() + "3");
            }
        }
    }

    class Listener4 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"4");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*4");
                }
                else
                    txt.setText(txt.getText() + "4");
            }
        }
    }

    class Listener5 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"5");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*5");
                }
                else
                    txt.setText(txt.getText() + "5");
            }
        }
    }

    class Listener6 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"6");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*6");
                }
                else
                    txt.setText(txt.getText() + "6");
            }
        }
    }

    class Listener7 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"7");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*7");
                }
                else
                    txt.setText(txt.getText() + "7");
            }
        }
    }

    class Listener8 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"8");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*8");
                }
                else
                    txt.setText(txt.getText() + "8");
            }
        }
    }

    class Listener9 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"9");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*9");
                }
                else
                    txt.setText(txt.getText() + "9");
            }
        }
    }

    class Listener0 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            if(zeroLimit(txt)){
                txt.setText(Txt.substring(0, Txt.length() - 1)+"0");
            }
            else {
                if(MulSupJudForNum(txt)){
                    txt.setText(txt.getText()+"*0");
                }
                else
                    txt.setText(txt.getText() + "0");
            }
        }
    }

    //写入限制：相同的字符不能连续输入到TextView中（用于加减乘除等符号）用于判断txt最后一位与c是否相同,相同返回false，不同返回true
    public boolean noRepeat(TextView txt, String c) {
        //String x = (((String)txt.getText()).charAt(txt.getText().length()-1))+"";
        String Txt = (String)txt.getText();
        if(Txt.length() != 0) {
            String x = Txt.charAt(Txt.length() - 1) + "";
            if (x.equals(c)) {
                return false;
            } else {
                return true;
            }
        }
        else
            return true;
    }

    class Divide implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            if(txt.getText().length() != 0 && noRepeat(txt, "/") && noRepeat(txt, "(")) {
                txt.setText(txt.getText() + "/");
            }
        }
    }

    class Multiply implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            if(txt.getText().length() != 0 && noRepeat(txt, "*") && noRepeat(txt, "(")) {
                txt.setText(txt.getText() + "*");
            }
        }
    }

    class Delete implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String a = (String)txt.getText();
            if(a.length() != 0) {
                txt.setText(a.substring(0, a.length() - 1));
            }
        }
    }

    class Subtract implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            //如果前面有+号，直接将+变为-
            if(!noRepeat(txt, "+")) {
                txt.setText(Txt.substring(0, Txt.length() - 1) + "-");
            }
            if(noRepeat(txt, "-") && noRepeat(txt, "(")) {
                txt.setText(txt.getText() + "-");
            }
        }
    }

    class Plus implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            if(txt.getText().length() != 0 && noRepeat(txt, "+") && noRepeat(txt, "-") && noRepeat(txt, "(")) {
                txt.setText(txt.getText() + "+");
            }
        }
    }

    //对%和dot的输入进行限制，%的左侧只能是数字或%，不能是加减乘除等符号，%右侧不能直接连数字
    public boolean pdLimit(TextView txt){
        String Txt = (String)txt.getText();
        String x = Txt.charAt(Txt.length()-1)+"";
        if(x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")){
            return false;
        }
        else
            return true;
    }

    class Percent implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            if(txt.getText().length() != 0 && pdLimit(txt) ) {
                txt.setText(txt.getText() + "%");
            }
        }
    }

    //对小数点的限制，不能让一个数有多个小数点
    public boolean dotLimit(TextView txt){
        String Txt = (String)txt.getText();
        String[] TxtSplit = Txt.split("\\.");
        int l = TxtSplit.length - 1;
        if(TxtSplit.length == 1){
            return true;
        }
        if(TxtSplit[l].contains("+") || TxtSplit[l].contains("-") || TxtSplit[l].contains("*") || TxtSplit[l].contains("/")){
            return true;
        }
        return false;
    }

    class Dot implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            //在右括号后点dot补全为*0.
            if(MulSupJudForNum(txt)){
                txt.setText(txt.getText()+"*0.");
            }
            //补全0.，当直接点击dot时补全为0.
            if(txt.getText().length() == 0 || !pdLimit(txt)){
                txt.setText(txt.getText()+"0.");
            }
            //不需补全的dot
            if(txt.getText().length() != 0 && noRepeat(txt, ".") && noRepeat(txt, "%") && pdLimit(txt) && dotLimit(txt)) {
                txt.setText(txt.getText() + ".");
            }
        }
    }

    class Left implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);

            if(MulSupJudForLeftCurly(txt)){
                txt.setText(txt.getText()+"*(");
            }
            else
                txt.setText(txt.getText() + "(");

        }
    }

    class Right implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);

            txt.setText(txt.getText() + ")");

        }
    }

    class Equal implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            String Txt = (String)txt.getText();
            Calculate cal = new Calculate();
            txt.setText(cal.calcDemo(Txt));
        }
    }
}
