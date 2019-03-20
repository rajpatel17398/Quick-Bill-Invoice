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

import java.util.HashMap;
import java.util.Map;

public class SP_part2_view extends AppCompatActivity {
    TextView e11,e12,e13;
    EditText e1;
    Button sp_button;
    String id,amount_paid,cname,invoice;
    String URL="https://rajpatel17398.000webhostapp.com/update sell payment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_part2_view);

        e11=findViewById(R.id.spn3);
        e12=findViewById(R.id.spn4);
        e13=findViewById(R.id.spn5);
        e1=findViewById(R.id.sp_view_add);
        sp_button=findViewById(R.id.sp_button1);


        cname=getIntent().getStringExtra("cname");
        id=getIntent().getStringExtra("id");
        amount_paid=getIntent().getStringExtra("amount_paid");

       invoice=getIntent().getStringExtra("invoice");
        String total=getIntent().getStringExtra("total");
        e11.setText(total);
        String paid=getIntent().getStringExtra("paid");
        e12.setText(paid);
        String pending=getIntent().getStringExtra("pending");
        e13.setText(pending);

        sp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res",">>>>>>>>"+response);
                        if (response.trim().equals("success")){


                            Toast.makeText(SP_part2_view.this, "Success", Toast.LENGTH_SHORT).show();

                            Intent intent22=new Intent(SP_part2_view.this,Sell_payment.class);
                            intent22.putExtra("invoice",invoice);
                            startActivity(intent22);
                        }else {

                            Toast.makeText(SP_part2_view.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SP_part2_view.this, error.toString(), Toast.LENGTH_SHORT).show();

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
                RequestQueue queue= Volley.newRequestQueue(SP_part2_view.this);
                queue.add(request);

            }


        });

    }
}

