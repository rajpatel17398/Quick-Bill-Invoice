package com.example.rajpa.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class registration extends AppCompatActivity {
    EditText registration_name,registration_email,registration_password;
    Button registration_button;
    String URL = "https://rajpatel17398.000webhostapp.com/Registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registration_name = (EditText) findViewById(R.id.registration_name);
        registration_email = (EditText) findViewById(R.id.registration_email);
        registration_password = (EditText) findViewById(R.id.registration_password);
        registration_button = (Button) findViewById(R.id.registration_button);
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=registration_name.getText().toString();
                String email=registration_email.getText().toString();
                String pass=registration_password.getText().toString();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(registration.this,"Fill Name",Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(registration.this, "Fill Email", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(registration.this,"Fill Password",Toast.LENGTH_LONG).show();
                }

                else {
//                    Toast.makeText(registration.this, "Submitted", Toast.LENGTH_LONG).show();

//                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//                intent.putExtra("name",name);
//                intent.putExtra("pass",pass);
//                intent.putExtra("email",email);
//                startActivity(intent);
                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {
                                Toast.makeText(registration.this, "Success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(registration.this, navigation_dashboard.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(registration.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(registration.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("registration_name", registration_name.getText().toString());
                            param.put("registration_email", registration_email.getText().toString());
                            param.put("registration_password", registration_password.getText().toString());

//
                            return param;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(registration.this);
                    queue.add(request);
                }
            }


        });
    }

}


