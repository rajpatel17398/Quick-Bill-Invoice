package com.example.rajpa.dashboard.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.rajpa.dashboard.R;

public class sell_part2 extends AppCompatActivity {
    Button b9,b11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sell_part2);

        b9=findViewById(R.id.sell_part2_button1);
        b11=findViewById(R.id.sell_part2_button2);
    }
}
