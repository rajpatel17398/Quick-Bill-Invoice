package com.example.rajpa.dashboard.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.rajpa.dashboard.R;

import java.util.HashMap;
import java.util.Map;

public class viewpurchaseparties_button extends AppCompatActivity {
    EditText e11,e12,e13,e14,e15,e16,e17,e18,e19;
    Button b5;
    String id;
    String URL= "https://rajpatel17398.000webhostapp.com/update purchase parties.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpurchaseparties_button);
        e11=findViewById(R.id.updatepurchasecompany);
        e12=findViewById(R.id.updatepurchaseparty1);
        e13=findViewById(R.id.updatepurchaseparty2);
        e14=findViewById(R.id.updatepurchasemo1);
        e15=findViewById(R.id.updatepurchasemo2);
        e16=findViewById(R.id.updatepurchaseemail);
        e17=findViewById(R.id.updatepurchasegstno);
        e18=findViewById(R.id.updatepurchasepanno);
        e19=findViewById(R.id.updatepurchaseaddress);
        b5=findViewById(R.id.updatepurchasebutton);

        id=getIntent().getStringExtra("id");
        final String coname=getIntent().getStringExtra("coname");
        e11.setText(coname);
        String pa1name=getIntent().getStringExtra("pa1name");
        e12.setText(pa1name);
        String pa2name=getIntent().getStringExtra("pa2name");
        e13.setText(pa2name);
        String mo=getIntent().getStringExtra("mo");
        e14.setText(mo);
        String mo2=getIntent().getStringExtra("mo2");
        e15.setText(mo2);
        String email=getIntent().getStringExtra("email");
        e16.setText(email);
        String gst=getIntent().getStringExtra("gst");
        e17.setText(gst);
        String pan=getIntent().getStringExtra("pan");
        e18.setText(pan);
        String address=getIntent().getStringExtra("address");
        e19.setText(address);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res",">>>>>>>>"+response);
                        if (response.trim().equals("success")){


                            Toast.makeText(viewpurchaseparties_button.this, "Success", Toast.LENGTH_SHORT).show();

                            Intent intent25=new Intent(viewpurchaseparties_button.this,setting_viewpurchaseparties.class);
                            startActivity(intent25);



                        }else {

                            Toast.makeText(viewpurchaseparties_button.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(viewpurchaseparties_button.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("id",id);
                        param.put("updatepurchasecompany",e11.getText().toString());
                        param.put("updatepurchaseparty1",e12.getText().toString());
                        param.put("updatepurchaseparty2",e13.getText().toString());
                        param.put("updatepurchasemo1",e14.getText().toString());
                        param.put("updatepurchasemo2",e15.getText().toString());
                        param.put("updatepurchaseemail",e16.getText().toString());
                        param.put("updatepurchasegstno", e17.getText().toString());
                        param.put("updatepurchasepanno",e18.getText().toString());
                        param.put("updatepurchaseaddress",e19.getText().toString());
//
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(viewpurchaseparties_button.this);
                queue.add(request);

            }


        });
    }
}
