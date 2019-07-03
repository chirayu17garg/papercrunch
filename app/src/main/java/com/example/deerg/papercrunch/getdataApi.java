package com.example.deerg.papercrunch;

import android.text.Editable;

import java.util.List;

import okhttp3.ResponseBody;
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

    @FormUrlEncoded
    @POST("api/register/")
    Call<Register> createReg(
            @Field("email") String email,
            @Field("password") String password,
            @Field("first_name")String first_name,
            @Field("last_name")String last_name,
            @Field("avatarId")int avatarId,
            @Field("google")boolean google
    );

    @GET("api/user-progress/")
    Call<user> getUserDetails(@Header("Authorization") String Header);

    @GET("api/logout/")
    Call<Void> logout(@Header("Authorization") String Header);

    @FormUrlEncoded
    @POST("api/sync-from-mobile/")
    Call<Void> sync(
            @Field("currentLevel") int currentLevel,
            @Field("totalStars") int totalStars,
            @Field("avatarId") int ID,
            @Header("Authorization") String Header
    );

    @FormUrlEncoded
    @POST("api/user-progress/")
    Call<Void> setUserDetails(@Header("Authorization") String Header,
                              @Field("levelOne") int levelOne,
                              @Field("levelTwo") int levelTwo,
                              @Field("levelThree") int levelThree,
                              @Field("levelFour") int levelFour,
                              @Field("levelFive") int levelFive,
                              @Field("levelSix") int levelSix,
                              @Field("levelSeven") int levelSeven,
                              @Field("levelEight") int levelEight,
                              @Field("levelNine") int levelNine
    );

    @GET("api/status/")
    Call<subbool> getbool(@Header("Authorization") String Header);

    @FormUrlEncoded
    @POST("api/status/")
    Call<Void> setbool(@Header("Authorization") String Header,
                       @Field("subLevel1") int sublevel1,
                       @Field("subLevel2") int sublevel2,
                       @Field("subLevel3") int sublevel3,
                       @Field("subLevel4") int sublevel4,
                       @Field("subLevel5") int sublevel5,
                       @Field("subLevel6") int sublevel6,
                       @Field("subLevel7") int sublevel7,
                       @Field("subLevel8") int sublevel8,
                       @Field("subLevel9") int sublevel9,
                       @Field("subLevel10") int sublevel10,
                       @Field("subLevel11") int sublevel11,
                       @Field("subLevel12") int sublevel12,
                       @Field("subLevel13") int sublevel13,
                       @Field("subLevel14") int sublevel14,
                       @Field("subLevel15") int sublevel15,
                       @Field("subLevel16") int sublevel16,
                       @Field("subLevel17") int sublevel17,
                       @Field("subLevel18") int sublevel18,
                       @Field("subLevel19") int sublevel19,
                       @Field("subLevel20") int sublevel20,
                       @Field("subLevel21") int sublevel21,
                       @Field("subLevel22") int sublevel22,
                       @Field("subLevel23") int sublevel23,
                       @Field("subLevel24") int sublevel24,
                       @Field("subLevel25") int sublevel25,
                       @Field("subLevel26") int sublevel26,
                       @Field("subLevel27") int sublevel27

    );

    @FormUrlEncoded
    @POST("api/playground/")
    Call<Code> sendcode(@Header("Authorization") String Header,
                        @Field("code") Editable code
                        );

}

