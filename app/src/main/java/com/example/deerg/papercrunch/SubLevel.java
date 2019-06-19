package com.example.deerg.papercrunch;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubLevel extends AppCompatActivity {
    //SQLiteDatabase datavase;
    MainActivity one;
    android.support.v7.widget.Toolbar custom_toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableListView mExpandableListView;
    android.widget.ExpandableListAdapter mExpandableListAdapter;
    List<String> listheader;
    HashMap<String, List<String>> listchild;
    public static String c1,c2,c3;
    List<String> lev;
    int id;
    int idiot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);
        setuptoolbar();

        Intent intet = getIntent();
        final String levelnam = intet.getExtras().getString("Levelname");
        String levelnum = intet.getExtras().getString("Level1");
        int imga = intet.getExtras().getInt("img");
        id = intet.getExtras().getInt("id");
        idiot = id;
        Log.d("id: ",Integer.toString(id));
        TextView lvln =(TextView)findViewById(R.id.textcard1);
        TextView lvlnn =(TextView)findViewById(R.id.textlvl1);
        ImageView img = (ImageView)findViewById(R.id.card1pic);

        lvln.setText(levelnum);
        lvlnn.setText(levelnam);
        img.setImageResource(imga);


        lev = new ArrayList();
        List legg = new ArrayList();
        final List<String> concept1;
        final List<String> concept2;
        final List<String> concept3;

        ListView list = (ListView)findViewById(R.id.sublist);

        one=new MainActivity();
        com.example.deerg.papercrunch.LevelDbHelper levelDbHelper = new LevelDbHelper(this);
        one.datavase = levelDbHelper.getWritableDatabase();


        levelDbHelper.onUpgrade(one.datavase, 1, 1);
        levelDbHelper.putsubLevel(one.datavase);
        
        lev = levelDbHelper.readSubLevel(one.datavase,id);
        concept1=levelDbHelper.getconcept1(one.datavase,id);
        concept2=levelDbHelper.getconcept2(one.datavase,id);
        concept3=levelDbHelper.getconcept3(one.datavase,id);

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,R.layout.sublevel_text,lev);
        list.setAdapter(adap);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) findViewById(R.id.subt);
                Intent intent;
                c1=concept1.get(position);
                c2=concept2.get(position);
                c3=concept3.get(position);
                intent = new Intent(SubLevel.this,ConceptScreen.class);
                intent.putExtra("con1",c1);
                intent.putExtra("con2",c2);
                intent.putExtra("con3",c3);
                intent.putExtra("subname",textView.getText());
                intent.putExtra("lul",levelnam);
                intent.putExtra("lulu",idiot);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.onerflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

}
