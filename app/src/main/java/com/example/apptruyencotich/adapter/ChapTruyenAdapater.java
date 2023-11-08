package com.example.apptruyencotich.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apptruyencotich.R;
import com.example.apptruyencotich.object.ChapTruyen;

import java.util.ArrayList;
import java.util.List;

public class ChapTruyenAdapater extends ArrayAdapter<ChapTruyen> {
    private Context ct;
    private ArrayList<ChapTruyen> arr;

    public ChapTruyenAdapater(@NonNull Context context, int resource, @NonNull List<ChapTruyen> objects) {
        super(context, resource, objects);
        this.ct=context;
        this.arr=new ArrayList<>(objects);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView ==null){
            LayoutInflater inflater=(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_chap_truyen,null);
        }
        if (arr.size()>0){
            TextView txvtheLoai,txvNgayNhap;
            txvtheLoai=convertView.findViewById(R.id.txvtheLoai);
            txvNgayNhap=convertView.findViewById(R.id.txvNgayNhap);
            ChapTruyen chapTruyen = arr.get(position);
            txvtheLoai.setText(chapTruyen.getTheLoai());
            txvNgayNhap.setText(chapTruyen.getNgayDang());
        }
        return convertView;
    }
}
