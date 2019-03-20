package com.example.rajpa.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.Activity.Buy;

import java.util.HashMap;
import java.util.Map;

public class Add_sell_parties extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1;
    String URL="https://rajpatel17398.000webhostapp.com/add sell_parties.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sell_parties);

        e1=findViewById(R.id.sellparties_companyname);
        e2=findViewById(R.id.sellparties_party1name);
        e3=findViewById(R.id.sellparties_mobile);
        e4=findViewById(R.id.sellparties_party2name);
        e5=findViewById(R.id.sellparties_mobile2);
        e6=findViewById(R.id.sellparties_email);
        e7=findViewById(R.id.sellparties_gstno);
        e8=findViewById(R.id.sellparties_panno);
        e9=findViewById(R.id.sellparties_address);
        b1=findViewById(R.id.sellparties_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
//                            pd.dismiss();
                            Toast.makeText(Add_sell_parties.this, "Success", Toast.LENGTH_SHORT).show();

//                            Intent intent=new Intent(Add_sell_parties.this,Add_stock.class);
//                            startActivity(intent);
                        }else {
//                            pd.dismiss();
                            Toast.makeText(Add_sell_parties.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        pd.dismiss();
                        Toast.makeText(Add_sell_parties.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("sellparties_companyname",e1.getText().toString());
                        param.put("sellparties_party1name",e2.getText().toString());
                        param.put("sellparties_mobile",e3.getText().toString());
                        param.put("sellparties_party2name",e4.getText().toString());
                        param.put("sellparties_mobile2",e5.getText().toString());
                        param.put("sellparties_email",e6.getText().toString());
                        param.put("sellparties_gstno",e7.getText().toString());
                        param.put("sellparties_panno",e8.getText().toString());
                        param.put("sellparties_address",e9.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Add_sell_parties.this);
                queue.add(request);

            }
        });



    }
}
