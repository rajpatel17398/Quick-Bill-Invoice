package com.example.rajpa.dashboard.Activity;

import android.app.ProgressDialog;
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

public class viewsellparties_button extends AppCompatActivity {
        EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
        String id;
        Button b1;
    ProgressDialog pd;
    String URL= "https://rajpatel17398.000webhostapp.com/updatesell.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsellparties_button);
        e1=findViewById(R.id.updatesellcompany);
        e2=findViewById(R.id.updatesellparty1);
        e3=findViewById(R.id.updatesellparty2);
        e4=findViewById(R.id.updatesellmo1);
        e5=findViewById(R.id.updatesellmo2);
        e6=findViewById(R.id.updatesellemail);
        pd=new ProgressDialog(viewsellparties_button.this);
        pd.setMessage("Loading..");
        e7=findViewById(R.id.updatesellgstno);
        e8=findViewById(R.id.updatesellpanno);
        e9=findViewById(R.id.updateselladdress);
        b1=findViewById(R.id.updatesellbutton);
        id=getIntent().getStringExtra("id");
        final String cname=getIntent().getStringExtra("coname");
        e1.setText(cname);
        String pname=getIntent().getStringExtra("pa1name");
        e2.setText(pname);
        String p2name=getIntent().getStringExtra("pa2name");
        e3.setText(p2name);
        String mo=getIntent().getStringExtra("mo");
        e4.setText(mo);
        String mo2=getIntent().getStringExtra("mo2");
        e5.setText(mo2);
        String email=getIntent().getStringExtra("email");
        e6.setText(email);
        String gst=getIntent().getStringExtra("gst");
        e7.setText(gst);
        String pan=getIntent().getStringExtra("pan");
        e8.setText(pan);
        String address=getIntent().getStringExtra("address");
        e9.setText(address);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res",">>>>>>>>"+response);
                        if (response.trim().equals("success")){


                            Toast.makeText(viewsellparties_button.this, "Success", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            Intent intent22=new Intent(viewsellparties_button.this,setting_viewsellparties.class);
                            startActivity(intent22);
                        }else {


                            Toast.makeText(viewsellparties_button.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(viewsellparties_button.this, error.toString(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("id",id);
                        param.put("updatesellcompany",e1.getText().toString());
                        param.put("updatesellparty1",e2.getText().toString());
                        param.put("updatesellparty2",e3.getText().toString());
                        param.put("updatesellmo1",e4.getText().toString());
                        param.put("updatesellmo2",e5.getText().toString());
                        param.put("updatesellemail",e6.getText().toString());
                        param.put("updatesellgstno", e7.getText().toString());
                        param.put("updatesellpanno",e8.getText().toString());
                        param.put("updateselladdress",e9.getText().toString());
//                        param.put("cgst_edit_text",cgst.getText().toString());
//                        param.put("sgst_edit_text",sgst.getText().toString());
//                        param.put("insu",insu.getText().toString());
//                        param.put("total",total.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(viewsellparties_button.this);
                queue.add(request);

            }


        });
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(viewsellparties_button.this,setting_viewsellparties.class);
        startActivity(intent);
        finish();

    }
}




