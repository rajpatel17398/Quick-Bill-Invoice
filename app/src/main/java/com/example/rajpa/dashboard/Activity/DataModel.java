package com.example.rajpa.dashboard.Activity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.util.Random;

@SuppressLint("ParcelCreator")
public class DataModel  implements Parcelable {


    String quality,bf,gsm,price,number,party,size,weight,addstock_size,addstock_weight,
            amount_paid,created_at,final_amount,
            company_name,name,name_2,mobile,mobile_2,email,gst_no,pan_no,address,edit,id,pending,invoice,sg,cg;

    public DataModel() {

    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getsize() {
        return size;
    }

    public void setsize(String size) {
        this.size = size;
    }

    public String getweight() {
        return weight;
    }

    public void setweight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return addstock_size;
    }

    public void setSize(String addstock_size) {
        this.addstock_size = addstock_size;
    }

    public String getWeight() {
        return addstock_weight;
    }

    public void setWeight(String addstock_weight) {
        this.addstock_weight = addstock_weight;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(String final_amount) {
        this.final_amount = final_amount;
    }

    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName_2() {
        return name_2;
    }
    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile_2() {
        return mobile_2;
    }
    public void setMobile_2(String mobile_2) {
        this.mobile_2 = mobile_2;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGst_no() {
        return gst_no;
    }
    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    public String getPan_no() {
        return pan_no;
    }
    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdit() {
        return edit;
    }
    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPending() {
        return pending;
    }
    public void setPending(String pending) {this.pending = pending; }

    public String getInvoice() {
        return invoice;
    }
    public void setInvoice(String invoice) {this.invoice = invoice; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public void setsgst(String sg) { this.sg=sg;
    }
    public String getsgst(){return sg;}

    public void setcgst(String cg) { this.cg=cg;
    }
    public String getcgst(){return cg;}
}

