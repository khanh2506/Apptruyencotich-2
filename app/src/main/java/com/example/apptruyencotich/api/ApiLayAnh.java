package com.example.apptruyencotich.api;

import android.os.AsyncTask;

import com.example.apptruyencotich.interfaces.LayAnhVe;
import com.example.apptruyencotich.interfaces.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data;
    String idChap;
    LayAnhVe layAnhVe;

    public ApiLayAnh(LayAnhVe layAnhVe,String idChap) {
        this.layAnhVe = layAnhVe;
        this.idChap =idChap;
        this.layAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://duliet15102002.000webhostapp.com/layAnh.php?idChap="+idChap)
                .build();
        data=null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();//1
        }catch (IOException e){
            data=null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data==null){
            this.layAnhVe.biLoi();
        }else {
            this.layAnhVe.ketThuc(data);
        }
    }
}
