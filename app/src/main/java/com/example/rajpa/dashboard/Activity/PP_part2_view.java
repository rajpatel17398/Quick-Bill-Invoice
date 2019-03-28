package com.example.rajpa.dashboard.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.R;
import com.example.rajpa.dashboard.navigation_dashboard;

import java.util.HashMap;
import java.util.Map;

public class PP_part2_view extends AppCompatActivity {
                TextView e11,e12,e13;
                EditText e1;
                Button ppbutton;
    String id,amount_paid,cname;
    String URL="https://rajpatel17398.000webhostapp.com/update purchase payment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_part2_view);

        e11=findViewById(R.id.ppn3);
        e12=findViewById(R.id.ppn4);
        e13=findViewById(R.id.ppn5);

        ppbutton=findViewById(R.id.pp_addbutton);

        e1=findViewById(R.id.pp_view_add);
        cname=getIntent().getStringExtra("cname");
        id=getIntent().getStringExtra("id");
        amount_paid=getIntent().getStringExtra("amount_paid");
        String total=getIntent().getStringExtra("total");
        e11.setText(total);
        String paid=getIntent().getStringExtra("paid");
        e12.setText(paid);
        String pending=getIntent().getStringExtra("pending");
        e13.setText(pending);

        ppbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res",">>>>>>>>"+response);
                        if (response.trim().equals("success")){


                            Toast.makeText(PP_part2_view.this, "Success", Toast.LENGTH_SHORT).show();

                            Intent intent22=new Intent(PP_part2_view.this,Purchase_payment.class);
                            intent22.putExtra("cname",cname);
                            startActivity(intent22);
                        }else {

                            Toast.makeText(PP_part2_view.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PP_part2_view.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("id",id);
                        param.put("amount_paid",amount_paid);
                        param.put("amountpaid",e1.getText().toString());

                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(PP_part2_view.this);
                queue.add(request);

            }


        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PP_part2_view.this,Purchase_payment.class);
        startActivity(intent);
        finish();

    }
}
