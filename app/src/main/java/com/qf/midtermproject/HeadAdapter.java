package com.qf.midtermproject;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qf.bean.HeadBean;
import com.qf.util.HandlerNetUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */

public class HeadAdapter extends PagerAdapter {
    private List<HeadBean> headData;
    private Context context;

    public HeadAdapter(List<HeadBean> headData, Context context) {
        this.headData = headData;
        this.context = context;
    }


    @Override
    public int getCount() {
        return headData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.i("----instantiateItem",headData.size()+"");

        final ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        HeadBean bean = new HeadBean();
        String url = headData.get(position).getPic();
        HandlerNetUtil.doNetWork(url, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
                if(bytes != null){
                    img.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
                }
            }
        });
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
