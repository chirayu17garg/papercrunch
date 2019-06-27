package com.example.deerg.papercrunch;
 import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

    public class MySingleton extends AppCompatActivity {
        private RequestQueue requestQueue;
        private static MySingleton mInstance;
        private static Context mCtx;
        private MySingleton(Context context){
            mCtx=context;
            requestQueue=getRequestQueue();
        }

        public RequestQueue getRequestQueue() {
            if(requestQueue==null){
                requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());

            }
            return requestQueue;
        }
        public static synchronized MySingleton getInstance(Context context){
            if(mInstance==null){
                mInstance= new MySingleton(context);
            }
            return mInstance;
        }
        public void addToRequest(Request<?>request){
            requestQueue.add(request);
        }
}
