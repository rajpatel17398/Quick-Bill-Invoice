package com.example.rajpa.dashboard.Activity;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajpa.dashboard.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  setting_addqualitybfgsm_BaseAdapter extends BaseAdapter {
    List<DataModel> list;
    LayoutInflater inflater;
    Context context;

    public setting_addqualitybfgsm_BaseAdapter(Context context, List<DataModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.addqualitybfgsm_listview_layout, null);
        TextView txt1 = convertView.findViewById(R.id.addbfgsmquality_quality);
        TextView txt2 = convertView.findViewById(R.id.addbfgsmquality_bf);
        TextView txt3 = convertView.findViewById(R.id.addbfgsmquality_gsm);
        TextView txt4 = convertView.findViewById(R.id.addbfgsmquality_size);

        //  TextView txt10=convertView.findViewById(R.id.vs_edit);

        final DataModel dm = list.get(position);
        txt1.setText(dm.getQuality());
        txt2.setText(dm.getBf());
        txt3.setText(dm.getGsm());
        txt4.setText(dm.getsize());


        return convertView;
    }
}

