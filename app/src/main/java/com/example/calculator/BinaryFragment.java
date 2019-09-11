package com.example.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BinaryFragment extends Fragment{
    public BinaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View binary = inflater.inflate(R.layout.binary_fragment, container, false);

        return binary;
    }

}
