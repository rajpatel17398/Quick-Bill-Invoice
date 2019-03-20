package com.example.rajpa.dashboard.Activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajpa.dashboard.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1_baseadapter extends BaseAdapter {
    List<DataModel> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    DataModel dm;

    public Fragment1_baseadapter(Context context, List<DataModel> list) {
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }

  /*  public Fragment1_baseadapter(Context context, List<DataModel> list) {

    }*/

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

        if (convertView!=null) {
            convertView = inflater.inflate(R.layout.stock_purchase_listviewlayout, null);
            TextView weight = convertView.findViewById(R.id.stock_purchase_weight);
            TextView price = convertView.findViewById(R.id.stock_purchase_price);

            dm = list.get(position);
            weight.setText(dm.getweight());
            price.setText(dm.getPrice());
            Log.e("Adapter",">>>>>>>"+dm.getweight());

        }else {
            Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();
        }
            return convertView;

    }
}
