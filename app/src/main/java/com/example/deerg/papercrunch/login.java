package com.example.deerg.papercrunch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;


public class login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    MainActivity one;
    Button skip;
    SignInButton signInButton;
    Button login;
    private GoogleApiClient googleApiClient;
    EditText username,password;
    LevelDbHelper levelDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.editText7);
        password=(EditText)findViewById(R.id.editText6);
        levelDbHelper=new LevelDbHelper(this);
        login = (Button)findViewById(R.id.button);
        one=new MainActivity();
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        final Intent i=new Intent(getApplicationContext(),CodedBefore.class);
        final Intent ii=new Intent(getApplicationContext(),Main2Activity.class);


        skip=(Button)findViewById(R.id.button3);
        if(sp.getInt("coded",0)==0)
            skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(i);
            }
        });
        else
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(ii);
                    finish();
                }
            });
        final Intent inte = new Intent((getApplicationContext()),Registeration.class);
        Button but = (Button)findViewById(R.id.button2);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(inte);
                finish();
            }
        } );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://192.168.43.29:8000/").addConverterFactory(GsonConverterFactory.create())
                        .build();
                final getdataApi gda=retrofit.create(getdataApi.class);

                //Post post=new Post(username.getText().toString(),password.getText().toString());
                Call<Post> call=gda.createPost(username.getText().toString(),password.getText().toString());

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(!response.isSuccessful()){
                            Log.d("Code: " + response.code(),"lol");
                            return;
                        }
                        Post posts=response.body();
                        one.totalstars=posts.getTotalstars();
                        one.token=posts.getToken();
                        Log.d("token: "+ posts.getTotalstars()," ");
                        one.avid=posts.getID();
                        levelDbHelper.updatecurrlev(one.datavase,posts.getCurrentLevel());
                        //int prog[]=posts.getLevelprogress();
                        //for(int i=0;i<prog.length;i++){
                        //  levelDbHelper.changeprogress(i+1,one.datavase,prog[i]);
                        //}

                        startActivity(ii);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.d("failed: ", t.getMessage());
                    }
                });

                call = gda.getUserDetails(one.token);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(!response.isSuccessful()){
                            Log.d("Code: " + response.code(),"lol");
                            return;
                        }
                        Post posts=response.body();
                        one.fname=posts.getFname();
                        one.lname=posts.getLname();
                        one.pno=posts.getPno();
                        List<Integer> prog=posts.getLevelprogress();
                        for(int i=0;i<prog.size();i++){
                            levelDbHelper.changeprogress(i+1,one.datavase,prog.get(i));
                            Log.d("lol ","lolo");
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.d("failed: ", t.getMessage());
                    }
                });

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent7,1);


            }
        });





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            gotoProfile();
        } else {
            Toast.makeText(getApplicationContext(), "Sign in cancel", Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile() {
        Intent intent8 = new Intent(login.this, ProfileActivity.class);
        startActivity(intent8);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
