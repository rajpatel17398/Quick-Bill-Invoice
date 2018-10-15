package com.example.rajpa.dashboard.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Buy extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridLayout g1;
    static Spinner s1,s2,s3,s4;
    Button b1,b2;
    TextView kg,rs,insurence,gst,total;
    EditText weight,price,insu;
    CheckBox c1;
    RadioGroup rg;
    RadioButton r1,r2;
    double t,w,p;
    List<String>party_list=new ArrayList<>();
    List<String>quality_list=new ArrayList<>();
    List<String>bf_list=new ArrayList<>();
    List<String>gsm_list=new ArrayList<>();
    String choose_quality_URL="https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_Quality=choose_Quality";
    String choose_party_URL="https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_party=choose_party";
    String choose_bf_URL="https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_bf=choose_bf";
            String choose_gsm_URL="https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_gsm=choose_gsm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        g1=(GridLayout)findViewById(R.id.grid);
        s1=(Spinner)findViewById(R.id.choose_party);
        s1.setOnItemSelectedListener(this);
        s2=(Spinner)findViewById(R.id.choose_Quality);
        s2.setOnItemSelectedListener(this);
        s3=(Spinner)findViewById(R.id.choose_bf);
        s4=(Spinner) findViewById(R.id.choose_gsm);
        b1=(Button) findViewById(R.id.buton);
        b2=(Button) findViewById(R.id.submit);
        kg=(TextView)findViewById(R.id.kg);
        rs=(TextView)findViewById(R.id.price);
        insurence=(TextView)findViewById(R.id.insurence);
        gst=(TextView)findViewById(R.id.gst);
        total=(TextView) findViewById(R.id.total);
        weight=(EditText)findViewById(R.id.weight);
        price=(EditText)findViewById(R.id.rs);
        insu=(EditText) findViewById(R.id.insu);
        c1=(CheckBox) findViewById(R.id.check);
        rg=(RadioGroup) findViewById(R.id.rg);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton) findViewById(R.id.r2);

        choose_party_spinner();
        choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();

    /*    price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                w = Double.parseDouble(weight.getText().toString());
                p = Double.parseDouble(price.getText().toString());
                t=w*p;
                total.setText(Double.toString(t));
                Toast.makeText(Buy.this, "hi", Toast.LENGTH_SHORT).show();
            }
        });*/

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
                    ArrayAdapter aa1= new ArrayAdapter(Buy.this,android.R.layout.simple_spinner_item,quality_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Buy.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Buy.this);
        queue1.add(request1);
    }

    private void choose_bf_spinner() {
        StringRequest request2=new StringRequest(Request.Method.GET, choose_bf_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    bf_list.add("Choose_bf");
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String bf=object.getString("bf");

                        bf_list.add(bf);
                    }
                    ArrayAdapter aa2= new ArrayAdapter(Buy.this,android.R.layout.simple_spinner_item,bf_list);
                    aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s3.setAdapter(aa2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Buy.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Buy.this);
        queue1.add(request2);
    }

    private void choose_gsm_spinner() {
        StringRequest request3=new StringRequest(Request.Method.GET, choose_gsm_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    gsm_list.add("Choose_gsm");
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String gsm=object.getString("gsm");

                        gsm_list.add(gsm);
                    }
                    ArrayAdapter aa3= new ArrayAdapter(Buy.this,android.R.layout.simple_spinner_item,gsm_list);
                    aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s4.setAdapter(aa3);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Buy.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Buy.this);
        queue1.add(request3);
    }


    private void choose_party_spinner() {

        StringRequest request=new StringRequest(Request.Method.GET, choose_party_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    party_list.add("Choose_Company");
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);

                        String company_name=object.getString("company_name");
                        party_list.add(company_name);
                     }
                    ArrayAdapter aa = new ArrayAdapter(Buy.this,android.R.layout.simple_spinner_item,party_list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Buy.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue=Volley.newRequestQueue(Buy.this);
        queue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.choose_party)
        {
            Toast.makeText(this, party_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if(spinner.getId() == R.id.choose_Quality)
        {
            Toast.makeText(this, quality_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if(spinner.getId() == R.id.choose_bf)
        {
            Toast.makeText(this, bf_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if(spinner.getId() == R.id.choose_gsm)
        {
            Toast.makeText(this, gsm_list.get(i), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
