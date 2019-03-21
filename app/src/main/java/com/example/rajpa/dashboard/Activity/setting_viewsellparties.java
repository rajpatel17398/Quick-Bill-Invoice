package com.example.rajpa.dashboard.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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

public class setting_viewsellparties extends AppCompatActivity {
    TextView edit;
    ProgressDialog pd;
    List<DataModel> list;
    ListView Setting_list;
    String URL= "https://rajpatel17398.000webhostapp.com/viewsellparties.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_viewsellparties);

        pd=new ProgressDialog(setting_viewsellparties.this);
        pd.setMessage("Loading..");
        pd.show();
        Setting_list=findViewById(R.id.viewsellparty_listview);
        list=new ArrayList<>();


        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Res", ">>>>>>>>" + response);

                try {
                    JSONArray array = new JSONArray(response);
                    pd.dismiss();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String id=object.getString("id");
                        String company_name = object.getString("company_name");
                        String name = object.getString("name");
                        String name_2 = object.getString("name_2");
                        String mobile = object.getString("mobile");
                        String mobile_2 = object.getString("mobile_2");
                        String email = object.getString("email");
                        String gst_no = object.getString("gst_no");
                        String pan_no = object.getString("pan_no");
                        String address = object.getString("address");


                        DataModel model = new DataModel();
                     //   String e = edit.getText().toString();
                        model.setId(id);
                        model.setName(name);
                        model.setName_2(name_2);
                        model.setMobile(mobile);
                        model.setMobile_2(mobile_2);
                        model.setEmail(email);
                        model.setGst_no(gst_no);
                        model.setPan_no(pan_no);
                        model.setAddress(address);
                        model.setCompany_name(company_name);
                  //      model.setEdit(e);

                        list.add(model);

                        setting_viewsellparties_BaseAdapter adapter = new setting_viewsellparties_BaseAdapter(setting_viewsellparties.this, list);
                        Setting_list.setAdapter(adapter);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(setting_viewsellparties.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }

                });

        RequestQueue queue= Volley.newRequestQueue(setting_viewsellparties.this);
        queue.add(request);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(setting_viewsellparties.this,Settings.class);
        startActivity(intent);
        finish();

    }


}

