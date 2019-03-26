package com.example.rajpa.dashboard.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.Add_stock;
import com.example.rajpa.dashboard.R;
import com.example.rajpa.dashboard.login;
import com.example.rajpa.dashboard.navigation_dashboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buy extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridLayout g1;
    static Spinner s1, s2, s3, s4;
    Button b1, b2, b3;
    ProgressDialog pd;
    String spin_company,spin_quality,spin_bf,spin_gsm;
    TextView kg, rs, insurence, gst, total;
    EditText weight, price, insu, cgst, sgst;
    CheckBox c1;
    RadioGroup rg;
    RadioButton r1, r2;
    double total1, i, cus, tax_amount, total_amount, ins_amount;
    double t, w, p, tt, s, g, q, ttt;
    double ta, total_amount1, tax_amount1, final_amount,ins_amount1,cus1,ta1,ta2,tax_amount2,cus2,ins_amount2;
    List<String> party_list = new ArrayList<>();
    List<String> quality_list = new ArrayList<>();
    List<String> bf_list = new ArrayList<>();
    List<String> gsm_list = new ArrayList<>();
    String choose_quality_URL = "https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_Quality=choose_Quality";
    String choose_party_URL = "https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_party=choose_party";
    String choose_bf_URL = "https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_bf=choose_bf";
    String choose_gsm_URL = "https://rajpatel17398.000webhostapp.com/buy%20fetch.php?choose_gsm=choose_gsm";
    String URL= "https://rajpatel17398.000webhostapp.com/buy.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        pd=new ProgressDialog(Buy.this);
        pd.setMessage("Loading..");

        pd.show();
        g1 = (GridLayout) findViewById(R.id.grid);
        s1 = (Spinner) findViewById(R.id.choose_party);
        s1.setOnItemSelectedListener(this);
        s2 = (Spinner) findViewById(R.id.choose_Quality);
        s2.setOnItemSelectedListener(this);
        s3 = (Spinner) findViewById(R.id.choose_bf);
        s3.setOnItemSelectedListener(this);
        s4 = (Spinner) findViewById(R.id.choose_gsm);
        s4.setOnItemSelectedListener(this);
//        b1=(Button) findViewById(R.id.buybutton1);
//        b2=(Button) findViewById(R.id.buybutton2);
        b3 = (Button) findViewById(R.id.buybutton3);
        kg = (TextView) findViewById(R.id.kg);
        rs = (TextView) findViewById(R.id.price);
        insurence = (TextView) findViewById(R.id.insurence);
        gst = (TextView) findViewById(R.id.gst);
        total = (TextView) findViewById(R.id.total);
        weight = (EditText) findViewById(R.id.weight);
        price = (EditText) findViewById(R.id.rs);

        cgst = (EditText) findViewById(R.id.cgst_edit_text);
        sgst = (EditText) findViewById(R.id.sgst_edit_text);

        rg = (RadioGroup) findViewById(R.id.rg);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        c1 = (CheckBox) findViewById(R.id.check);
        insu = (EditText) findViewById(R.id.insu);

        choose_party_spinner();
        choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();

//        addListnerOnButtonClick();


        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                w = Double.parseDouble(weight.getText().toString());
                p = Double.parseDouble(price.getText().toString());
                ta = w * p;
                total.setText(Double.toString(ta));


                if (c1.isChecked()) {

                    p = Double.parseDouble(price.getText().toString());
                    w = Double.parseDouble(weight.getText().toString());
                    i = Double.parseDouble(insu.getText().toString());

                    ins_amount = (p * w * i) / 100;
                    cus =(p * w)+ins_amount;
                    total.setText(Double.toString(cus));
//                    int selectedId = rg.getCheckedRadioButtonId();
//                    rg = (RadioGroup) findViewById(selectedId);

                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //with
                            w = Double.parseDouble(weight.getText().toString());
                            p = Double.parseDouble(price.getText().toString());
                            i = Double.parseDouble(insu.getText().toString());

                            ins_amount1 = (w * p * i) / 100;
                            cus1 = ins_amount1 + (w * p);

                            s = Double.parseDouble(sgst.getText().toString());
                            g = Double.parseDouble(cgst.getText().toString());
                            tax_amount = cus1 *((s + g) / 100);
                            final_amount = tax_amount + cus1;

                            total.setText(Double.toString(final_amount));
                        }
                    });
                    r2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // without
//
                            w = Double.parseDouble(weight.getText().toString());
                            p = Double.parseDouble(price.getText().toString());
                            i = Double.parseDouble(insu.getText().toString());

                            ins_amount2 = (w * p * i) / 100;
                            cus2 = ins_amount2 + (w * p);

//                               tax_amount1 = 0;
//                               total_amount = tax_amount1 + cus2;

                            total.setText(Double.toString(cus2));
                        }
                    });

//

//                    if (selectedId == -1) {
//                        Toast.makeText(Buy.this, "choose with or without", Toast.LENGTH_SHORT).show();
//                    }
////
////                    selectedId = rg.getCheckedRadioButtonId();
////                    r2 = (RadioButton) findViewById(selectedId);
//
//                    else {
//
//                    }
                }
                else {
//                    int selectedId = rg.getCheckedRadioButtonId();
//                    r1 = (RadioButton) findViewById(selectedId);
                    r1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //with

                            w = Double.parseDouble(weight.getText().toString());
                            p = Double.parseDouble(price.getText().toString());
                            s = Double.parseDouble(sgst.getText().toString());
                            g = Double.parseDouble(cgst.getText().toString());
                            ta1 = w * p;
                            tax_amount2 = ta1 * (s + g) / 100;
                            total_amount1 = tax_amount2 + ta1;
                            total.setText(Double.toString(total_amount1));
                        }
                    });

                    r2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            w = Double.parseDouble(weight.getText().toString());
                            p = Double.parseDouble(price.getText().toString());
                            ta2 = w * p;
                            total.setText(Double.toString(ta2));
                        }
                    });


                }

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
//                final String comany=s1.getText().toString();
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            pd.dismiss();
                            Toast.makeText(Buy.this, "Success", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(Buy.this,Add_stock.class);
                            startActivity(intent);
                        }else {
                            pd.dismiss();
                            Toast.makeText(Buy.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(Buy.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();
                        param.put("choose_party",spin_company);
                        param.put("choose_bf",spin_bf);
                        param.put("choose_gsm",spin_gsm);
                        param.put("choose_Quality",spin_quality);
                        param.put("weight",weight.getText().toString());
                        param.put("rs",price.getText().toString());
                        param.put("cgst_edit_text",cgst.getText().toString());
                        param.put("sgst_edit_text",sgst.getText().toString());
                        param.put("insu",insu.getText().toString());
                        param.put("total",total.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Buy.this);
                queue.add(request);

            }


        });
    }







    private void choose_quality_spinner() {
        StringRequest request1=new StringRequest(Request.Method.GET, choose_quality_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.show();
                try {
                    JSONArray array=new JSONArray(response);
                    quality_list.add("Choose_quality");
                    pd.dismiss();
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
                pd.show();
                try {
                    JSONArray array=new JSONArray(response);
                    bf_list.add("Choose_bf");
                    pd.dismiss();
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
                pd.show();
                try {
                    JSONArray array=new JSONArray(response);
                    gsm_list.add("Choose_gsm");
                    pd.dismiss();
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
            spin_company=party_list.get(i);
            Toast.makeText(this, party_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if(spinner.getId() == R.id.choose_Quality)
        {
            spin_quality=quality_list.get(i);
            Toast.makeText(this, quality_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if(spinner.getId() == R.id.choose_bf)
        {
            spin_bf=bf_list.get(i);
            Toast.makeText(this, bf_list.get(i), Toast.LENGTH_SHORT).show();
        }
        else if(spinner.getId() == R.id.choose_gsm)
        {
            spin_gsm=gsm_list.get(i);
            Toast.makeText(this, gsm_list.get(i), Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
