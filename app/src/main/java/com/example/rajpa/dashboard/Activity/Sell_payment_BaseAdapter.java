package com.example.rajpa.dashboard.Activity;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rajpa.dashboard.R;

import java.util.List;

public class  Sell_payment_BaseAdapter extends BaseAdapter {
    private List<DataModel> list;

    LayoutInflater inflater;
    Context context;







    public  Sell_payment_BaseAdapter( Context context,  List<DataModel> list) {
        this.context=context;

        this.list=list;
        inflater=LayoutInflater.from(context);
    }
//    public List<DataModel> getData() {
//        return list;
//    }




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

        convertView=inflater.inflate(R.layout.sp_part2_listview_layout,null);
        TextView txt1=convertView.findViewById(R.id.sp_coname);
        TextView txt2=convertView.findViewById(R.id.sp_quality);
        TextView txt3=convertView.findViewById(R.id.sp_bf);
        TextView txt4=convertView.findViewById(R.id.sp_gsm);
        TextView txt5=convertView.findViewById(R.id.sp_weight);
        TextView txt6=convertView.findViewById(R.id.sp_price);
        TextView txt7=convertView.findViewById(R.id.sp_pricepaid);
        TextView txt8=convertView.findViewById(R.id.sp_pending);
        Button b10=convertView.findViewById(R.id.sp_part2_view);

        final DataModel dm=list.get(position);
        txt1.setText(dm.getCompany_name());
        txt2.setText(dm.getQuality());
        txt3.setText(dm.getBf());
        txt4.setText(dm.getGsm());
        txt5.setText(dm.getweight());
        txt6.setText(dm.getFinal_amount());
        txt7.setText(dm.getAmount_paid());
        txt8.setText(dm.getPending());


        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent20=new Intent(context,SP_part2_view.class);
                intent20.putExtra("id",dm.getId());
                intent20.putExtra("amount_paid",dm.getAmount_paid());
                intent20.putExtra("total",dm.getFinal_amount());
                intent20.putExtra("paid",dm.getAmount_paid());
                intent20.putExtra("pending",dm.getPending());
                intent20.putExtra("cname",dm.getCompany_name());
                intent20.putExtra("invoice",dm.getInvoice());
                context.startActivity(intent20);
            }
        });

        return convertView;
    }
}
