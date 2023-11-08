package com.example.apptruyencotich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.apptruyencotich.adapter.TruyenCoTichAdapter;
import com.example.apptruyencotich.api.ApiLayTruyen;
import com.example.apptruyencotich.interfaces.LayTruyenVe;
import com.example.apptruyencotich.object.TruyenCoTich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
GridView gdvDSTruyen;
TruyenCoTichAdapter adapter;
ArrayList<TruyenCoTich> truyenCoTichArrayList;
EditText editTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    private void init(){
        truyenCoTichArrayList = new ArrayList<>();
        adapter=new TruyenCoTichAdapter(this,0,truyenCoTichArrayList);
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        editTimKiem = findViewById(R.id.editTimKiem);

    }
    private void setUp(){

        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        editTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s =editTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TruyenCoTich truyenCoTich= truyenCoTichArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyenCoTich);
                Intent intent = new Intent(MainActivity.this,ChapActivity2.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"dang lay ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenCoTichArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0;i<arr.length();i++){
                JSONObject o=arr.getJSONObject(i);
                truyenCoTichArrayList.add(new TruyenCoTich(o));
            }
            adapter=new TruyenCoTichAdapter(this,0,truyenCoTichArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi Ket Noi",Toast.LENGTH_SHORT).show();
    }
    public void update(View view){
        new ApiLayTruyen(this).execute();
    }
}