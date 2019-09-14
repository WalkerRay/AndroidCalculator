package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.litepal.LitePal;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);


        List<CalculatorModel> all = LitePal.findAll(CalculatorModel.class);
        String[] expRe = new String[all.size()];
        for(int i = 0; i < all.size(); i ++){
            expRe[i] = all.get(i).getExpression()+"="+all.get(i).getResult();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, expRe);
        ListView listView = (ListView)findViewById(R.id.history);
        listView.setAdapter(adapter);
    }
}
