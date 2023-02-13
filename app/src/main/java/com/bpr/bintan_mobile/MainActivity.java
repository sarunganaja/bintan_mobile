package com.bpr.bintan_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bpr.bintan_mobile.adapter.AdapterTab;
import com.bpr.bintan_mobile.api.ApiClient;
import com.bpr.bintan_mobile.api.ApiInterface;
import com.bpr.bintan_mobile.tabungan.TabModel;
import com.bpr.bintan_mobile.tabungan.TabResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<TabModel> listdata = new ArrayList<>();
    ApiInterface apiInterface;

    TextView tv_iduser, tv_namauser;
    String id, nama, cariid;

    SessionManager sessionManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        tv_iduser = findViewById(R.id.tv_iduser);
        tv_namauser = findViewById(R.id.tv_namauser);

        id = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        nama = sessionManager.getUserDetail().get(SessionManager.NAMA);
        tv_iduser.setText(id);
        tv_namauser.setText(nama);


        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        retrieveData();
    }

    public void retrieveData(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TabResponse> tampilData = apiInterface.ardRetrieveTab();

        tampilData.enqueue(new Callback<TabResponse>() {
            @Override
            public void onResponse(Call<TabResponse> call, Response<TabResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listdata = response.body().getData();

                adData = new AdapterTab(MainActivity.this, listdata);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TabResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void cariTabungan(){

    }

    private void moveToLogin() {
        Intent intentLogout = new Intent(MainActivity.this, LoginActivity.class);
        intentLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentLogout);
        finish();
    }
}