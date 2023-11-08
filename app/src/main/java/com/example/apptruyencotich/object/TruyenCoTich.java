package com.example.apptruyencotich.object;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class TruyenCoTich implements Serializable   {
    private String tenTruyen,Phan,LinkAnh,id;
    public TruyenCoTich(JSONObject o) throws JSONException {
        id =o.getString("id");
        tenTruyen =o.getString("tenTruyen");
        Phan =o.getString("Phan");
        LinkAnh =o.getString("LinkAnh");
    }

    public TruyenCoTich(String tenTruyen, String phan, String linkAnh) {
        this.tenTruyen = tenTruyen;
        this.Phan = phan;
        LinkAnh = linkAnh;
    }
    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getPhan() {
        return Phan;
    }

    public void setPhan(String phan) {
        Phan = phan;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
