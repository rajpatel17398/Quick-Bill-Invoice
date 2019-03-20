package com.example.rajpa.dashboard;

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

import java.util.HashMap;
import java.util.Map;

public class Add_purchase_parties extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b3;
    String URL="https://rajpatel17398.000webhostapp.com/add purchase_parties.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase_parties);

        e1=findViewById(R.id.purchaseparties_companyname);
        e2=findViewById(R.id.purchaseparties_party1name);
        e3=findViewById(R.id.purchaseparties_mobile);
        e4=findViewById(R.id.purchaseparties_party2name);
        e5=findViewById(R.id.purchaseparties_mobile2);
        e6=findViewById(R.id.purchaseparties_email);
        e7=findViewById(R.id.purchaseparties_gstno);
        e8=findViewById(R.id.purchaseparties_panno);
        e9=findViewById(R.id.purchaseparties_address);
        b3=findViewById(R.id.purchaseparties_button);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
//                            pd.dismiss();
                            Toast.makeText(Add_purchase_parties.this, "Success", Toast.LENGTH_SHORT).show();

//                            Intent intent=new Intent(Add_sell_parties.this,Add_stock.class);
//                            startActivity(intent);
                        }else {
//                            pd.dismiss();
                            Toast.makeText(Add_purchase_parties.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        pd.dismiss();
                        Toast.makeText(Add_purchase_parties.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("purchaseparties_companyname",e1.getText().toString());
                        param.put("purchaseparties_party1name",e2.getText().toString());
                        param.put("purchaseparties_mobile",e3.getText().toString());
                        param.put("purchaseparties_party2name",e4.getText().toString());
                        param.put("purchaseparties_mobile2",e5.getText().toString());
                        param.put("purchaseparties_email",e6.getText().toString());
                        param.put("purchaseparties_gstno",e7.getText().toString());
                        param.put("purchaseparties_panno",e8.getText().toString());
                        param.put("purchaseparties_address",e9.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Add_purchase_parties.this);
                queue.add(request);

            }
        });



    }
}
