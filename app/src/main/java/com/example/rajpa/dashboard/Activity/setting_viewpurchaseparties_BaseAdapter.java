package com.example.rajpa.dashboard.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rajpa.dashboard.R;

import java.util.List;

public class setting_viewpurchaseparties_BaseAdapter extends BaseAdapter {
    List<DataModel>list;
    LayoutInflater inflater;
    Context context;

    public  setting_viewpurchaseparties_BaseAdapter(Context context, List<DataModel> list) {
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

        convertView=inflater.inflate(R.layout.viewpurchaseparties_listview_layout,null);
        TextView txt1=convertView.findViewById(R.id.vp_coname);
        TextView txt2=convertView.findViewById(R.id.vp_pa1name);
        TextView txt3=convertView.findViewById(R.id.vp_pa2name);
        TextView txt4=convertView.findViewById(R.id.vp_mo1);
        TextView txt5=convertView.findViewById(R.id.vp_mo2);
        TextView txt6=convertView.findViewById(R.id.vp_email);
        TextView txt7=convertView.findViewById(R.id.vp_gstno);
        TextView txt8=convertView.findViewById(R.id.vp_panno);
        TextView txt9=convertView.findViewById(R.id.vp_address);
        TextView txt10=convertView.findViewById(R.id.vp_edit);

        DataModel dm=list.get(position);
        txt1.setText(dm.getCompany_name());
        txt2.setText(dm.getName());
        txt3.setText(dm.getName_2());
        txt4.setText(dm.getMobile());
        txt5.setText(dm.getMobile_2());
        txt6.setText(dm.getEmail());
        txt7.setText(dm.getGst_no());
        txt8.setText(dm.getPan_no());
        txt9.setText(dm.getAddress());
        txt10.setText(dm.getEdit());


        return convertView;
    }

}
