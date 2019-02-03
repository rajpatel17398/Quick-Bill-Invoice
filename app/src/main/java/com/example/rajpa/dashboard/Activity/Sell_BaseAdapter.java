package com.example.rajpa.dashboard.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rajpa.dashboard.R;
import java.util.List;

public class Sell_BaseAdapter extends BaseAdapter {
    List<DataModel>list;
    LayoutInflater inflater;
    Context context;

    public Sell_BaseAdapter(Context context, List<DataModel> list) {
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

        convertView=inflater.inflate(R.layout.sell_listview_laout,null);
        TextView txt1=convertView.findViewById(R.id.textView1);
        TextView txt2=convertView.findViewById(R.id.textView2);

        DataModel dm=list.get(position);
        txt1.setText(dm.getQuality()+"/"+dm.getBf()+"/"+dm.getGsm()+"/"+dm.getsize()+"/"+dm.getweight());
        txt2.setText(dm.getPrice());

        return convertView;
    }
}
