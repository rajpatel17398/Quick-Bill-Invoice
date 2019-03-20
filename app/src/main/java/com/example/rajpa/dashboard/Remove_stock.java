package com.example.rajpa.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.rajpa.dashboard.Activity.Buy;
import com.example.rajpa.dashboard.Activity.DataModel;
import com.example.rajpa.dashboard.Activity.Display;
import com.example.rajpa.dashboard.Activity.Display_BaseAdapter;
import com.example.rajpa.dashboard.Activity.Sell;
import com.example.rajpa.dashboard.Activity.Settings;
import com.example.rajpa.dashboard.Activity.setting_viewsellparties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Remove_stock extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static Spinner s1, s2, s3,s4;
    String spin_quality, spin_bf, spin_gsm,spin_size;
    Button b2;
    ProgressDialog pd;
    List<DataModel>list;
    ListView removestock_list;
    List<String> quality_list = new ArrayList<>();
    List<String> bf_list = new ArrayList<>();
    List<String> gsm_list = new ArrayList<>();
    List<String> size_list = new ArrayList<>();
    String choose_quality_URL="https://rajpatel17398.000webhostapp.com/removestock1.php?choose_Quality=choose_Quality";
    String choose_bf_URL="https://rajpatel17398.000webhostapp.com/removestock1.php?choose_bf=choose_bf";
    String choose_gsm_URL="https://rajpatel17398.000webhostapp.com/removestock1.php?choose_gsm=choose_gsm";
    String choose_size_URL = "https://rajpatel17398.000webhostapp.com/removestock1.php?choose_size=choose_size";
    String URL = "https://rajpatel17398.000webhostapp.com/removestockfetch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_stock);
        s1 = (Spinner) findViewById(R.id.remove_stock_qualityspinner);
        s1.setOnItemSelectedListener(this);
        s2 = (Spinner) findViewById(R.id.remove_stock_bfspinner);
        s2.setOnItemSelectedListener(this);
        s3 = (Spinner) findViewById(R.id.remove_stock_gsmspinner);
        s3.setOnItemSelectedListener(this);
        s4 = (Spinner) findViewById(R.id.remove_stock_sizespinner);
        s4.setOnItemSelectedListener(this);
        removestock_list=findViewById(R.id.removestock_list);
        b2=findViewById(R.id.remove_stock_button1);
        pd=new ProgressDialog(Remove_stock.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();

        choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();
        choose_size_spinner();
        list=new ArrayList<>();


//
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    pd.dismiss();
                        try {
                            JSONArray array=new JSONArray(response);
//                            pd.dismiss();
                            for (int i=0;i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);
                                String id=object.getString("id");
                                String weight =object.getString("weight");
                                Log.e("Res",">>>>>>>>"+weight);
//                                String gsm =object.getString("gsm");
//                                String quality =object.getString("quality");
//                                String amount_paid =object.getString("amount_paid");
//                                String final_amount =object.getString("final_amount");
//                                String created_at=object.getString("created_at");
//                                String company_name=object.getString("company_name");

                                DataModel model=new DataModel();
                                model.setId(id);
                                model.setweight(weight);
//                                model.setGsm(gsm);
//                                model.setQuality(quality);
//                                model.setAmount_paid(amount_paid);
//                                model.setCreated_at(created_at);
//                                model.setFinal_amount(final_amount);
//                                model.setCompany_name(company_name);

                                list.add(model);

                                Remove_stock_BaseAdapter adapter=new Remove_stock_BaseAdapter(Remove_stock.this,list);
                                removestock_list.setAdapter(adapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pd.dismiss();
                                Toast.makeText(Remove_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("quality",spin_quality);
                        param.put("bf",spin_bf);
                        param.put("gsm",spin_gsm);
                        param.put("size",spin_size);

//                        param.put("cgst_edit_text",cgst.getText().toString());
//                        param.put("sgst_edit_text",sgst.getText().toString());
//                        param.put("insu",insu.getText().toString());
//                        param.put("total",total.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Remove_stock.this);
                queue.add(request);

            }






        });
    }

    private void choose_quality_spinner() {
        StringRequest request1 = new StringRequest(Request.Method.GET, choose_quality_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONArray array = new JSONArray(response);
                    quality_list.add("Choose_quality");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String quality = object.getString("quality");

                        quality_list.add(quality);
                    }
                    ArrayAdapter aa1 = new ArrayAdapter(Remove_stock.this, android.R.layout.simple_spinner_item, quality_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Remove_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Remove_stock.this);
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
                    ArrayAdapter aa2 = new ArrayAdapter(Remove_stock.this, android.R.layout.simple_spinner_item, bf_list);
                    aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(aa2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Remove_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Remove_stock.this);
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
                    ArrayAdapter aa3 = new ArrayAdapter(Remove_stock.this, android.R.layout.simple_spinner_item, gsm_list);
                    aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s3.setAdapter(aa3);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Remove_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Remove_stock.this);
        queue1.add(request3);
    }
    private void choose_size_spinner() {
        StringRequest request4 = new StringRequest(Request.Method.GET, choose_size_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    size_list.add("Choose_size");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String size = object.getString("size");

                        size_list.add(size);
                    }
                    ArrayAdapter aa4 = new ArrayAdapter(Remove_stock.this, android.R.layout.simple_spinner_item, size_list);
                    aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s4.setAdapter(aa4);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Remove_stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Remove_stock.this);
        queue1.add(request4);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        if (spinner.getId() == R.id.remove_stock_qualityspinner) {
            spin_quality = quality_list.get(i);
            Toast.makeText(this, quality_list.get(i), Toast.LENGTH_SHORT).show();
        } else if (spinner.getId() == R.id.remove_stock_bfspinner) {
            spin_bf = bf_list.get(i);
            Toast.makeText(this, bf_list.get(i), Toast.LENGTH_SHORT).show();
        } else if (spinner.getId() == R.id.remove_stock_gsmspinner) {
            spin_gsm = gsm_list.get(i);
            Toast.makeText(this, gsm_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.remove_stock_sizespinner) {
            spin_size = size_list.get(i);
            Toast.makeText(this, size_list.get(i), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(Remove_stock.this,navigation_dashboard.class);
//        startActivity(intent);
//        finish();
//
//    }
}
