package com.example.rajpa.dashboard;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.Activity.Buy;
import com.spark.submitbutton.SubmitButton;
//import com.unstoppable.submitbuttonview.SubmitButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    TextView t1;
    ProgressDialog pd;
    String URL= "https://rajpatel17398.000webhostapp.com/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pd=new ProgressDialog(login.this);
        pd.setMessage("Loading..");

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);

        b1=findViewById(R.id.login_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                final String mail=e1.getText().toString();
                final String pass=e2.getText().toString();
//                String name=registration_name.getText().toString();
//                String email=registration_email.getText().toString();
//                String pass=registration_password.getText().toString();

                if (TextUtils.isEmpty(mail)){
                    pd.dismiss();
                    Toast.makeText(login.this,"Fill Name",Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(pass)) {
                    pd.dismiss();
                    Toast.makeText(login.this, "Fill Email", Toast.LENGTH_LONG).show();
                }


                else {


                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {
                                pd.dismiss();
                                Toast.makeText(login.this, "Success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(login.this, navigation_dashboard.class);
                                intent.putExtra("email", mail);
                                startActivity(intent);
                            } else {
                                pd.dismiss();
                                Toast.makeText(login.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("email", mail);
                            param.put("password", pass);
                            return param;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(login.this);
                    queue.add(request);
                }
            }


        });
    }
}