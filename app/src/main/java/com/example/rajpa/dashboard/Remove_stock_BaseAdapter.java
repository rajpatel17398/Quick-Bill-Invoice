package com.example.rajpa.dashboard;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.Activity.DataModel;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajpa.dashboard.Activity.setting_viewsellparties;
import com.example.rajpa.dashboard.R;
import java.util.List;
import java.util.Map;

public class  Remove_stock_BaseAdapter extends BaseAdapter {
    List<DataModel>list;
    LayoutInflater inflater;
    Context context;
    String URL="https://rajpatel17398.000webhostapp.com/delete remove stock.php";

    public  Remove_stock_BaseAdapter(Context context, List<DataModel> list) {
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=inflater.inflate(R.layout.removestock_listview_layout,null);
        final TextView txt1=convertView.findViewById(R.id.removestock_listview_weight);
        final Button b100=convertView.findViewById(R.id.removestock_listview_button);


        final DataModel dm=list.get(position);
        txt1.setText(dm.getweight());
        b100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res",">>>>>>>>"+response);
                        if (response.trim().equals("success")){
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                          txt1.setText("");
                          b100.setVisibility(View.GONE);


                        }else {

                            Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String>param=new HashMap<>();
                        // final DataModel dm=list.get(position);
                        param.put("id",dm.getId());
//
                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(context);
                queue.add(request);

            }



        });
        return convertView;
    }
}