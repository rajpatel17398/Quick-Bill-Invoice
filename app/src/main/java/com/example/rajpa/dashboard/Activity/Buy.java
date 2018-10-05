package com.example.rajpa.dashboard.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rajpa.dashboard.R;

public class Buy extends AppCompatActivity {
    GridLayout g1;
    Spinner s1,s2,s3,s4;
    Button b1,b2;
    TextView kg,rs,insurence,gst,total;
    EditText weight,price,insu;
    CheckBox c1;
    RadioGroup rg;
    RadioButton r1,r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        g1=(GridLayout)findViewById(R.id.grid);
        s1=(Spinner)findViewById(R.id.choose_party);
        s2=(Spinner)findViewById(R.id.choose_Quality);
        s3=(Spinner)findViewById(R.id.choose_bf);
        s4=(Spinner) findViewById(R.id.choose_gsm);
        b1=(Button) findViewById(R.id.buton);
        b2=(Button) findViewById(R.id.submit);
        kg=(TextView)findViewById(R.id.kg);
        rs=(TextView)findViewById(R.id.price);
        insurence=(TextView)findViewById(R.id.insurence);
        gst=(TextView)findViewById(R.id.gst);
        total=(TextView) findViewById(R.id.total);
        weight=(EditText)findViewById(R.id.weight);
        price=(EditText)findViewById(R.id.rs);
        insu=(EditText) findViewById(R.id.insu);
        c1=(CheckBox) findViewById(R.id.check);
        rg=(RadioGroup) findViewById(R.id.rg);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton) findViewById(R.id.r2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
    }
}
