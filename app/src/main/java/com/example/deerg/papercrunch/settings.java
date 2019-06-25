package com.example.deerg.papercrunch;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class settings extends AppCompatActivity {

    MainActivity one;
    Main2Activity two;
    Context mContext;
    android.support.v7.widget.Toolbar custom_toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableListView mExpandableListView;
    ExpandableListAdapter mExpandableListAdapter;
    List<String> listheader;
    HashMap<String, List<String>> listchild;
    public static List<String> c1,c2,c3;
    public static List<String> lev;
    public static List<String> head2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setuptoolbar();

        one=new MainActivity();
        two=new Main2Activity();
        LevelDbHelper levelDbHelper = new LevelDbHelper(this);

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
                    intent = new Intent(settings.this,ConceptScreen.class);
                    intent.putExtra("con1",c1.get(childPosition));
                    intent.putExtra("con2",c2.get(childPosition));
                    intent.putExtra("con3",c3.get(childPosition));
                    intent.putExtra("subname",lev.get(childPosition));
                    intent.putExtra("levelid",sid);
                    intent.putExtra("levelname",two.card1.get(childPosition).getlevelname());
                    startActivity(intent);
                }
                else if(groupPosition==1)
                {
                    Intent intent = new Intent(mContext,SubLevel.class);
                    intent.putExtra("Level1",two.card1.get(childPosition).getlevelnum());
                    intent.putExtra("Levelname",two.card1.get(childPosition).getlevelname());
                    intent.putExtra("img",two.card1.get(childPosition).getimg());
                    intent.putExtra("prog",two.card1.get(childPosition).geprog());
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
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.onerflow_menu, menu);
        ImageeAdapter imageeAdapter=new ImageeAdapter(this);
        menu.findItem(R.id.avatar).setIcon(imageeAdapter.image_id2[one.avid]);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i=new Intent(this,IdScreen.class);
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