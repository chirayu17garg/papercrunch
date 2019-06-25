package com.example.deerg.papercrunch;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class QuetionFragmentAdapter extends FragmentPagerAdapter {

    public static int index=0;
    // public static int slide=1;

    public QuetionFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {

        QuestionFragment frag1=new QuestionFragment();
        Bundle bundle1=new Bundle();
        i=index;
        int a=i+1;
        bundle1.putString("msg0","QUESTION "+a);
        bundle1.putString("msg1",ConceptScreen.question[i]);
        bundle1.putString("opt1",ConceptScreen.option1[i]);
        bundle1.putString("opt2",ConceptScreen.option2[i]);
        bundle1.putString("opt3",ConceptScreen.option3[i]);

        frag1.setArguments(bundle1);
        return frag1;
    }

    @Override
    public int getCount() {

        return 1;

    }
}
