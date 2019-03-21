package com.example.rajpa.dashboard.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.rajpa.dashboard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.rajpa.dashboard.R;

import com.example.rajpa.dashboard.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stock extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static Spinner s2,s3,s4;
    Button b1;
    ProgressDialog pd;
    List<DataModel>list=new ArrayList<>();
    DataModel dm;
    List<String> quality_list=new ArrayList<>();
    List<String>bf_list=new ArrayList<>();
    List<String>gsm_list=new ArrayList<>();
    String choose_quality_URL="https://rajpatel17398.000webhostapp.com/stock.php?choose_Quality=choose_Quality";
    String choose_bf_URL="https://rajpatel17398.000webhostapp.com/stock.php?choose_bf=choose_bf";
    String choose_gsm_URL="https://rajpatel17398.000webhostapp.com/stock.php?choose_gsm=choose_gsm";
        String URL="https://rajpatel17398.000webhostapp.com/stockfinal.php";
    String URL1="https://rajpatel17398.000webhostapp.com/stockfinal1.php";
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        fragmentManager=getSupportFragmentManager();
        s2=(Spinner)findViewById(R.id.quality);
        pd=new ProgressDialog(Stock.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        s2.setOnItemSelectedListener(this);
        s3=(Spinner)findViewById(R.id.bf);
        s3.setOnItemSelectedListener(this);
        s4=(Spinner) findViewById(R.id.gsm);
        b1=(Button) findViewById(R.id.stockbutton);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Purchase"));
        tabLayout.addTab(tabLayout.newTab().setText("Sell"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

       /* choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();*/

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res", ">>>>>>>>" + response);

                        try {
                            JSONArray array = new JSONArray(response);
                            pd.dismiss();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                String weight = object.getString("weight");
                                String price = object.getString("price");

                                Log.e("weight", ">>>>>>>>" + weight);
                                dm = new DataModel();
                                dm.setweight(weight);
                                dm.setPrice(price);

                                list.add(dm);

                                }

                            Stock_fragment1 stock_fragment1 = new Stock_fragment1();
                            Bundle bundle = new Bundle();
                              bundle.putSerializable("valuesArray", (Serializable) list);
                              bundle.putString("12","123");
                           // bundle.putString("hi", "hi" );
                            stock_fragment1.setArguments(bundle);
                            fragmentManager.beginTransaction().replace(R.id.viewPager, stock_fragment1).commit();

//                            Bundle bundle2 = new Bundle();
//                            bundle2.putString("edttext", "From Activity");
//// set Fragmentclass Arguments
//                            Stock_fragment1 fragobj = new Stock_fragment1();
//                            fragobj.setArguments(bundle2);


                            //getSupportFragmentManager().beginTransaction().replace(R.layout.activity_stock, stock_fragment1).commit();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },


                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pd.dismiss();
                                Toast.makeText(Stock.this, error.toString(), Toast.LENGTH_SHORT).show();

                            }

                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("quality", "golden");
                        param.put("bf", "10");
                        param.put("gsm", "20");

//                        param.put("cgst_edit_text",cgst.getText().toString());
//                        param.put("sgst_edit_text",sgst.getText().toString());
//                        param.put("insu",insu.getText().toString());
//                        param.put("total",total.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
                        return param;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(Stock.this);
                queue.add(request);


            }
        });


        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
                    ArrayAdapter aa1= new ArrayAdapter(Stock.this,android.R.layout.simple_spinner_item,quality_list);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(aa1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1=Volley.newRequestQueue(Stock.this);
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
                    ArrayAdapter aa2= new ArrayAdapter(Stock.this,android.R.layout.simple_spinner_item,bf_list);
                    aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s3.setAdapter(aa2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Stock.this, error.toString(), Toast.LENGTH_SHORT).show();
            }

        });
        RequestQueue queue1=Volley.newRequestQueue(Stock.this);
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
                    ArrayAdapter aa3 = new ArrayAdapter(Stock.this, android.R.layout.simple_spinner_item, gsm_list);
                    aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s4.setAdapter(aa3);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Stock.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(Stock.this);
        queue1.add(request3);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

         if(spinner.getId() == R.id.choose_Quality)
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


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}

//    final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
