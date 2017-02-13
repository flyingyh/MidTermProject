package com.qf.midtermproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> viewpageData;

    public MyAdapter(FragmentManager fm, List<Fragment> viewpageData) {
        super(fm);
        this.viewpageData = viewpageData;
    }
    @Override
    public Fragment getItem(int position) {
        return viewpageData.get(position);
    }

    @Override
    public int getCount() {
        return viewpageData.size();
    }
}
