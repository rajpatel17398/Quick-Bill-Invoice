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

public class Invoice extends AppCompatActivity {
    TextView edit;
    ProgressDialog pd;
    List<DataModel> list;
    ListView Invoice_list;
    String URL= "https://rajpatel17398.000webhostapp.com/Invoiceee.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Invoice_list=findViewById(R.id.invoice_list);
        pd=new ProgressDialog(Invoice.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
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

                        String company_name = object.getString("party_name");
                        String invoice = object.getString("invoice");
                        String created_at = object.getString("created_at");



                        DataModel model = new DataModel();
                        //   String e = edit.getText().toString();

                        model.setCreated_at(created_at);
                        model.setInvoice(invoice);
                        model.setCompany_name(company_name);
                        //      model.setEdit(e);

                        list.add(model);

                        Invoice_BaseAdapter adapter = new Invoice_BaseAdapter(Invoice.this, list);
                        Invoice_list.setAdapter(adapter);

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
                        Toast.makeText(Invoice.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }

                });

        RequestQueue queue= Volley.newRequestQueue(Invoice.this);
        queue.add(request);
    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(Invoice.this,Settings.class);
//        startActivity(intent);
//        finish();
//
//    }


}
