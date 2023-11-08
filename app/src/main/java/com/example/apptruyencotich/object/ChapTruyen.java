package com.example.apptruyencotich.object;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapTruyen {
    private String theLoai,ngayDang,id;

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChapTruyen(){

    };

    public ChapTruyen(String theLoai, String ngayDang){
        this.theLoai = theLoai;
        this.ngayDang = ngayDang;
    }
    public ChapTruyen(JSONObject o) throws JSONException {
        theLoai =o.getString("tentheloai");
        ngayDang =o.getString("ngaynhap");
        id =o.getString("id");

    }
}
