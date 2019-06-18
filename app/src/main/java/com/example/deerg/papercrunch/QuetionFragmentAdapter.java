package com.example.deerg.papercrunch;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class QuetionFragmentAdapter extends FragmentPagerAdapter {

    public QuetionFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        QuestionFragment frag1=new QuestionFragment();
        Bundle bundle1=new Bundle();
        if(i==0)
        {
            bundle1.putString("msg1","Which of the following is not a valid variable name in C? ");
            bundle1.putString("opt1","A) Django");
            bundle1.putString("opt2","B)1Python *");
            bundle1.putString("opt3","C)_Jojah");
        }
        else if (i==1)
        {
            bundle1.putString("msg1","Which is a valid variable name?");
            bundle1.putString("opt1","A) uufhe,de");
            bundle1.putString("opt2","B)3kodikom__23");
            bundle1.putString("opt3","C)forensic *");
        }
        else if(i==2)
        {
            bundle1.putString("msg1","Question test 3");
            bundle1.putString("opt1","A) option 1");
            bundle1.putString("opt2","B) option 2");
            bundle1.putString("opt3","C) option 3");
        }
        frag1.setArguments(bundle1);
        return frag1;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

