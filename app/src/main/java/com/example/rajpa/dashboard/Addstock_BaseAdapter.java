package com.example.rajpa.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rajpa.dashboard.Activity.DataModel;

import java.util.List;

class Addstock_BaseAdapter extends BaseAdapter {
    List<DataModel> list;
    LayoutInflater inflater;
    Context context;
    public Addstock_BaseAdapter(Context context, List<DataModel> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=inflater.inflate(R.layout.addstock_listview_layout,null);
        TextView txt1=convertView.findViewById(R.id.addstock_info);
        TextView txt2=convertView.findViewById(R.id.addstock_size);
        TextView txt3=convertView.findViewById(R.id.addstock_weight);

        DataModel dm=list.get(position);
        txt1.setText(dm.getQuality()+dm.getBf()+"/"+dm.getGsm());
        txt2.setText(dm.getSize());
        txt3.setText(dm.getWeight());

        return convertView;
    }
}
