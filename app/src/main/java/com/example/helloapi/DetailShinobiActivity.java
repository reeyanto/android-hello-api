package com.example.helloapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailShinobiActivity extends AppCompatActivity {

    private TextView nama, deskripsi;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shinobi);

        nama = findViewById(R.id.tv_detail_nama);
        deskripsi = findViewById(R.id.tv_detail_deskripsi);
        gambar = findViewById(R.id.iv_detail_gambar);

        nama.setText(getIntent().getStringExtra("NAMA"));
        deskripsi.setText(getIntent().getStringExtra("DESKRIPSI"));
        Glide.with(this).load(getIntent().getStringExtra("GAMBAR")).into(gambar);
    }
}