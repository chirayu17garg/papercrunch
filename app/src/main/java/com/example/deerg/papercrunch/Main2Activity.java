package com.example.deerg.papercrunch;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    android.support.v7.widget.Toolbar custom_toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableListView mExpandableListView;
    ExpandableListAdapter mExpandableListAdapter;
    List<String> listheader;
    HashMap<String, List<String>> listchild;
    private ExpandableListView listView;
    private aExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private ProgressBar progressBar;
    private Object LevelDbHelper;
    //SQLiteDatabase datavase;
    MainActivity one;
    public static List<CardData> card1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setuptoolbar();

        card1 = new ArrayList<>();

        /*card1.add(new CardData("Level 1","Introduction","25%",R.drawable.ic_content_paste_black_24dp,1));
        card1.add(new CardData("Level 2","Data Types and Variables","25%",R.drawable.ic_date_range_black_24dp,2));
        card1.add(new CardData("Level 3","Operators","25%",R.drawable.ic_developer_mode_black_24dp,3));
        card1.add(new CardData("Level 4","Input/Output","25%",R.drawable.ic_input_black_24dp,4));
        card1.add(new CardData("Level 5","Logical Operators","25%",R.drawable.logic,5));
        card1.add(new CardData("Level 6","Conditional Statements","25%",R.drawable.ic_swap_horiz_black_24dp,6));
        card1.add(new CardData("Level 7","Loops","25%",R.drawable.ic_loop_black_24dp,7));
        card1.add(new CardData("Level 8","Functions","25%",R.drawable.ic_functions_black_24dp,8));
        card1.add(new CardData("Level 9","Arrays and Strings","25%",R.drawable.ic_view_array_black_24dp,9));*/

        one=new MainActivity();
        com.example.deerg.papercrunch.LevelDbHelper levelDbHelper = new LevelDbHelper(this);
        //levelDbHelper.putLevel(one.datavase);

        for(int i=1;i<=9;i++){
            CardData cardData = levelDbHelper.readLevel(i,one.datavase);
            card1.add(cardData);
        }



        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerAdapter myAdap = new RecyclerAdapter(this,card1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdap);

        //listView = (ExpandableListView) findViewById(R.id.lvExp);
       /* initData();
        listAdapter = new com.example.deerg.papercrunch.aExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getApplicationContext(), ConceptScreen.class);
                startActivity(intent);
                return true;
            }
        });*/
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

    /*private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        one=new MainActivity();
        com.example.deerg.papercrunch.LevelDbHelper levelDbHelper = new LevelDbHelper(this);
        one.datavase = levelDbHelper.getWritableDatabase();


        levelDbHelper.onUpgrade(one.datavase, 1, 1);
        levelDbHelper.putLevel(one.datavase);
        listDataHeader = levelDbHelper.readLevel(one.datavase);

        levelDbHelper.putsubLevel(one.datavase);
        for (int i = 1; i <= 9; i++) {
            List<String> lev1 = levelDbHelper.readSubLevel(one.datavase, i);
            listHash.put(listDataHeader.get(i-1), lev1);
        }
    }*/

}

/*     <ExpandableListView
        android:layout_width="match_parent"
        android:id="@+id/lvExp"
        android:groupIndicator="@null"
        android:layout_height="match_parent"
        android:dividerHeight="0.5dp">

    </ExpandableListView>*/

