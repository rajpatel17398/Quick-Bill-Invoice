package com.example.rajpa.dashboard.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Purchase_payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button b4;
    static Spinner s1;
    List<DataModel>list;
    ListView pp_list;

    ProgressDialog pd;
    String spin_company;
    List<String> party_list = new ArrayList<>();
    String choose_party_URL = "https://rajpatel17398.000webhostapp.com/purchasepayment%20fetch.php?choose_party=choose_party";
    String URL= "https://rajpatel17398.000webhostapp.com/purchasepayment.php";


//    private Purchase_payment_BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_payment);
        pd=new ProgressDialog(Purchase_payment.this);
        pd.setMessage("Loading..");
        pd.show();
        b4=findViewById(R.id.pp_button);
        s1 = (Spinner) findViewById(R.id.pp_spinner);
        s1.setOnItemSelectedListener(this);
        pp_list=findViewById(R.id.pp_listview_layout);
        list=new ArrayList<>();
       final String cname=getIntent().getStringExtra("cname");


//        List<DataModel> objects = object1();
//        // and put it into an adapter for the list
//        mAdapter = new Purchase_payment_BaseAdapter(this, objects);
//        pp_list.setAdapter(mAdapter);
    if (TextUtils.isEmpty(cname)) {

        choose_party_spinner();


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.show();
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("Res", ">>>>>>>>" + response);

                        try {
                            JSONArray array = new JSONArray(response);
                            pd.dismiss();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String id = object.getString("id");
                                String company_name = object.getString("company_name");
                                String bf = object.getString("bf");
                                String gsm = object.getString("gsm");
                                String quality = object.getString("quality");
                                String amount_paid = object.getString("amount_paid");
                                String final_amount = object.getString("final_amount");
                                String weight = object.getString("weight");
                                String pending = object.getString("pending");


                                DataModel model = new DataModel();
                                model.setId(id);
                                model.setCompany_name(company_name);
                                model.setBf(bf);
                                model.setGsm(gsm);
                                model.setQuality(quality);
                                model.setAmount_paid(amount_paid);
                                model.setFinal_amount(final_amount);
                                model.setweight(weight);
                                model.setPending(pending);


                                list.add(model);

                                Purchase_payment_BaseAdapter adapter = new Purchase_payment_BaseAdapter(Purchase_payment.this, list);
                                pp_list.setAdapter(adapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Purchase_payment.this, error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("company_name", spin_company);
//
                        return param;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Purchase_payment.this);
                queue.add(request);

            }


        });
    }else {
        pd.show();
        choose_party_spinner();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Res", ">>>>>>>>" + response);

                try {
                    JSONArray array = new JSONArray(response);
                    pd.dismiss();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String id = object.getString("id");
                        String company_name = object.getString("company_name");
                        String bf = object.getString("bf");
                        String gsm = object.getString("gsm");
                        String quality = object.getString("quality");
                        String amount_paid = object.getString("amount_paid");
                        String final_amount = object.getString("final_amount");
                        String weight = object.getString("weight");
                        String pending = object.getString("pending");


                        DataModel model = new DataModel();
                        model.setId(id);
                        model.setCompany_name(company_name);
                        model.setBf(bf);
                        model.setGsm(gsm);
                        model.setQuality(quality);
                        model.setAmount_paid(amount_paid);
                        model.setFinal_amount(final_amount);
                        model.setweight(weight);
                        model.setPending(pending);


                        list.add(model);

                        Purchase_payment_BaseAdapter adapter = new Purchase_payment_BaseAdapter(Purchase_payment.this, list);
                        pp_list.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Purchase_payment.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("company_name", cname);
//
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Purchase_payment.this);
        queue.add(request);

    }

//                Intent i3=new Intent(Purchase_payment.this, PP_part2.class);
//                startActivity(i3);
            }

//    public List<DataModel> object1() {
//        return null;
//    }
//
//    private void reloadAllData(){
//        // get new modified random data
//        List<DataModel> objects = object1();
//        // update data in our adapter
//        mAdapter.getData().clear();
//        mAdapter.getData().addAll(objects);
//        // fire the event
//        mAdapter.notifyDataSetChanged();
//    }
//
//    /**
//     * helper to show how only changing properties of data
//     * elements also works
//     */
//    private void scrambleChecked(){
//        Random random = new Random();
//        // update data in our adapter, iterate all objects and
//        // resetting the checked option
//        for( DataModel mo : mAdapter.getData())
//        {
////            mo.setChecked(random.nextBoolean());
//        }
//        // fire the event
//        mAdapter.notifyDataSetChanged();
//    }


    private void choose_party_spinner() {

        StringRequest request=new StringRequest(Request.Method.GET, choose_party_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.show();
                try {
                    JSONArray array=new JSONArray(response);
                    party_list.add("Choose_Company");
                    pd.dismiss();
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


            spin_company=party_list.get(i);
            Toast.makeText(this, party_list.get(i), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
