package com.example.rajpa.dashboard.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rajpa.dashboard.R;
import java.util.List;

public class Display_BaseAdapter extends BaseAdapter {
    List<DataModel>list;
    LayoutInflater inflater;
    Context context;

    public Display_BaseAdapter(Context context, List<DataModel> list) {
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

        convertView=inflater.inflate(R.layout.display_listview_layout,null);
        TextView txt1=convertView.findViewById(R.id.di_coname);
//        TextView txt2=convertView.findViewById(R.id.di_buydate);
        TextView txt3=convertView.findViewById(R.id.di_quality);
        TextView txt4=convertView.findViewById(R.id.di_bf);
        TextView txt5=convertView.findViewById(R.id.di_gsm);
        TextView txt6=convertView.findViewById(R.id.di_paid);
        TextView txt7=convertView.findViewById(R.id.di_total);

        DataModel dm=list.get(position);
        txt1.setText(dm.getCompany_name());
//        txt2.setText(dm.getCreated_at());
        txt3.setText(dm.getQuality());
        txt4.setText(dm.getBf());
        txt5.setText(dm.getGsm());
        txt6.setText(dm.getAmount_paid());
        txt7.setText(dm.getFinal_amount());

        return convertView;
    }
}

