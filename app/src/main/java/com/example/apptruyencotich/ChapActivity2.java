package com.example.apptruyencotich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apptruyencotich.adapter.ChapTruyenAdapater;
import com.example.apptruyencotich.api.ApiTheLoaiTruyen;
import com.example.apptruyencotich.interfaces.LayTheLoaiVe;
import com.example.apptruyencotich.object.ChapTruyen;
import com.example.apptruyencotich.object.TruyenCoTich;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity2 extends AppCompatActivity implements LayTheLoaiVe {
    TextView txvTenTruyens;
    ImageView imgAnhTruyens;
    TruyenCoTich truyenCoTich;
    ListView lsvDanhSachTheloai;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapater chapTruyenAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap2);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiTheLoaiTruyen(this,truyenCoTich.getId()).execute();
    }
    private void init(){
        Bundle b =getIntent().getBundleExtra("data");
        truyenCoTich=(TruyenCoTich) b.getSerializable("truyen");

        //tao du lieu ao
        arrChap=new ArrayList<>();
       /* for (int i=0;i<20;i++){
            arrChap.add(new ChapTruyen("thể loại "+i,"06 - 10 - 2022"));
        }
        chapTruyenAdapater= new ChapTruyenAdapater(this,0,arrChap);

        */
    }
    private void anhXa(){
        imgAnhTruyens  =findViewById(R.id.imgAnhTruyens);
        txvTenTruyens =findViewById(R.id.txvTentruyens);
        lsvDanhSachTheloai =findViewById(R.id.lsvDanhSachTheloai);
    }
    private void setUp(){
        txvTenTruyens.setText(truyenCoTich.getTenTruyen());
        Glide.with(this).load(truyenCoTich.getLinkAnh()).into(imgAnhTruyens);
//        lsvDanhSachTheloai.setAdapter(chapTruyenAdapater);
    }
    private void setClick(){
        lsvDanhSachTheloai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("idChap",arrChap.get(i).getId());
                Intent intent = new Intent(ChapActivity2.this,DocTruyenActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Lay Chap Ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array=new JSONArray(data);
            for (int i=0;i<array.length();i++){
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapater= new ChapTruyenAdapater(this,0,arrChap);
            lsvDanhSachTheloai.setAdapter(chapTruyenAdapater);
        } catch (JSONException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {

    }
}