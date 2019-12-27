package com.example.loginretrofit.api;

import com.example.loginretrofit.model.RegisterData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/register.php")
    @FormUrlEncoded
    Call<RegisterData> registerUser(
        @Field("username") String username,
        @Field("email") String email,
                @Field("password") String password,
                    @Field("confirm_password") String confirm_password
    );


}
