package com.qf.midtermproject;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> vpData;

    public ViewPagerAdapter(Context context, List<Integer> vpData) {
        this.context = context;
        this.vpData = vpData;
    }

    @Override
    public int getCount() {
        return vpData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        img.setImageResource(vpData.get(position));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
