package com.example.deerg.papercrunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface getdataApi {


    @FormUrlEncoded
    @POST("api/login/")
    Call<Post> createPost(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/user-details")
    Call<Post> getUserDetails(@Header("Dynamic header") String Header);

}
