package com.example.helloapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShinobiAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ShinobiModel> shinobi;

    public ShinobiAdapter(Context context) {
        this.context = context;
        this.shinobi = new ArrayList<>();
    }

    public void setShinobi(ArrayList<ShinobiModel> shinobi) {
        this.shinobi = shinobi;
    }

    @Override
    public int getCount() {
        return shinobi.size();
    }

    @Override
    public Object getItem(int i) {
        return shinobi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        ShinobiModel shinobiModel = (ShinobiModel) getItem(i);
        viewHolder.bind(shinobiModel);
        return view;
    }

    private class ViewHolder {
        private TextView nama, deskripsi;
        private ImageView gambar;

        public ViewHolder(View view) {
            nama = view.findViewById(R.id.tv_nama);
            deskripsi = view.findViewById(R.id.tv_deskripsi);
            gambar = view.findViewById(R.id.iv_gambar);
        }

        public void bind(ShinobiModel shinobi) {
            nama.setText(shinobi.getNama());
            deskripsi.setText(shinobi.getDeskripsi());
            Glide.with(context).load(shinobi.getGambar()).into(gambar);
        }
    }
}
