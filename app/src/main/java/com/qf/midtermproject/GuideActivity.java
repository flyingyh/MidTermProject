package com.qf.midtermproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qf.util.StaticData;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页面
 */
public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager vp;
    private Button bt_enter;
    private ViewPagerAdapter vpAdapter;
    private List<Integer> vpData;//viewpager的相关图片
    private boolean isFirst = true;//判断是否为第一次进入
    private SharedPreferences sp;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_guide);

        sp = getSharedPreferences(StaticData.FILENAME,MODE_PRIVATE);
        isFirst = sp.getBoolean("msg",true);

        if(isFirst){
        }else{
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        initPager();//初始化viewpager
        vp.addOnPageChangeListener(this);

        vpAdapter = new ViewPagerAdapter(this,vpData);
        vp.setAdapter(vpAdapter);
    }

    public void initPager(){
        vp = (ViewPager) findViewById(R.id.vp);
        bt_enter = (Button) findViewById(R.id.bt_enter);

        vpData = new ArrayList<>();
        vpData.add(R.drawable.bj);
        vpData.add(R.drawable.bj2);
        vpData.add(R.drawable.bj3);
    }

    //进入主界面的按钮
    public void BtEnterClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position == (vpData.size()-1)){
            bt_enter.setVisibility(View.VISIBLE);
        }else{
            bt_enter.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
