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
import com.bpr.bintan_mobile.login.Login;
import com.bpr.bintan_mobile.login.LoginData;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText L_etUsername, L_etPassword;
    String Username, Password;
    Button L_btn_Login;
    TextView txt_register;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        L_etUsername = findViewById(R.id.L_etUsername);
        L_etPassword = findViewById(R.id.L_etPassword);
        L_btn_Login = findViewById(R.id.L_btn_Login);
        L_btn_Login.setOnClickListener(this);
        txt_register = findViewById(R.id.txt_register);
        txt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.L_btn_Login:
                Username = L_etUsername.getText().toString();
                Password = L_etPassword.getText().toString();
                login(Username, Password);
                break;

            case R.id.txt_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }


    }

    private void login(String username, String password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    sessionManager = new SessionManager(LoginActivity.this);
                    LoginData loginData = response.body().getData();
                    sessionManager.createLoginSession(loginData);

                    Toast.makeText(LoginActivity.this, response.body().getData().getNama(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });


    }
}