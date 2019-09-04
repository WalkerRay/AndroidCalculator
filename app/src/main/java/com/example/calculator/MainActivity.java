package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clear = (Button)findViewById(R.id.Clear);
        Button divide = (Button)findViewById(R.id.Delete);
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

        btn1.setOnClickListener(new Listener1());
        btn2.setOnClickListener(new Listener2());
        clear.setOnClickListener(new Clear());
    }

    class Clear implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            txt.setText("");
        }
    }

    class Listener1 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            txt.setText(txt.getText()+"1");
        }
    }

    class Listener2 implements View.OnClickListener {
        @Override
        public void onClick(View v){
            TextView txt = (TextView) findViewById(R.id.result);
            txt.setText(txt.getText()+"2");
        }
    }
}
