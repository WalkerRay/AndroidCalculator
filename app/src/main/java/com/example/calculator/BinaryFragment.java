package com.example.calculator;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.text.method.DigitsKeyListener;
import android.widget.TextView;

import binconversion.BinConversion;


public class BinaryFragment extends Fragment{

    private Spinner spinner_from;
    private Spinner spinner_to;
    private ArrayAdapter<CharSequence> spinner_adapter;

    public BinaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View binary = inflater.inflate(R.layout.binary_fragment, container, false);


        //连接布局文件中的spinner_from
        spinner_from = binary.findViewById(R.id.spinner_from);
        //连接布局文件中的spinner_to
        spinner_to = binary.findViewById(R.id.spinner_to);

        /*使用适配器方法createFromResource读取数据，第一个参数是context上下文，
        第二个参数是在xml文件中配置数据的string-array的name名字，第三个参数是item
        数据项的格式，使用的是系统默认最简单的。*/
        spinner_adapter = ArrayAdapter.createFromResource(getContext(),R.array.spinner_list,android.R.layout.simple_spinner_item);

        //setDropDownViewResource方法是设置下拉菜单样式使用默认的spinner下拉菜单样式（可以使用自定义的Layout布局）
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //设置适配器，设置首个item为默认的item（2进制）
        spinner_from.setAdapter(spinner_adapter);
        spinner_from.setSelection(0);
        spinner_to.setAdapter(spinner_adapter);
        spinner_to.setSelection(0);

        controlEditText(binary);

        //监听对spinner的选择
        spinner_from.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_from.setSelection(i);

                controlEditText(binary);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            spinner_from.setSelection(0);
            }
        });

        spinner_to.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_to.setSelection(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_to.setSelection(0);
            }
        });

        //设置开始转换的按钮
        Button start = (Button)binary.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change(binary);
            }
        });

        return binary;
    }

    //控制EditText的输入规范
    public void controlEditText(View binary){
        EditText txt = (EditText)binary.findViewById(R.id.number1);
        String jinzhi = (String)spinner_from.getSelectedItem();
        switch(jinzhi){
            case "2进制":
                txt.setKeyListener(DigitsKeyListener.getInstance("01"));
                break;
            case "8进制":
                txt.setKeyListener(DigitsKeyListener.getInstance("01234567"));
                break;
            case "10进制":
                txt.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                break;
            case "16进制":
                txt.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdef"));
                break;
        }
    }

    //得出转化后的结果并打印到TextView上
    public void change(View binary){
        EditText txt1 = (EditText)binary.findViewById(R.id.number1);
        TextView txt2 = (TextView) binary.findViewById(R.id.number2);
        String from = (String)spinner_from.getSelectedItem();
        String to = (String)spinner_to.getSelectedItem();
        BinConversion binC = new BinConversion();
        switch (from){
            case "2进制":
                switch (to){
                    case "2进制":
                        txt2.setText(txt1.getText());
                        break;
                    case "8进制":
                        String two = txt1.getText().toString();
                        txt2.setText(binC.twoToeight(two));
                        break;
                    case "10进制":
                        two = txt1.getText().toString();
                        txt2.setText(binC.twoToten(two));
                        break;
                    case "16进制":
                        two = txt1.getText().toString();
                        txt2.setText(binC.twoTosixteen(two));
                        break;
                }
                break;
            case "8进制":
                switch (to){
                    case "8进制":
                        txt2.setText(txt1.getText());
                        break;
                    case "2进制":
                        String eight = txt1.getText().toString();
                        txt2.setText(binC.eightTotwo(eight));
                        break;
                    case "10进制":
                        eight = txt1.getText().toString();
                        txt2.setText(binC.eightToten(eight));
                        break;
                    case "16进制":
                        eight = txt1.getText().toString();
                        txt2.setText(binC.eightTosixteen(eight));
                        break;
                }
                break;
            case "10进制":
                switch (to){
                    case "10进制":
                        txt2.setText(txt1.getText());
                        break;
                    case "8进制":
                        String ten = txt1.getText().toString();
                        txt2.setText(binC.tenToeight(ten));
                        break;
                    case "2进制":
                        ten = txt1.getText().toString();
                        txt2.setText(binC.tenTotwo(ten));
                        break;
                    case "16进制":
                        ten = txt1.getText().toString();
                        txt2.setText(binC.tenTosixteen(ten));
                        break;
                }
                break;
            case "16进制":
                switch (to){
                    case "16进制":
                        txt2.setText(txt1.getText());
                        break;
                    case "8进制":
                        String six = txt1.getText().toString();
                        txt2.setText(binC.sixteenToeight(six));
                        break;
                    case "10进制":
                        six = txt1.getText().toString();
                        txt2.setText(binC.sixteenToten(six));
                        break;
                    case "2进制":
                        six = txt1.getText().toString();
                        txt2.setText(binC.sixteenTotwo(six));
                        break;
                }
                break;
        }
    }

}
