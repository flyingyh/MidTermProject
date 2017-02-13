package com.qf.midtermproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class VpArticleAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> vpData;

    public VpArticleAdapter(FragmentManager fm,Context context,List<Fragment> vpData) {
        super(fm);
        this.vpData = vpData;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return vpData.get(position);
    }

    @Override
    public int getCount() {
        return vpData.size();
    }
}
