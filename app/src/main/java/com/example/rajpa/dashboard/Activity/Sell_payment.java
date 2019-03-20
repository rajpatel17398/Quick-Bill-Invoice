package com.example.rajpa.dashboard.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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

public class Sell_payment extends AppCompatActivity {
    Button b44;
   EditText e100;
    List<DataModel> list;
    ListView sp_list;
    ProgressDialog pd;
    String URL= "https://rajpatel17398.000webhostapp.com/sellpayment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_payment);

        pd=new ProgressDialog(Sell_payment.this);
        pd.setMessage("Loading..");
//        pd.show();
        b44=findViewById(R.id.spbutton);
        sp_list=findViewById(R.id.sp_listview_layout);
        e100=findViewById(R.id.editText_sp);
        list=new ArrayList<>();
        final String inv=getIntent().getStringExtra("invoice");


//        List<DataModel> objects = object1();
//        // and put it into an adapter for the list
//        mAdapter = new Purchase_payment_BaseAdapter(this, objects);
//        pp_list.setAdapter(mAdapter);
        if (TextUtils.isEmpty(inv)) {


        b44.setOnClickListener(new View.OnClickListener() {
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
                                String company_name = object.getString("party_name");
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
                                model.setInvoice(e100.getText().toString());


                                list.add(model);

                                Sell_payment_BaseAdapter adapter1 = new Sell_payment_BaseAdapter(Sell_payment.this, list);
                                sp_list.setAdapter((ListAdapter) adapter1);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Sell_payment.this, error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("invoice",e100.getText().toString());
//
                        return param;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Sell_payment.this);
                queue.add(request);

            }


        });
    }else {
        pd.show();
        e100.setText(inv);

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
                        String company_name = object.getString("party_name");
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

                        Purchase_payment_BaseAdapter adapter = new Purchase_payment_BaseAdapter(Sell_payment.this, list);
                        sp_list.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Sell_payment.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("invoice",e100.getText().toString());
//
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Sell_payment.this);
        queue.add(request);

    }

//                Intent i3=new Intent(Purchase_payment.this, PP_part2.class);
//                startActivity(i3);
}


    }

