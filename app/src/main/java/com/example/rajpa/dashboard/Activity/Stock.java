package com.example.rajpa.dashboard.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
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
import com.example.rajpa.dashboard.R;

import com.example.rajpa.dashboard.R;

import java.util.ArrayList;
import java.util.List;

public class Stock extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static Spinner s2,s3,s4;
    Button b1;
    List<String> quality_list=new ArrayList<>();
    List<String>bf_list=new ArrayList<>();
    List<String>gsm_list=new ArrayList<>();
    String choose_quality_URL="https://rajpatel17398.000webhostapp.com/stock.php?choose_Quality=choose_Quality";
    String choose_bf_URL="https://rajpatel17398.000webhostapp.com/stock.php?choose_bf=choose_bf";
    String choose_gsm_URL="https://rajpatel17398.000webhostapp.com/stock.php?choose_gsm=choose_gsm";
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        s2=(Spinner)findViewById(R.id.quality);
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

        choose_quality_spinner();
        choose_bf_spinner();
        choose_gsm_spinner();

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
