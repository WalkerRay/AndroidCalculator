package com.example.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.io.IOException;
import okhttp3.Response;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONObject;


public class RateFragment extends Fragment{
    final String url = "http://haobaoshui.com:8008/exchangerate/v1/rate?scur=usd&tcur=cny";
    private double Rate;

    private Spinner spinner_rate;
    private ArrayAdapter<CharSequence> spinner_adapterrate;

    public RateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rate = inflater.inflate(R.layout.rate_fragment, container, false);


        spinner_rate = (Spinner) rate.findViewById(R.id.spinner_rate);

        spinner_adapterrate = ArrayAdapter.createFromResource(getContext(),R.array.spinner_money,android.R.layout.simple_spinner_item);
        spinner_adapterrate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_rate.setAdapter(spinner_adapterrate);
        spinner_rate.setSelection(0);

        spinner_rate.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_rate.setSelection(i);
                TextView txt = (TextView)rate.findViewById(R.id.changeTip);
                if(spinner_rate.getSelectedItem().toString().contains("￥")){
                    txt.setText("转换为美元($)");
                }else{
                    txt.setText("转换为人民币(￥)");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_rate.setSelection(0);
            }
        });




        Button start = (Button)rate.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(url)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            Rate = parseJSONWithJSONObject(responseData);
                        } catch (IOException e) {
                            System.out.println("OK");
                        }
                    }
                }).start();

                changeRate(rate, Rate);
            }
        });


        return rate;
    }


    private double parseJSONWithJSONObject(String jsonData){
        String rate = new String();
        try{
                JSONObject jsonObject = new JSONObject(jsonData);
                rate = jsonObject.getString("rate");
        }catch (Exception e){
            e.printStackTrace();
        }
        double Rate = Double.parseDouble(rate);
        return Rate;
    }

    public void changeRate(View rate, double Rate){
        EditText txt1 = (EditText)rate.findViewById(R.id.money1);
        TextView txt2 = (TextView) rate.findViewById(R.id.money2);
        if(txt1.getText().toString().length()!=0) {

            System.out.println("Rate:" + Rate);
            String money = (String) spinner_rate.getSelectedItem();
            switch (money) {
                case "人民币(￥)":
                    double cny = Double.parseDouble(txt1.getText().toString());
                    double result = cny / Rate;
                    String Usd = String.format("%.8f", result);
                    txt2.setText(Usd);
                    break;
                case "美元($)":
                    double usd = Double.parseDouble(txt1.getText().toString());
                    result = usd * Rate;
                    String Cny = String.format("%.8f", result);
                    txt2.setText(Cny);
            }
        }
    }


}
