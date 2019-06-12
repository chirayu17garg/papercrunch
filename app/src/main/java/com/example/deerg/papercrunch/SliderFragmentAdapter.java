package com.example.deerg.papercrunch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SliderFragmentAdapter extends FragmentPagerAdapter {
    public SliderFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        ConceptFragment frag =new ConceptFragment();
        Bundle bundle=new Bundle();
        i=i+1;
        bundle.putString("msg","Data "+i);
        frag.setArguments(bundle);

        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

