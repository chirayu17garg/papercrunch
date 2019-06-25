package com.example.deerg.papercrunch;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.deerg.papercrunch.ConceptScreen.lev;
import static com.example.deerg.papercrunch.ConceptScreen.levid;
import static com.example.deerg.papercrunch.ConceptScreen.subid;
import static com.example.deerg.papercrunch.ConceptScreen.subname;
import static com.example.deerg.papercrunch.Main2Activity.card1;

public class Ans_Popup extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    public static int tries=0;
    MainActivity one;
    LevelDbHelper levelDbHelper;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans__popup);
        one =new MainActivity();
        levelDbHelper=new LevelDbHelper(this);
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

        tv1=(TextView)findViewById(R.id.tv_correct);
        tv2=(TextView)findViewById(R.id.tv_hint1);

        iv1=(ImageView)findViewById(R.id.star1_on);
        iv2=(ImageView)findViewById(R.id.star1_off);
        iv3=(ImageView)findViewById(R.id.star2_on);
        iv4=(ImageView)findViewById(R.id.star2_off);
        iv5=(ImageView)findViewById(R.id.star3_on);
        iv6=(ImageView)findViewById(R.id.star3_off);

        String ans=ConceptScreen.answer[QuetionFragmentAdapter.index];

        if(QuestionFragment.ab.equals(ans))
        {
            tv1.setText("Yay That's Correct!!");
            tv2.setVisibility(View.INVISIBLE);
            //store stars in database remaining
            if(tries==0)
            {
                iv1.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.VISIBLE);
                iv5.setVisibility(View.VISIBLE);
            }
            else if(tries==1)
            {
                iv1.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.VISIBLE);
                iv6.setVisibility(View.VISIBLE);
            }
            else
            {
                iv1.setVisibility(View.VISIBLE);
                iv4.setVisibility(View.VISIBLE);
                iv6.setVisibility(View.VISIBLE);
            }
            tries=0;
            iv=(ImageView)findViewById(R.id.iv_next);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(ConceptScreen.cnt==(QuetionFragmentAdapter.index+1))
                    {
                        QuetionFragmentAdapter.index=0;
                        int check = levelDbHelper.getbool(subid,one.datavase);
                        if(check==1){
                            int prog =1;
                            prog = levelDbHelper.getprogress(one.datavase,levid);
                            int size = lev.size();
                            switch (size){
                                case 3 : levelDbHelper.changeprogress(levid,one.datavase,prog+=17);break;
                                default: levelDbHelper.changeprogress(levid,one.datavase,prog+=(50/size));break;

                            }
                            if(prog>95){
                                levelDbHelper.changeprogress(levid,one.datavase,prog=100);
                            }

                        }
                        levelDbHelper.updatebool(subid,one.datavase,2);

                        Intent intent = new Intent(getApplicationContext(),SubLevel.class);
                        intent.putExtra("Level1",card1.get(levid).getlevelnum());
                        intent.putExtra("Levelname",card1.get(levid).getlevelname());
                        intent.putExtra("img",card1.get(levid).getimg());
                        intent.putExtra("prog",card1.get(levid).geprog());
                        intent.putExtra("id",levid);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        QuetionFragmentAdapter.index++;
                        Intent intent6=new Intent(getApplicationContext(),QuestionScreen.class);
                        intent6.putExtra("subname",subname);
                        startActivity(intent6);
                        finish();
                    }
                }
            });

        }
        else
        {
            tries++;
            tv1.setText("Not Quite!, Try Again");
            tv2.setText(ConceptScreen.hint[QuetionFragmentAdapter.index]);

            iv=(ImageView)findViewById(R.id.iv_next);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    finish();
                }
            });
        }
    }
}
