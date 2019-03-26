package com.example.rajpa.dashboard.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
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
import com.example.rajpa.dashboard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sell extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridLayout g1;
    ProgressDialog pd;
    static Spinner s1,s2,s3,s4,s5,s6;
    DataModel dataModel;
    String spin_company,spin_quality,spin_bf,spin_gsm,spin_size,spin_weight;
    EditText sellprice,cgst,sgst;
    String check,invoice;
    Button b1,b2,b3;
    CheckBox c1;
    List<String>party_list=new ArrayList<>();
    List<String>quality_list=new ArrayList<>();
    ListView list1;
    TextView invoice1;
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
    String URL= "https://rajpatel17398.000webhostapp.com/sell.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
       // spin_company=findViewById(R.id.pa)

        pd=new ProgressDialog(Sell.this);
        pd.setMessage("Loading..");

        pd.show();
        invoice1=findViewById(R.id.invoice1);
        sellprice=findViewById(R.id.sellprice);
        cgst=findViewById(R.id.cgst1);
        sgst=findViewById(R.id.sgst1);
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
        c1=findViewById(R.id.sellcheck);


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
                String sg=sgst.getText().toString();
                String cg=cgst.getText().toString();
                dataModel=new DataModel();
                dataModel.setBf(spin_bf);
                dataModel.setGsm(spin_gsm);
                dataModel.setQuality(spin_quality);
                dataModel.setPrice(s);
                dataModel.setParty(spin_company);
                dataModel.setsize(spin_size);
                dataModel.setweight(spin_weight);
                dataModel.setsgst(sg);
                dataModel.setcgst(cg);


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
                pd.show();
                if (c1.isChecked()) {
                     check = "1";
                    Toast.makeText(Sell.this, "Mail is sending", Toast.LENGTH_SHORT).show();
                } else {
                     check = "0";
                }


                for (int i = 0; i < list.size(); i++) {
                    Log.e("TotalData", ">>>>>>" + list.get(i).getGsm());
                    final String p=list.get(i).getParty();
                    final String b=list.get(i).getBf();
                    final String g=list.get(i).getGsm();
                    final String s=list.get(i).getsize();
                    final String w=list.get(i).getweight();
                    final String q=list.get(i).getQuality();
                    final String sp=list.get(i).getPrice();
                    final String cg=list.get(i).getcgst();
                    final String sg=list.get(i).getsgst();
                    StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Res",">>>>>>>>"+response);
                            if (response.trim().equals("not_success")){
                                pd.dismiss();
                                Toast.makeText(Sell.this, "Something Wrong", Toast.LENGTH_SHORT).show();

//                            Intent intent=new Intent(Sell.this,Add_stock.class);
//                            startActivity(intent);
                            }else {
                                pd.dismiss();
                                invoice = response.toString();
                                invoice1.setText(invoice);


                                Toast.makeText(Sell.this, "Success", Toast.LENGTH_SHORT).show();

//                                Intent intent=new Intent(Sell.this,sell_part2.class);
//                                intent.putExtra("invoice",invoice);
//                                startActivity(intent);
//                            }}
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(Sell.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>param=new HashMap<>();
                            param.put("choose_party",p);
                            param.put("choose_bf",b);
                            param.put("choose_gsm",g);
                            param.put("choose_size",s);
                            param.put("choose_weight",w);
                            param.put("choose_Quality",q);
                            param.put("sellprice", sp);
                            param.put("cgst", cg);
                            param.put("sgst", sg);
                            param.put("sellcheck",check);
//                        param.put("cgst_edit_text",cgst.getText().toString());
//                        param.put("sgst_edit_text",sgst.getText().toString());
//                        param.put("insu",insu.getText().toString());
//                        param.put("total",total.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                            return param;
                        }
                    };
                    RequestQueue queue= Volley.newRequestQueue(Sell.this);
                    queue.add(request);

                }


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

        StringRequest request4=new StringRequest(Request.Method.GET, choose_party_URL, new Response.Listener<String>() {
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
                    ArrayAdapter aa4 = new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,party_list);
                    aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s1.setAdapter(aa4);


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
        queue.add(request4);
    }
    private void choose_size_spinner() {
        StringRequest request1=new StringRequest(Request.Method.GET, choose_size_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               pd.show();
                try {
                    JSONArray array=new JSONArray(response);
                    size_list.add("Choose_size");
                    pd.dismiss();
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String size=object.getString("size");

                        size_list.add(size);
                    }
                    ArrayAdapter aa5= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,size_list);
                    aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s5.setAdapter(aa5);


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
               pd.show();
                try {
                    JSONArray array=new JSONArray(response);
                    weight_list.add("Choose_weight");
                    pd.dismiss();
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String weight=object.getString("weight");

                        weight_list.add(weight);
                    }
                    ArrayAdapter aa6= new ArrayAdapter(Sell.this,android.R.layout.simple_spinner_item,weight_list);
                    aa6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s6.setAdapter(aa6);


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