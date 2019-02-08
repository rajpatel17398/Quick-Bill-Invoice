package com.example.rajpa.dashboard.Activity;

import android.content.Intent;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.Add_stock;
import com.example.rajpa.dashboard.R;
import com.example.rajpa.dashboard.registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class setting_addqualitybfgsm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static Spinner s1;
    String spin_quality;
    EditText e1, e2, e3;
    Button b1;
    List<String> quality_list = new ArrayList<>();
    String choose_quality_URL = "https://rajpatel17398.000webhostapp.com/addqualitybfgsm%20fetch.php?choose_Quality=choose_Quality";
    String URL = "https://rajpatel17398.000webhostapp.com/addqualitybfgsm.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_addqualitybfgsm);

        s1 = (Spinner) findViewById(R.id.addqualitybfgsm_spinner);
        s1.setOnItemSelectedListener(this);
        e1 = findViewById(R.id.addqualitybfgsm_bf);
        e2 = findViewById(R.id.addqualitybfgsm_gsm);
        e3 = findViewById(R.id.addqualitybfgsm_size);
        b1 = findViewById(R.id.addqualitybfgsm_button);

        choose_quality_spinner();

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String bf = e1.getText().toString();
                String gsm = e2.getText().toString();
                String size = e3.getText().toString();

                if (TextUtils.isEmpty(bf))

                {
                    Toast.makeText(setting_addqualitybfgsm.this, "Fill bf", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(gsm))

                {
                    Toast.makeText(setting_addqualitybfgsm.this, "Fill gsm", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(size))

                {
                    Toast.makeText(setting_addqualitybfgsm.this, "Fill size", Toast.LENGTH_LONG).show();
                } else {
//
                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {
                                Toast.makeText(setting_addqualitybfgsm.this, "Success", Toast.LENGTH_SHORT).show();

//                                Intent intent = new Intent(setting_addqualitybfgsm.this, setting_addqualitybfgsm.class);
//                                startActivity(intent);
                            } else {
                                Toast.makeText(setting_addqualitybfgsm.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(setting_addqualitybfgsm.this, error.toString(), Toast.LENGTH_SHORT).show();

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("choose_Quality", spin_quality);
                            param.put("addqualitybfgsm_bf", e1.getText().toString());
                            param.put("addqualitybfgsm_gsm", e2.getText().toString());
                            param.put("addqualitybfgsm_size ", e3.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                            return param;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(setting_addqualitybfgsm.this);
                    queue.add(request);
                }


            }
        });

    }



    private void choose_quality_spinner() {
        StringRequest request1=new StringRequest(Request.Method.GET, choose_quality_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    quality_list.add("Choose_quality");
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String quality=object.getString("quality");

                        quality_list.add(quality);
                    }
                    ArrayAdapter aa1= new ArrayAdapter(setting_addqualitybfgsm.this,android.R.layout.simple_spinner_item,quality_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(setting_addqualitybfgsm.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(setting_addqualitybfgsm.this);
        queue1.add(request1);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Spinner spinner = (Spinner) adapterView;

        if(spinner.getId() == R.id.choose_Quality)
        {
            spin_quality=quality_list.get(i);
            Toast.makeText(this, quality_list.get(i), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
