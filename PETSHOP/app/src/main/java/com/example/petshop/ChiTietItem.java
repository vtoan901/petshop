package com.example.petshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChiTietItem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_item);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        Dog selectedDog = (Dog) intent.getSerializableExtra("SelectedDog");

        // Hiển thị thông tin của con chó được chọn
        TextView txtId = findViewById(R.id.txtId);  // Thay thế bằng id thật của TextView trong layout
        TextView txtName = findViewById(R.id.txtName);  // Thay thế bằng id thật của TextView trong layout
        TextView txtGia = findViewById(R.id.txtGia);  // Thay thế bằng id thật của TextView trong layout
        ImageView imgHinh = findViewById(R.id.imgHinh);

        if (selectedDog != null){
            txtId.setText("Id: " + String.valueOf(selectedDog.getId()));
            txtName.setText("Ten: " + selectedDog.getTen());
            txtGia.setText("Gia: " + selectedDog.getGia());
            Bitmap bitmap = BitmapFactory.decodeByteArray(selectedDog.getHinh(), 0, selectedDog.getHinh().length);
            imgHinh.setImageBitmap(bitmap);
        }
        else {
            Toast.makeText(this, "OKOKOOKOK", Toast.LENGTH_SHORT).show();
        }

    }
}
