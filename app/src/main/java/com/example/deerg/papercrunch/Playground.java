package com.example.deerg.papercrunch;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Playground extends AppCompatActivity {

    EditText editText;
    String tocompile;
    String compile1,compile2;
    TextView result;
    Button comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);


        editText = (EditText)findViewById(R.id.EnterCode);
        tocompile = editText.toString();
        result = (TextView)findViewById(R.id.compiled);
        comp=(Button)findViewById(R.id.compilebutt);
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://papercrunch-1.herokuapp.com/").addConverterFactory(GsonConverterFactory.create())
                        .build();
                final getdataApi gda=retrofit.create(getdataApi.class);
                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                final String token=sp.getString("token","");



                Call<Code> call2 = gda.sendcode("Token " + token,tocompile);
                call2.enqueue(new Callback<Code>() {
                    @Override
                    public void onResponse(Call<Code> call, Response<Code> response) {
                        if (!response.isSuccessful()) {

                            Log.d("Code poker: " + response.code(), "Anything");
                            return;
                        }
                        Code code = response.body();
                        compile1 = code.getCompiled_result();
                        compile2 = code.getRun_result();
                    }

                    @Override
                    public void onFailure(Call<Code> call, Throwable t) {

                        Log.d("failed poke: ", t.getMessage());
                        //Toast.makeText(Main2Activity.this, "Failed Please try again!!!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
