package com.bpr.bintan_mobile;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bpr.bintan_mobile.api.ApiClient;
import com.bpr.bintan_mobile.api.ApiInterface;
import com.bpr.bintan_mobile.register.Register;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txt_login;
    EditText R_edt_username, R_edt_name, R_edt_pass;
    String Username, Name, Pass;
    Button R_btn_register;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txt_login = findViewById(R.id.txt_login);
        txt_login.setOnClickListener(this);

        R_edt_username = findViewById(R.id.R_edt_username);
        R_edt_name =findViewById(R.id.R_edt_name);
        R_edt_pass = findViewById(R.id.R_edt_pass);

        R_btn_register = findViewById(R.id.R_btn_register);
        R_btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.R_btn_register:
                Username = R_edt_username.getText().toString();
                Name = R_edt_name.getText().toString();
                Pass = R_edt_pass.getText().toString();
                register(Username, Name, Pass);
                break;

            case R.id.txt_login:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;

        }

    }

    private void register(String username, String name, String password){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerResponse(username, name, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}