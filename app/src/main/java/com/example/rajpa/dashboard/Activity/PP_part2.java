package com.example.rajpa.dashboard.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rajpa.dashboard.R;

public class PP_part2 extends AppCompatActivity {

    TextView b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_part2);
        b1=findViewById(R.id.pp_part2_view);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(PP_part2.this, PP_part2_view.class);
                startActivity(i3);
            }
        });
    }
}
