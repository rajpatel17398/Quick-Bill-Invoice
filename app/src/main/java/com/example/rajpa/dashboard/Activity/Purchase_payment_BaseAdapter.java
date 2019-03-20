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

public class Purchase_payment_BaseAdapter extends BaseAdapter {
   private List<DataModel> list;

   LayoutInflater inflater;
    Context context;







    public Purchase_payment_BaseAdapter( Context context,  List<DataModel> list) {
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

        convertView=inflater.inflate(R.layout.pp_part2_listview_layout,null);
        TextView txt1=convertView.findViewById(R.id.pp_coname);
        TextView txt2=convertView.findViewById(R.id.pp_quality);
        TextView txt3=convertView.findViewById(R.id.pp_bf);
        TextView txt4=convertView.findViewById(R.id.pp_gsm);
        TextView txt5=convertView.findViewById(R.id.pp_weight);
        TextView txt6=convertView.findViewById(R.id.pp_price);
        TextView txt7=convertView.findViewById(R.id.pp_pricepaid);
        TextView txt8=convertView.findViewById(R.id.pp_pending);
        Button b10=convertView.findViewById(R.id.pp_part2_button);

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

                Intent intent=new Intent(context, PP_part2_view.class);
                intent.putExtra("id",dm.getId());
                intent.putExtra("amount_paid",dm.getAmount_paid());
                        intent.putExtra("total",dm.getFinal_amount());
                        intent.putExtra("paid",dm.getAmount_paid());
                        intent.putExtra("pending",dm.getPending());
                        intent.putExtra("cname",dm.getCompany_name());
//                        intent.putExtra("pa2name",dm.getName_2());
//                        intent.putExtra("mo",dm.getMobile());
//                        intent.putExtra("mo2",dm.getMobile_2());
//                        intent.putExtra("email",dm.getEmail());
//                        intent.putExtra("gst",dm.getGst_no());
//                        intent.putExtra("pan",dm.getPan_no());
//                        intent.putExtra("address",dm.getAddress());



                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
