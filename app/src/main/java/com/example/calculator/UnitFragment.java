package com.example.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import nuitconversion.LengthConversion;
import nuitconversion.VolumeConversion;

import binconversion.BinConversion;

public class UnitFragment extends Fragment{

    private Spinner spinner_lengthfrom;
    private Spinner spinner_lengthto;
    private Spinner spinner_volumefrom;
    private Spinner spinner_volumeto;
    private ArrayAdapter<CharSequence> spinner_adapterlength;
    private ArrayAdapter<CharSequence> spinner_adaptervolume;


    public UnitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View unit = inflater.inflate(R.layout.unit_fragment, container, false);

        spinner_lengthfrom = unit.findViewById(R.id.spinner_lengthfrom);
        spinner_lengthto = unit.findViewById(R.id.spinner_lengthto);
        spinner_volumefrom = unit.findViewById(R.id.spinner_volumefrom);
        spinner_volumeto = unit.findViewById(R.id.spinner_volumeto);

        spinner_adapterlength = ArrayAdapter.createFromResource(getContext(),R.array.spinner_length,android.R.layout.simple_spinner_item);
        spinner_adapterlength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_adaptervolume = ArrayAdapter.createFromResource(getContext(),R.array.spinner_volume,android.R.layout.simple_spinner_item);
        spinner_adaptervolume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_lengthfrom.setAdapter(spinner_adapterlength);
        spinner_lengthfrom.setSelection(0);
        spinner_lengthto.setAdapter(spinner_adapterlength);
        spinner_lengthto.setSelection(0);
        spinner_volumefrom.setAdapter(spinner_adaptervolume);
        spinner_volumefrom.setSelection(0);
        spinner_volumeto.setAdapter(spinner_adaptervolume);
        spinner_volumeto.setSelection(0);

        spinner_lengthfrom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_lengthfrom.setSelection(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_lengthfrom.setSelection(0);
            }
        });
        spinner_lengthto.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_lengthto.setSelection(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_lengthto.setSelection(0);
            }
        });
        spinner_volumefrom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_volumefrom.setSelection(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_volumefrom.setSelection(0);
            }
        });
        spinner_volumeto.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_volumeto.setSelection(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_volumeto.setSelection(0);
            }
        });

        Button startlength = (Button)unit.findViewById(R.id.start1);
        startlength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelength(unit);
            }
        });

        Button startvolume = (Button)unit.findViewById(R.id.start2);
        startvolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changevolume(unit);
            }
        });

        return unit;
    }

    //得出转化后的结果并打印到TextView上
    public void changelength(View unit){
        EditText txt1 = (EditText)unit.findViewById(R.id.length1);
        TextView txt2 = (TextView) unit.findViewById(R.id.length2);
        String from = (String)spinner_lengthfrom.getSelectedItem();
        String to = (String)spinner_lengthto.getSelectedItem();
        LengthConversion lengthC = new LengthConversion();
        switch (from){
            case "mm":
                switch (to){
                    case "mm":
                        txt2.setText(txt1.getText());
                        break;
                    case "cm":
                        String mm = txt1.getText().toString();
                        txt2.setText(lengthC.mmTocm(mm));
                        break;
                    case "m":
                        mm = txt1.getText().toString();
                        txt2.setText(lengthC.mmTom(mm));
                        break;
                    case "km":
                        mm = txt1.getText().toString();
                        txt2.setText(lengthC.mmTokm(mm));
                        break;
                }
                break;
            case "cm":
                switch (to){
                    case "cm":
                        txt2.setText(txt1.getText());
                        break;
                    case "mm":
                        String cm = txt1.getText().toString();
                        txt2.setText(lengthC.cmTomm(cm));
                        break;
                    case "m":
                        cm = txt1.getText().toString();
                        txt2.setText(lengthC.cmTom(cm));
                        break;
                    case "km":
                        cm = txt1.getText().toString();
                        txt2.setText(lengthC.cmTokm(cm));
                        break;
                }
                break;
            case "m":
                switch (to){
                    case "m":
                        txt2.setText(txt1.getText());
                        break;
                    case "cm":
                        String m = txt1.getText().toString();
                        txt2.setText(lengthC.mTocm(m));
                        break;
                    case "mm":
                        m = txt1.getText().toString();
                        txt2.setText(lengthC.mTomm(m));
                        break;
                    case "km":
                        m = txt1.getText().toString();
                        txt2.setText(lengthC.mTokm(m));
                        break;
                }
                break;
            case "km":
                switch (to){
                    case "km":
                        txt2.setText(txt1.getText());
                        break;
                    case "cm":
                        String km = txt1.getText().toString();
                        txt2.setText(lengthC.kmTocm(km));
                        break;
                    case "m":
                        km = txt1.getText().toString();
                        txt2.setText(lengthC.kmTom(km));
                        break;
                    case "mm":
                        km = txt1.getText().toString();
                        txt2.setText(lengthC.kmTomm(km));
                        break;
                }
                break;
        }
    }

    public void changevolume(View unit){
        EditText txt1 = (EditText) unit.findViewById(R.id.volume1);
        TextView txt2 = (TextView) unit.findViewById(R.id.volume2);
        String from = (String)spinner_volumefrom.getSelectedItem();
        String to = (String)spinner_volumeto.getSelectedItem();
        VolumeConversion volumeC = new VolumeConversion();
        switch (from){
            case "ml":
                switch (to){
                    case "ml":
                        txt2.setText(txt1.getText());
                        break;
                    case "L":
                        String ml = txt1.getText().toString();
                        txt2.setText(volumeC.mlTol(ml));
                        break;
                    case "cm^3":
                        txt2.setText(txt1.getText());
                        break;
                    case "m^3":
                        ml = txt1.getText().toString();
                        txt2.setText(volumeC.mlTom3(ml));
                        break;
                }
                break;
            case "L":
                switch (to){
                    case "L":
                        txt2.setText(txt1.getText());
                        break;
                    case "ml":
                        String l = txt1.getText().toString();
                        txt2.setText(volumeC.lToml(l));
                        break;
                    case "cm^3":
                        l = txt1.getText().toString();
                        txt2.setText(volumeC.lTocm3(l));
                        break;
                    case "m^3":
                        l = txt1.getText().toString();
                        txt2.setText(volumeC.lTom3(l));
                        break;
                }
                break;
            case "cm^3":
                switch (to){
                    case "ml":
                        txt2.setText(txt1.getText());
                        break;
                    case "L":
                        String cm = txt1.getText().toString();
                        txt2.setText(volumeC.cm3Tol(cm));
                        break;
                    case "cm^3":
                        txt2.setText(txt1.getText());
                        break;
                    case "m^3":
                        cm = txt1.getText().toString();
                        txt2.setText(volumeC.cm3Tom3(cm));
                        break;
                }
                break;
            case "m^3":
                switch (to){
                    case "m^3":
                        txt2.setText(txt1.getText());
                        break;
                    case "L":
                        String m = txt1.getText().toString();
                        txt2.setText(volumeC.m3Tol(m));
                        break;
                    case "cm^3":
                        m = txt1.getText().toString();
                        txt2.setText(volumeC.m3Tocm3(m));
                        break;
                    case "ml":
                        m = txt1.getText().toString();
                        txt2.setText(volumeC.m3Toml(m));
                        break;
                }
                break;
        }
    }

}
