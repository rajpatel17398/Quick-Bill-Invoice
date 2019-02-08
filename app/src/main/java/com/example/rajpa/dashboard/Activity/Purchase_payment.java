package com.example.rajpa.dashboard.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class Purchase_payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button b1;
    static Spinner s1;
    String spin_company;
    List<String> party_list = new ArrayList<>();
    String choose_party_URL = "https://rajpatel17398.000webhostapp.com/purchasepayment%20fetch.php?choose_party=choose_party";
    String URL= "https://rajpatel17398.000webhostapp.com/purchasepayment fetch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_payment);

        b1=findViewById(R.id.pp_button);
        s1 = (Spinner) findViewById(R.id.pp_spinner);
        s1.setOnItemSelectedListener(this);
        choose_party_spinner();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(Purchase_payment.this, PP_part2.class);
                startActivity(i3);
            }
        });
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
                    ArrayAdapter aa = new ArrayAdapter(Purchase_payment.this,android.R.layout.simple_spinner_item,party_list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Purchase_payment.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue=Volley.newRequestQueue(Purchase_payment.this);
        queue.add(request);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.choose_party)
        {
            spin_company=party_list.get(i);
            Toast.makeText(this, party_list.get(i), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
