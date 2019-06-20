package com.example.deerg.papercrunch;

import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubLevel extends AppCompatActivity {
    //SQLiteDatabase datavase;
    MainActivity one;
    Main2Activity two;
    android.support.v7.widget.Toolbar custom_toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableListView mExpandableListView;
    android.widget.ExpandableListAdapter mExpandableListAdapter;
    List<String> listheader;
    HashMap<String, List<String>> listchild;
    public static String c1,c2,c3;
    public static List<String> lev;
    public static List<String> head2;
    public static List<String> concept1;
    public static List<String> concept2;
    public static List<String> concept3;
    public static int id;
    Context mContext;
    com.example.deerg.papercrunch.LevelDbHelper levelDbHelper;
    int idiot;
    int levid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);
        mContext=this;
        setuptoolbar();

        Intent intet = getIntent();
        id = intet.getExtras().getInt("id");
        levid = id;
        idiot = id;
        one=new MainActivity();
        two=new Main2Activity();
        levelDbHelper = new LevelDbHelper(this);
        //levelDbHelper.putsubLevel(one.datavase);
        lev = new ArrayList();
        ListView list = (ListView)findViewById(R.id.sublist);

        lev = levelDbHelper.readSubLevel(one.datavase,id);

        CardData cardDatasub = levelDbHelper.readLevel(levid,one.datavase);


        final String levelnam =  cardDatasub.getlevelname();;
        String levelnum = cardDatasub.getlevelnum();
        int imga = cardDatasub.getimg();
        int progr = cardDatasub.geprog();

        TextView lvln =(TextView)findViewById(R.id.textcard1);
        TextView lvlnn =(TextView)findViewById(R.id.textlvl1);
        ImageView img = (ImageView)findViewById(R.id.card1pic);
        TextView progress = (TextView)findViewById(R.id.prog);

        lvln.setText(levelnum);
        lvlnn.setText(levelnam);
        img.setImageResource(imga);
        progress.setText(Integer.toString(progr));



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

        head2=levelDbHelper.getprev(one.datavase,id);

        listchild.put(listheader.get(0), lev);
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

        concept1=levelDbHelper.getconcept1(one.datavase,id);
        concept2=levelDbHelper.getconcept2(one.datavase,id);
        concept3=levelDbHelper.getconcept3(one.datavase,id);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(groupPosition==0)
                {
                    TextView textView = (TextView) findViewById(R.id.subt);
                    Intent intent;
                    c1=concept1.get(childPosition);
                    c2=concept2.get(childPosition);
                    c3=concept3.get(childPosition);
                    intent = new Intent(SubLevel.this,ConceptScreen.class);
                    intent.putExtra("con1",c1);
                    intent.putExtra("con2",c2);
                    intent.putExtra("con3",c3);
                    intent.putExtra("subname",textView.getText());
                    intent.putExtra("levelid",idiot);
                    intent.putExtra("lul",levelnam);

                    startActivity(intent);
                }
                else if(groupPosition==1)
                {
                    Intent intent = new Intent(mContext,SubLevel.class);
                    intent.putExtra("Level1",two.card1.get(childPosition).getlevelnum());
                    intent.putExtra("Levelname",two.card1.get(childPosition).getlevelname());
                    intent.putExtra("img",two.card1.get(childPosition).getimg());
                    intent.putExtra("id",childPosition+1);
                    mContext.startActivity(intent);
                }
                finish();
                return true;
            }
        });

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,R.layout.sublevel_text,lev);
        list.setAdapter(adap);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView textView = (TextView) findViewById(R.id.subt);
                String sub = lev.get(position);
                Intent intent;
                c1=concept1.get(position);
                c2=concept2.get(position);
                c3=concept3.get(position);
                intent = new Intent(SubLevel.this,ConceptScreen.class);
                intent.putExtra("con1",c1);
                intent.putExtra("con2",c2);
                intent.putExtra("con3",c3);
                intent.putExtra("subname",sub);
                intent.putExtra("lul",levelnam);
                intent.putExtra("lulu",idiot);//levlid
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
    }
}
