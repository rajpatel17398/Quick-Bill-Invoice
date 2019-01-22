package com.example.rajpa.dashboard.Activity;

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
import com.example.rajpa.dashboard.R;
import com.example.rajpa.dashboard.navigation_dashboard;
import com.example.rajpa.dashboard.registration;

import java.util.HashMap;
import java.util.Map;

public class setting_yourinformation extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5;
    Button b1;
    String URL = "https://rajpatel17398.000webhostapp.com/yourinformation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_yourinformation);

        e1 = findViewById(R.id.your_information_companyname);
        e2 = findViewById(R.id.your_information_companyaddress);
        e3 = findViewById(R.id.your_information_companynumber1);
        e4 = findViewById(R.id.your_information_companynumber2);
        e5 = findViewById(R.id.your_information_companyemail);
        b1 = findViewById(R.id.your_information_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e1.getText().toString();
                String address = e2.getText().toString();
                String number = e3.getText().toString();
                String email = e5.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(setting_yourinformation.this, "Fill Company Name", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(setting_yourinformation.this, "Fill Company Address", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(number)) {
                    Toast.makeText(setting_yourinformation.this, "Fill Company Number", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(setting_yourinformation.this, "Fill Password", Toast.LENGTH_LONG).show();
                } else {
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
                                Toast.makeText(setting_yourinformation.this, "Success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(setting_yourinformation.this, navigation_dashboard.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(setting_yourinformation.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(setting_yourinformation.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("your_information_companyname", e1.getText().toString());
                            param.put("your_information_companyaddress", e2.getText().toString());
                            param.put("your_information_companynumber1", e3.getText().toString());
                            param.put("your_information_companynumber2", e4.getText().toString());
                            param.put("your_information_companyemail", e5.getText().toString());

//
                            return param;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(setting_yourinformation.this);
                    queue.add(request);
                }
            }


        });
    }
}

