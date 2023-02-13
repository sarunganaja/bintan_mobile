package com.bpr.bintan_mobile.api;

import com.bpr.bintan_mobile.login.Login;
import com.bpr.bintan_mobile.register.Register;
import com.bpr.bintan_mobile.tabungan.TabResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("password") String password
    );

    @GET("tabdat.php")
    Call<TabResponse> ardRetrieveTab();

}
