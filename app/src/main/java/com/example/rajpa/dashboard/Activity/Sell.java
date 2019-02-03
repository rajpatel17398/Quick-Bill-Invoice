package com.example.rajpa.dashboard.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
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

public class Sell extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridLayout g1;
    static Spinner s1,s2,s3,s4,s5,s6;
    DataModel dataModel;
    String spin_company,spin_quality,spin_bf,spin_gsm,spin_size,spin_weight;
    EditText sellprice;
    Button b1,b2,b3;
    List<String>party_list=new ArrayList<>();
    List<String>quality_list=new ArrayList<>();
    ListView list1;
    List<DataModel>list;
    List<String>bf_list=new ArrayList<>();
    List<String>size_list=new ArrayList<>();
    List<String>weight_list=new ArrayList<>();
    List<String>gsm_list=new ArrayList<>();
    String choose_quality_URL="https://rajpatel17398.000webhostapp.com/sell%20fetch.php?choose_Quality=choose_Quality";
    String choose_party_URL="https://rajpatel17398.000webhostapp.com/sell%20fetch.php?choose_party=choose_party";
    String choose_bf_URL="https://rajpatel17398.000webhostapp.com/sell%20fetch.php?choose_bf=choose_bf";
    String choose_gsm_URL="https://rajpatel17398.000webhostapp.com/sell%20fetch.php?choose_gsm=choose_gsm";
    String choose_size_URL="https://rajpatel17398.000webhostapp.com/sell%20fetch.php?choose_size=choose_size";
    String choose_weight_URL="https://rajpatel17398.000webhostapp.com/sell%20fetch.php?choose_weight=choose_weight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
       // spin_company=findViewById(R.id.pa)
        sellprice=findViewById(R.id.sellprice);
        g1=(GridLayout)findViewById(R.id.grid);
        list1=findViewById(R.id.list1);
        s1=(Spinner)findViewById(R.id.choose_party);
        s1.setOnItemSelectedListener(this);
        s2=(Spinner)findViewById(R.id.choose_Quality);
        s2.setOnItemSelectedListener(this);
        s3=(Spinner)findViewById(R.id.choose_bf);
        s3.setOnItemSelectedListener(this);
        s4=(Spinner) findViewById(R.id.choose_gsm);
        s4.setOnItemSelectedListener(this);
        s5=(Spinner) findViewById(R.id.choose_size);
        s5.setOnItemSelectedListener(this);
        s6=(Spinner) findViewById(R.id.choose_weight);
        s6.setOnItemSelectedListener(this);
//        b1=(Button) findViewById(R.id.sellbutton1);
        b2=(Button) findViewById(R.id.sellbutton2);
        b3=(Button) findViewById(R.id.sellbutton3);

        choose_party_spinner();
        choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();
        choose_size_spinner();
        choose_weight_spinner();
        list=new ArrayList<>();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s=sellprice.getText().toString();
                dataModel=new DataModel();
                dataModel.setBf(spin_bf);
                dataModel.setGsm(spin_gsm);
                dataModel.setQuality(spin_quality);
                dataModel.setPrice(s);
                dataModel.setParty(spin_company);
                dataModel.setsize(spin_size);
                dataModel.setweight(spin_weight);


                list.add(dataModel);
//                list.add(spin_bf);
//                list.add(spin_gsm);

                Sell_BaseAdapter adapter=new Sell_BaseAdapter(Sell.this,list);
                list1.setAdapter(adapter);

                Toast.makeText(Sell.this, spin_quality+"\n"+spin_bf+"\n"+spin_gsm+"\n"+spin_size+"\n"+spin_weight, Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<list.size();i++)
                {
                    Log.e("TotalData",">>>>>>"+list.get(i).getGsm());
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
                    ArrayAdapter aa1= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,quality_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Sell.this);
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
                    ArrayAdapter aa2= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,bf_list);
                    aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s3.setAdapter(aa2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Sell.this);
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
                    ArrayAdapter aa3= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,gsm_list);
                    aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s4.setAdapter(aa3);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Sell.this);
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
                    ArrayAdapter aa = new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,party_list);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue=Volley.newRequestQueue(Sell.this);
        queue.add(request);
    }
    private void choose_size_spinner() {
        StringRequest request1=new StringRequest(Request.Method.GET, choose_size_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    size_list.add("Choose_size");
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String size=object.getString("size");

                        size_list.add(size);
                    }
                    ArrayAdapter aa1= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,size_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s5.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Sell.this);
        queue1.add(request1);
    }
    private void choose_weight_spinner() {
        StringRequest request1=new StringRequest(Request.Method.GET, choose_weight_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    weight_list.add("Choose_weight");
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String weight=object.getString("weight");

                        weight_list.add(weight);
                    }
                    ArrayAdapter aa1= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,weight_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s6.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Sell.this);
        queue1.add(request1);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.choose_party)
        {
            spin_company=party_list.get(i);
            Toast.makeText(this, party_list.get(i), Toast.LENGTH_SHORT).show();
        }
         if(spinner.getId() == R.id.choose_Quality)
        {
            spin_quality=quality_list.get(i);
            Toast.makeText(this, quality_list.get(i), Toast.LENGTH_SHORT).show();
        }
         if(spinner.getId() == R.id.choose_bf)
        {
            spin_bf=bf_list.get(i);
            Toast.makeText(this, bf_list.get(i), Toast.LENGTH_SHORT).show();
        }
        if(spinner.getId() == R.id.choose_gsm)
        {
            spin_gsm=gsm_list.get(i);
            Toast.makeText(this, gsm_list.get(i), Toast.LENGTH_SHORT).show();
        }
        if(spinner.getId() == R.id.choose_size)
        {
            spin_size=size_list.get(i);
            Toast.makeText(this, size_list.get(i), Toast.LENGTH_SHORT).show();
        }
        if(spinner.getId() == R.id.choose_weight)
        {
            spin_weight=weight_list.get(i);
            Toast.makeText(this, weight_list.get(i), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}