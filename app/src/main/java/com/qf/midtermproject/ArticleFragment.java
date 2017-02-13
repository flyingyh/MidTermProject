package com.qf.midtermproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ArticleFragment extends Fragment implements
        RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    private int i,n;
    private boolean flag = true;

    private FragmentManager manager;
    private View view;
    private HorizontalScrollView hsv;//上面的水平滚动控件
    private List<Fragment> vpData = new ArrayList<>();//用来存放下方滚动的
    private VpArticleAdapter vpadapter;
    private ViewPager vp_article;
    private RadioGroup rg;
    private RadioButton btn,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;

    public ArticleFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.article_fragment,container,false);

        init();//初始化
        initListener();//绑定监听器

        btn.setChecked(true);//设置第一个默认选中
        btn.setTextColor(Color.RED);

        initData();//适配器添加数据

        manager = getFragmentManager();
        vpadapter = new VpArticleAdapter(manager,getActivity(),vpData);
        vpadapter.notifyDataSetChanged();
        vp_article.setAdapter(vpadapter);


        return view;
    }

    public void init(){
        rg = (RadioGroup)view.findViewById(R.id.rg);
        btn = (RadioButton) view.findViewById(R.id.btn);
        btn1 = (RadioButton)view.findViewById(R.id.btn1);
        btn2 = (RadioButton)view.findViewById(R.id.btn2);
        btn3 = (RadioButton)view.findViewById(R.id.btn3);
        btn4 = (RadioButton)view.findViewById(R.id.btn4);
        btn5 = (RadioButton)view.findViewById(R.id.btn5);
        btn6 = (RadioButton)view.findViewById(R.id.btn6);
        btn7 = (RadioButton)view.findViewById(R.id.btn7);
        btn8 = (RadioButton)view.findViewById(R.id.btn8);
        btn9 = (RadioButton)view.findViewById(R.id.btn9);
        hsv = (HorizontalScrollView)view.findViewById(R.id.hsv);
        vp_article =(ViewPager)view.findViewById(R.id.vp_article);
    }
    public void initListener(){
        rg.setOnCheckedChangeListener(this);
        vp_article.addOnPageChangeListener(this);
    }

    public void initData(){
        List<Fragment> data = new ArrayList<>();
        for (int i = 0; i <rg.getChildCount() ; i++) {
            //添加到viewpager中
            data.add(new BlankFragment());
        }
        vpData.addAll(data);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setAllColor();
//        RadioButton button = (RadioButton) group.getChildAt(i);
        switch (checkedId){
            case R.id.btn:
                i=1;
                btn.setTextColor(Color.RED);
                break;
            case R.id.btn1:
                i=2;
                btn1.setTextColor(Color.RED);
                break;
            case R.id.btn2:
                i=151;
                btn2.setTextColor(Color.RED);
                break;
            case R.id.btn3:
                i=152;
                btn3.setTextColor(Color.RED);
                break;
            case R.id.btn4:
                i=153;
                btn4.setTextColor(Color.RED);
                break;
            case R.id.btn5:
                i=154;
                btn5.setTextColor(Color.RED);
                break;
            case R.id.btn6:
                i=196;
                btn6.setTextColor(Color.RED);
                break;
            case R.id.btn7:
                i=197;
                btn7.setTextColor(Color.RED);
            case R.id.btn8:
                i=199;
                btn8.setTextColor(Color.RED);
                break;
            case R.id.btn9:
                i=25;
                btn9.setTextColor(Color.RED);
                break;

        }
//
//                lvData.clear();
//                initLvData(i,n);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
     */
    @Override
    public void onPageSelected(int position) {
        setAllColor();
        btn.setTextColor(Color.RED);
        if (position == 0) {
            btn.setChecked(true);
            btn.setTextColor(Color.RED);
        }else if (position == 1) {
            btn1.setChecked(true);
            btn1.setTextColor(Color.RED);
        }else if (position == 2) {
            btn2.setChecked(true);
            btn2.setTextColor(Color.RED);
        }else if (position == 3) {
            btn3.setChecked(true);
            btn3.setTextColor(Color.RED);
        }else if (position == 4) {
            btn4.setChecked(true);
            btn4.setTextColor(Color.RED);
        }else if (position == 5) {
            btn5.setChecked(true);
            btn5.setTextColor(Color.RED);
        }else if (position == 6) {
            btn6.setChecked(true);
            btn6.setTextColor(Color.RED);
        }else if (position == 7) {
            btn7.setChecked(true);
            btn7.setTextColor(Color.RED);
        }else if (position == 8) {
            btn8.setChecked(true);
            btn8.setTextColor(Color.RED);
        }else if (position == 9) {
            btn9.setChecked(true);
            btn9.setTextColor(Color.RED);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setAllColor(){
        btn.setTextColor(Color.BLACK);
        btn1.setTextColor(Color.BLACK);
        btn2.setTextColor(Color.BLACK);
        btn3.setTextColor(Color.BLACK);
        btn4.setTextColor(Color.BLACK);
        btn5.setTextColor(Color.BLACK);
        btn6.setTextColor(Color.BLACK);
        btn7.setTextColor(Color.BLACK);
        btn8.setTextColor(Color.BLACK);
    }


}
