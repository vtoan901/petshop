package com.example.petshop;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DogAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Dog> list;

    public DogAdapter(Context context, int layout, List<Dog> list) {
        this.context = context;
        this.layout= layout;
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtName,txtGia,txtId;
        ImageView imgHinh;



    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtId = (TextView) view.findViewById(R.id.txtId);
            holder.txtName = (TextView) view.findViewById(R.id.txtTen);
            holder.txtGia = (TextView) view.findViewById(R.id.txtGia);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imgHinh);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();

        }
        Dog dog = list.get(i);
        holder.txtId.setText(String.valueOf(dog.getId()));
        holder.txtName.setText(dog.getTen());
        holder.txtGia.setText(dog.getGia());
        //chuyen mang byte[] sang kieu bitmap
        byte[] hinhAnh = dog.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        holder.imgHinh.setImageBitmap(bitmap);

        return view;
    }
}

