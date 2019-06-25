package com.example.deerg.papercrunch;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

public class PopupActivityJ extends AppCompatActivity {
    static GridView grid_View2;
    IdScreen idScreen;
    MainActivity one;

    //public static ImageButton img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_j);
        one=new MainActivity();
        idScreen=new IdScreen();
        grid_View2=(GridView)findViewById(R.id.gridview2);
        grid_View2.setAdapter(new ImageeAdapter(this));
        grid_View2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("imgpos",position);
                setResult(RESULT_OK, intent);
                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("id_avatar", position);
                editor.commit();
                one.avid=position;
                finish();
            }
        });
        //img2=(ImageButton) findViewById(R.id.avatarr);


        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

    }
}
