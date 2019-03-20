package com.example.rajpa.dashboard.Activity;

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

public class  setting_viewsellparties_BaseAdapter extends BaseAdapter {
    List<DataModel>list;
    LayoutInflater inflater;
    Context context;
    String URL="https://rajpatel17398.000webhostapp.com/delete sell parties.php";

    public  setting_viewsellparties_BaseAdapter(Context context, List<DataModel> list) {
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

        convertView=inflater.inflate(R.layout.viewsellparty_listview_layout,null);
        TextView txt1=convertView.findViewById(R.id.vs_coname);
        TextView txt2=convertView.findViewById(R.id.vs_pa1name);
        TextView txt3=convertView.findViewById(R.id.vs_pa2name);
        TextView txt4=convertView.findViewById(R.id.vs_pa1mobile);
        TextView txt5=convertView.findViewById(R.id.vs_pa2mobile);
        TextView txt6=convertView.findViewById(R.id.vs_email);
        TextView txt7=convertView.findViewById(R.id.vs_gstno);
        TextView txt8=convertView.findViewById(R.id.vs_panno);
        TextView txt9=convertView.findViewById(R.id.vs_address);
        Button b1=convertView.findViewById(R.id.vs_button);
        Button b2=convertView.findViewById(R.id.vs_button1);

      //  TextView txt10=convertView.findViewById(R.id.vs_edit);

        final DataModel dm=list.get(position);
        txt1.setText(dm.getCompany_name());
        txt2.setText(dm.getName());
        txt3.setText(dm.getName_2());
        txt4.setText(dm.getMobile());
        txt5.setText(dm.getMobile_2());
        txt6.setText(dm.getEmail());
        txt7.setText(dm.getGst_no());
        txt8.setText(dm.getPan_no());
        txt9.setText(dm.getAddress());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,viewsellparties_button.class);
                intent.putExtra("id",dm.getId());
                intent.putExtra("coname",dm.getCompany_name());
                intent.putExtra("pa1name",dm.getName());
                intent.putExtra("pa2name",dm.getName_2());
                intent.putExtra("mo",dm.getMobile());
                intent.putExtra("mo2",dm.getMobile_2());
                intent.putExtra("email",dm.getEmail());
                intent.putExtra("gst",dm.getGst_no());
                intent.putExtra("pan",dm.getPan_no());
                intent.putExtra("address",dm.getAddress());

                context.startActivity(intent);
            }
        });
       // txt10.setText(dm.getEdit());

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Res",">>>>>>>>"+response);
                        if (response.trim().equals("success")){
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context,setting_viewsellparties.class);
                            context.startActivity(intent);


//                            Intent intent=new Intent(Sell.this,Add_stock.class);
//                            startActivity(intent);
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
                        param.put("company_name",dm.getCompany_name());
//                        param.put("updatesellparty1",e2.getText().toString());
//                        param.put("updatesellparty2",e3.getText().toString());
//                        param.put("updatesellmo1",e4.getText().toString());
//                        param.put("updatesellmo2",e5.getText().toString());
//                        param.put("updatesellemail",e6.getText().toString());
//                        param.put("updatesellgstno", e7.getText().toString());
//                        param.put("updatesellpanno",e8.getText().toString());
//                        param.put("updateselladdress",e9.getText().toString());
//                        param.put("cgst_edit_text",cgst.getText().toString());
//                        param.put("sgst_edit_text",sgst.getText().toString());
//                        param.put("insu",insu.getText().toString());
//                        param.put("total",total.getText().toString());
//                        param.put("cgst_edit_text",mail);
//                        param.put("cgst_edit_text",mail);
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

