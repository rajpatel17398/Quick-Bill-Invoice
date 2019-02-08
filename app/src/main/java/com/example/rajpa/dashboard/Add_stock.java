package com.example.rajpa.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.Activity.Buy;
import com.example.rajpa.dashboard.Activity.DataModel;
import com.example.rajpa.dashboard.Activity.Sell;
import com.example.rajpa.dashboard.Activity.Sell_BaseAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Add_stock extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static Spinner s1, s2, s3;
    String spin_quality, spin_bf, spin_gsm;
    EditText addstock_size, addstock_weight;
    Button b1, b2;
    DataModel dataModel;
    ListView addstock_list;
    List<DataModel>list;
    List<String> quality_list = new ArrayList<>();
    List<String> bf_list = new ArrayList<>();
    List<String> gsm_list = new ArrayList<>();

    String choose_quality_URL = "https://rajpatel17398.000webhostapp.com/Addstock%20fetch.php?choose_Quality=choose_Quality";
    String choose_bf_URL = "https://rajpatel17398.000webhostapp.com/Addstock%20fetch.php?choose_bf=choose_bf";
    String choose_gsm_URL = "https://rajpatel17398.000webhostapp.com/Addstock%20fetch.php?choose_gsm=choose_gsm";
    String URL = "https://rajpatel17398.000webhostapp.com/Addstock fetch.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        addstock_list=findViewById(R.id.addstock_list);
        s1 = (Spinner) findViewById(R.id.add_stock_qualityspinner);
        s1.setOnItemSelectedListener(this);
        s2 = (Spinner) findViewById(R.id.add_stock_bfspinner);
        s2.setOnItemSelectedListener(this);
        s3 = (Spinner) findViewById(R.id.add_stock_gsmspinner);
        s3.setOnItemSelectedListener(this);
        b1=findViewById(R.id.add_stock_button1);
        b2=findViewById(R.id.add_stock_button2);
        addstock_size=findViewById(R.id.add_stock_size);
        addstock_weight=findViewById(R.id.add_stock_weight);

        choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();
        list=new ArrayList<>();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Size=addstock_size.getText().toString();
                String Weight=addstock_weight.getText().toString();
                dataModel=new DataModel();
                dataModel.setBf(spin_bf);
                dataModel.setGsm(spin_gsm);
                dataModel.setQuality(spin_quality);
                dataModel.setSize(Size);
                dataModel.setWeight(Weight);



                list.add(dataModel);
//                list.add(spin_bf);
//                list.add(spin_gsm);

                Addstock_BaseAdapter adapter1=new Addstock_BaseAdapter(Add_stock.this,list);
                addstock_list.setAdapter(adapter1);

               // Toast.makeText(Add_stock.this, spin_quality+"\n"+spin_bf+"\n"+spin_gsm+"\n"+addstock_size+"\n"+addstock_weight, Toast.LENGTH_SHORT).show();
            }
        });

    }





    private void choose_quality_spinner() {
        StringRequest request1 = new StringRequest(Request.Method.GET, choose_quality_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    quality_list.add("Choose_quality");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String quality = object.getString("quality");

                        quality_list.add(quality);
                    }
                    ArrayAdapter aa1 = new ArrayAdapter(Add_stock.this, android.R.layout.simple_spinner_item, quality_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Add_stock.this);
        queue1.add(request1);
    }

    private void choose_bf_spinner() {
        StringRequest request2 = new StringRequest(Request.Method.GET, choose_bf_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    bf_list.add("Choose_bf");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String bf = object.getString("bf");

                        bf_list.add(bf);
                    }
                    ArrayAdapter aa2 = new ArrayAdapter(Add_stock.this, android.R.layout.simple_spinner_item, bf_list);
                    aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(aa2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Add_stock.this);
        queue1.add(request2);
    }

    private void choose_gsm_spinner() {
        StringRequest request3 = new StringRequest(Request.Method.GET, choose_gsm_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    gsm_list.add("Choose_gsm");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String gsm = object.getString("gsm");

                        gsm_list.add(gsm);
                    }
                    ArrayAdapter aa3 = new ArrayAdapter(Add_stock.this, android.R.layout.simple_spinner_item, gsm_list);
                    aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s3.setAdapter(aa3);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Add_stock.this);
        queue1.add(request3);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        if (spinner.getId() == R.id.add_stock_qualityspinner) {
            spin_quality = quality_list.get(i);
            Toast.makeText(this, quality_list.get(i), Toast.LENGTH_SHORT).show();
        } else if (spinner.getId() == R.id.add_stock_bfspinner) {
            spin_bf = bf_list.get(i);
            Toast.makeText(this, bf_list.get(i), Toast.LENGTH_SHORT).show();
        } else if (spinner.getId() == R.id.add_stock_gsmspinner) {
            spin_gsm = gsm_list.get(i);
            Toast.makeText(this, gsm_list.get(i), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
