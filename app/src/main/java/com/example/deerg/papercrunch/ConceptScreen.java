package com.example.deerg.papercrunch;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConceptScreen extends AppCompatActivity {

    android.support.v7.widget.Toolbar custom_toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableListView mExpandableListView;
    android.widget.ExpandableListAdapter mExpandableListAdapter;
    List<String> listheader;
    HashMap<String, List<String>> listchild;
    private ViewPager vp;
    private SliderFragmentAdapter adapter;
    Button quiztime;
    MainActivity one;
    public static String concept1;
    public static String concept2;
    public static String concept3;
    public static String subn;
    public static String lvln;
    public static String question[];
    public static String option1[];
    public static String option2[];
    public static String option3[];

    public static String subname;
    public static String lvlname;
    public static int sid;

    public static int subid=15;
    public static int cnt=0;
    DataDbHelper dh=new DataDbHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept_screen);
        setuptoolbar();

        vp=findViewById(R.id.vp_question);
        adapter=new SliderFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);



        quiztime=(Button)findViewById(R.id.btnQuiz);

        Intent getinfo = getIntent();
        concept1 = getinfo.getExtras().getString("con1");
        concept2 = getinfo.getExtras().getString("con2");
        concept3 = getinfo.getExtras().getString("con3");
        subn = getinfo.getExtras().getString("subname");
        lvln = getinfo.getExtras().getString("lul");
        sid=getinfo.getExtras().getInt("lulu");

        Toast.makeText(this, "test "+sid, Toast.LENGTH_SHORT).show();
        subname = subn;
        lvlname = lvln;
        //subid=sid;
        dh.insertInValues(db);
        cnt=dh.readCount(subid,db);


        question=new String[cnt];
        option1=new String[cnt];
        option2=new String[cnt];
        option3=new String[cnt];

        question=dh.readQuestion(subid,db);
        option1=dh.readOption1(subid,db);
        option2=dh.readOption2(subid,db);
        option3=dh.readOption3(subid,db);


        TextView tv1 = (TextView)findViewById(R.id.tv_subname);
        TextView tv2 = (TextView)findViewById(R.id.tv_lvlname);

        tv1.setText(subname);
        tv2.setText(lvlname);


        quiztime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),QuestionScreen.class);
                i.putExtra("subname",subname);
                startActivity(i);
            }
        });

    }
    public void setuptoolbar() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            custom_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.custom_toolbar);
        }
        setSupportActionBar(custom_toolbar);
        mExpandableListView = (ExpandableListView) findViewById(R.id.navmenu);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        prepareData();
        mExpandableListAdapter = new com.example.deerg.papercrunch.ExpandableListAdapter(this, listheader, listchild, mExpandableListView);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        getSupportActionBar().setIcon(R.drawable.logo1);
        getSupportActionBar().setTitle("");
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, custom_toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        navigationView.setItemIconTintList(null);

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    mExpandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

    private void prepareData() {
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
        listheader.add("About us");

        List<String> head1 = new ArrayList<String>();
        head1.add("Sub level 1");
        head1.add("Sub level 2");
        head1.add("Sub level 3");
        head1.add("Sub level 4");

        List<String> head2 = new ArrayList<String>();
        head2.add("Level 1");
        head2.add("Level 2");
        head2.add("Level 3");
        head2.add("Level 4");

        listchild.put(listheader.get(0), head1);
        listchild.put(listheader.get(1), head2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.onerflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
