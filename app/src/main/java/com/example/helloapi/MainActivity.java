package com.example.helloapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ShinobiModel shinobiModel;
    private ArrayList<ShinobiModel> shinobiModelArrayList;
    private ShinobiAdapter shinobiAdapter;
    private static String URL = "https://www.melonkoding.com/sample-api/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_shinobi);
        shinobiModelArrayList = new ArrayList<>();
        fetchDataFromAPI();

        listView.setOnItemClickListener(this);
    }

    private void fetchDataFromAPI() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    shinobiModel = new ShinobiModel();
                    shinobiModel.setNama(jsonObject.getString("nama"));
                    shinobiModel.setDeskripsi(jsonObject.getString("deskripsi"));
                    shinobiModel.setGambar(jsonObject.getString("gambar"));

                    shinobiModelArrayList.add(shinobiModel);
                }
                shinobiAdapter = new ShinobiAdapter(this);
                shinobiAdapter.setShinobi(shinobiModelArrayList);
                listView.setAdapter(shinobiAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        });
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetailShinobiActivity.class);
        intent.putExtra("NAMA", shinobiModelArrayList.get(i).getNama());
        intent.putExtra("DESKRIPSI", shinobiModelArrayList.get(i).getDeskripsi());
        intent.putExtra("GAMBAR", shinobiModelArrayList.get(i).getGambar());

        startActivity(intent);
    }
}