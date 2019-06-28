package com.example.deerg.papercrunch;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    MainActivity one;
    Main2Activity two;
    Context mContext;
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
    public static List<String> c1,c2,c3;
    public static String concept1;
    public static String concept2;
    public static String concept3;
    public static String subn;
    public static String lvln;
    public static String question[];
    public static String option1[];
    public static String option2[];
    public static String option3[];

    public static String answer[];
    public static String hint[];


    public static String subname;
    public static String lvlname;
    public static int sid;
    public static int levid;
    public static int subid=15;
    public static int cnt=0;
    DataDbHelper dh=new DataDbHelper(this);
    SQLiteDatabase db;
    LevelDbHelper levelDbHelper;
    public static List<String> lev;
    public static List<String> head2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept_screen);
        mContext=this;
        setuptoolbar();

        Intent getinfo = getIntent();
        concept1 = getinfo.getExtras().getString("con1");
        concept2 = getinfo.getExtras().getString("con2");
        concept3 = getinfo.getExtras().getString("con3");
        subn = getinfo.getExtras().getString("subname");
        lvln = getinfo.getExtras().getString("levelname");
        sid=getinfo.getExtras().getInt("levelid");

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
        two=new Main2Activity();
        levelDbHelper=new LevelDbHelper(this);
        mContext=this;

        c1=SubLevel.concept1;
        c2=SubLevel.concept2;
        c3=SubLevel.concept3;
        final int id=levelDbHelper.getcurrlev(one.datavase);
        lev = levelDbHelper.readSubLevel(one.datavase,id);
        head2=levelDbHelper.getprev(one.datavase,id);

        listchild.put(listheader.get(0),lev);
        listchild.put(listheader.get(1),head2);

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
                    intent = new Intent(ConceptScreen.this,ConceptScreen.class);
                    intent.putExtra("con1",c1.get(childPosition));
                    intent.putExtra("con2",c2.get(childPosition));
                    intent.putExtra("con3",c3.get(childPosition));
                    intent.putExtra("subname",lev.get(childPosition));
                    intent.putExtra("levelid",id);
                    intent.putExtra("lul",lvln);
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
                    two.recreate();
                    mContext.startActivity(intent);

                }
                finish();
                return true;
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition==0 || groupPosition==1)
                {
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
        vp=findViewById(R.id.vp_question);
        adapter=new SliderFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        quiztime=(Button)findViewById(R.id.btnQuiz);

        subname = subn;
        lvlname = lvln;


        subid = levelDbHelper.readSubid(subname,one.datavase);
        cnt=dh.readCount(subid,db);


        question=new String[cnt];
        option1=new String[cnt];
        option2=new String[cnt];
        option3=new String[cnt];

        answer=new String[cnt];
        hint=new String[cnt];


        question=dh.readQuestion(subid,db);
        option1=dh.readOption1(subid,db);
        option2=dh.readOption2(subid,db);
        option3=dh.readOption3(subid,db);

        answer=dh.readAnswer(subid,db);
        hint=dh.readHint(subid,db);



        TextView tv1 = (TextView)findViewById(R.id.tv_subname);
        TextView tv2 = (TextView)findViewById(R.id.tv_lvlname);

        tv1.setText(subname);
        tv2.setText(lvln);

        levid = levelDbHelper.getlevid(lvln,one.datavase);

        if(ConceptScreen.cnt==0)
        {
            quiztime.setText("Next");
        }

        quiztime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConceptScreen.cnt>0) {
                    int check = levelDbHelper.getbool(subid,one.datavase);
                    if(check==0){
                        int prog =1;
                        prog = levelDbHelper.getprogress(one.datavase,levid);
                        int size = lev.size();
                        switch (size){
                            case 3 : levelDbHelper.changeprogress(levid,one.datavase,prog+=17);break;
                            default: levelDbHelper.changeprogress(levid,one.datavase,prog+=(50/size));break;

                        }

                        levelDbHelper.updatebool(subid,one.datavase,1);
                    }

                    Intent i = new Intent(getApplicationContext(), QuestionScreen.class);
                    i.putExtra("subname", subname);
                    startActivity(i);
                    finish();
                }
                else
                {
                    quiztime.findViewById(R.id.btnQuiz);
                    int check = levelDbHelper.getbool(subid,one.datavase);
                    if(check==0){
                        int prog =1;
                        prog = levelDbHelper.getprogress(one.datavase,levid);
                        int size = lev.size();
                        switch (size){
                            case 3 : levelDbHelper.changeprogress(levid,one.datavase,prog+=32);break;
                            default: levelDbHelper.changeprogress(levid,one.datavase,prog+=(100/size));break;
                        }
                        if(prog>95){
                            levelDbHelper.changeprogress(levid,one.datavase,prog=100);
                        }

                    }
                    levelDbHelper.updatebool(subid,one.datavase,1);
                    Intent intent1 = new Intent(ConceptScreen.this, SubLevel.class);
                    intent1.putExtra("Level1",two.card1.get(levid).getlevelnum());
                    intent1.putExtra("Levelname",two.card1.get(levid).getlevelname());
                    intent1.putExtra("img",two.card1.get(levid).getimg());
                    intent1.putExtra("prog",two.card1.get(levid).geprog());
                    intent1.putExtra("id",levid);
                    startActivity(intent1);

                    finish();
                }
            }
        });

    }
    public void setuptoolbar() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            custom_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.custom_toolbar);
        }
        setSupportActionBar(custom_toolbar);
    }
    @Override
    public void onBackPressed(){

        Intent intent1 = new Intent(ConceptScreen.this, SubLevel.class);
        intent1.putExtra("Level1",two.card1.get(levid).getlevelnum());
        intent1.putExtra("Levelname",two.card1.get(levid).getlevelname());
        intent1.putExtra("img",two.card1.get(levid).getimg());
        intent1.putExtra("prog",two.card1.get(levid).geprog());
        intent1.putExtra("id",levid);
        startActivity(intent1);


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
        Intent i=new Intent(ConceptScreen.this,IdScreen.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}
