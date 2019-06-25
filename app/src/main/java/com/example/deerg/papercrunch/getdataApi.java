package com.example.deerg.papercrunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface getdataApi {

    @GET("api/sync-from-mobile")
    Call<Post>  getPosts(@Field("name") String id);
}
