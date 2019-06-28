package com.example.deerg.papercrunch;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    MainActivity one;

    Context mContext;
    android.support.v7.widget.Toolbar custom_toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableListView mExpandableListView;
    ExpandableListAdapter mExpandableListAdapter;
    List<String> listheader;
    HashMap<String, List<String>> listchild;
    private ExpandableListView listView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private ProgressBar progressBar;
    private Object LevelDbHelper;
    public static List<String> lev;
    public static List<String> head2;
    public static List<CardData> card1;
    public static List<String> c1,c2,c3;

    LevelDbHelper levelDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setuptoolbar();

        card1 = new ArrayList<>();

        one=new MainActivity();
        final LevelDbHelper levelDbHelper = new LevelDbHelper(this);


        for(int i=1;i<=9;i++){
            CardData cardData = levelDbHelper.readLevel(i,one.datavase);
            card1.add(cardData);
        }

        listheader = new ArrayList<String>();
        listchild = new HashMap<String, List<String>>();
        listheader.add("View All Sub Levels");
        listheader.add("View Prevoius Level");
        listheader.add("View Next Level");
        listheader.add("");
        listheader.add("");
        listheader.add("");
        listheader.add("Settings");
        listheader.add("Rate us");
        listheader.add("Save Your Progress");

        one=new MainActivity();
        mContext=this;

        final int sid=levelDbHelper.getcurrlev(one.datavase);
        c1=levelDbHelper.getconcept1(one.datavase,sid);
        c2=levelDbHelper.getconcept2(one.datavase,sid);
        c3=levelDbHelper.getconcept3(one.datavase,sid);

        lev = levelDbHelper.readSubLevel(one.datavase,sid);
        head2=levelDbHelper.getprev(one.datavase,sid);

        listchild.put(listheader.get(0),lev);
        listchild.put(listheader.get(1), head2);

        mExpandableListView = (ExpandableListView) findViewById(R.id.navmenu);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mExpandableListAdapter = new com.example.deerg.papercrunch.ExpandableListAdapter(this, listheader, listchild, mExpandableListView);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        getSupportActionBar().setIcon(R.drawable.logo1);
        getSupportActionBar().setTitle("");

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, custom_toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        navigationView.setItemIconTintList(null);



        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id1) {
                if(groupPosition==0)
                {
                    TextView textView = (TextView) findViewById(R.id.subt);
                    Intent intent;
                    intent = new Intent(Main2Activity.this,ConceptScreen.class);
                    intent.putExtra("con1",c1.get(childPosition));
                    intent.putExtra("con2",c2.get(childPosition));
                    intent.putExtra("con3",c3.get(childPosition));
                    intent.putExtra("subname",lev.get(childPosition));
                    intent.putExtra("levelid",sid);
                    intent.putExtra("levelname",card1.get(childPosition).getlevelname());
                    startActivity(intent);
                }
                else if(groupPosition==1)
                {
                    Intent intent = new Intent(mContext,SubLevel.class);
                    intent.putExtra("Level1",card1.get(childPosition).getlevelnum());
                    intent.putExtra("Levelname",card1.get(childPosition).getlevelname());
                    intent.putExtra("img",card1.get(childPosition).getimg());
                    intent.putExtra("prog",card1.get(childPosition).geprog());
                    intent.putExtra("id",childPosition+1);
                    mContext.startActivity(intent);
                }
                return true;
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition==0 || groupPosition==1)
                {
                    if(mExpandableListView.isGroupExpanded(groupPosition))
                        mExpandableListView.collapseGroup(groupPosition);
                    mExpandableListView.expandGroup(groupPosition);
                }
                else if(groupPosition==6)
                {
                    Intent i=new Intent(mContext,settings.class);
                    startActivity(i);
                }

                else if(groupPosition==8)
                {
                    final Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl("http://192.168.43.29:8000/").addConverterFactory(GsonConverterFactory.create())
                            .build();
                    final getdataApi gda=retrofit.create(getdataApi.class);
                    DataDbHelper dataDbHelper = new DataDbHelper(Main2Activity.this);

                    SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    final String token=sp.getString("token","");
                    Toast.makeText(Main2Activity.this,token,Toast.LENGTH_SHORT).show();
                    Call<Void> call=gda.sync(levelDbHelper.getcurrlev(one.datavase),dataDbHelper.getStars(one.datavase),sp.getInt("id_avatar", 0),"Token "+token);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Log.d("Code: " + response.code(), "lol");
                                Toast.makeText(Main2Activity.this, "Failed Please try again!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("failed: ", t.getMessage());
                            Toast.makeText(Main2Activity.this,"Failed Please try again",Toast.LENGTH_SHORT).show();
                        }
                    });

                    int prog[] = new int[9];
                    for (int i = 0; i < 9; i++)
                        prog[i] = levelDbHelper.getprogress(one.datavase, i + 1);

                    Call<Void> call1 = gda.setUserDetails("Token " + token, prog[0], prog[1], prog[2], prog[3], prog[4], prog[5], prog[6], prog[7], prog[8]);
                    call1.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {

                                Toast.makeText(Main2Activity.this, "Sync Successful!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            Log.d("failed poke: ", t.getMessage());
                            Toast.makeText(Main2Activity.this, "Failed Please try again!!!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    int sbb[] = new int[27];
                    for(int i=0;i<27;i++)
                        sbb[i]=levelDbHelper.getbool(i+1,one.datavase);

                    Call<Void> call2 = gda.setbool("Token " + token, sbb[0], sbb[1], sbb[2], sbb[3], sbb[4], sbb[5], sbb[6], sbb[7], sbb[8],
                            sbb[9],sbb[10],sbb[11],sbb[12],sbb[13],sbb[14],sbb[15],sbb[16],sbb[17],sbb[18],sbb[19],sbb[20],sbb[21],sbb[22],sbb[23],sbb[24], sbb[25],sbb[26]);
                    call2.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {

                                Log.d("Code poker: " + response.code(), token);
                                Toast.makeText(Main2Activity.this, "Failed Please try again!!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if (response.code() == 200) {

                                Toast.makeText(Main2Activity.this, "Sync Successful!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            Log.d("failed poke: ", t.getMessage());
                            Toast.makeText(Main2Activity.this, "Failed Please try again!!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                return true;
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerAdapter myAdap = new RecyclerAdapter(this,card1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdap);

    }
    @Override
    public void onBackPressed(){
        recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        ImageeAdapter imageeAdapter=new ImageeAdapter(this);
        menuInflater.inflate(R.menu.onerflow_menu, menu);
        menu.findItem(R.id.avatar).setIcon(imageeAdapter.image_id2[one.avid]);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Intent i=new Intent(Main2Activity.this,IdScreen.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    public void setuptoolbar() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            custom_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.custom_toolbar);
        }
        setSupportActionBar(custom_toolbar);

    }

}


