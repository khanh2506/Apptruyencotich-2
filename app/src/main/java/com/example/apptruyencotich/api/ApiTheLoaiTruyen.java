package com.example.apptruyencotich.api;

import android.os.AsyncTask;

import com.example.apptruyencotich.interfaces.LayTheLoaiVe;
import com.example.apptruyencotich.interfaces.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiTheLoaiTruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayTheLoaiVe layTheLoaiVe;
    String idTruyen;
    public ApiTheLoaiTruyen(LayTheLoaiVe layTheLoaiVe,String idTruyen  ) {
        this.layTheLoaiVe = layTheLoaiVe;
        this.layTheLoaiVe.batDau();
        this.idTruyen= idTruyen;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://duliet15102002.000webhostapp.com/layChap.php?id=" + idTruyen)
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
            this.layTheLoaiVe.biLoi();
        }else {
            this.layTheLoaiVe.ketThuc(data);
        }
    }
}
