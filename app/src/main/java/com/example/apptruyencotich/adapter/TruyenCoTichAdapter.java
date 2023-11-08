package com.example.apptruyencotich.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.apptruyencotich.R;
import com.example.apptruyencotich.object.TruyenCoTich;

import java.util.ArrayList;
import java.util.List;

public class TruyenCoTichAdapter extends ArrayAdapter<TruyenCoTich> {
    private Context ct;
    private ArrayList<TruyenCoTich> arr;
    public TruyenCoTichAdapter(@NonNull Context context, int resource, @NonNull List<TruyenCoTich> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }
    public void sortTruyen(String s){
        s=s.toUpperCase();
        int k=0;
        for (int i=0;i<arr.size();i++ ){
            TruyenCoTich t=arr.get(i);
            String ten=t.getTenTruyen().toUpperCase();
            if (ten.indexOf(s)>=0){
                arr.set(i,arr.get(k));
                arr.set(k,t);
                k++;
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater =(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen,null);
        }
        if (arr.size()>0){
            TruyenCoTich truyenCoTich=this.arr.get(position);
            TextView tenTenTruyen =convertView.findViewById(R.id.txvTentruyen);
            TextView tenPhan =convertView.findViewById(R.id.txvTenChap);
            ImageView imAnhTruyen =convertView.findViewById(R.id.imgAnhTruyen);
            tenTenTruyen.setText(truyenCoTich.getTenTruyen());
            tenPhan.setText(truyenCoTich.getPhan());
            Glide.with(this.ct).load(truyenCoTich.getLinkAnh()).into(imAnhTruyen);
        }
        return convertView;
    }
}
