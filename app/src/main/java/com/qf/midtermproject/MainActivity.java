package com.qf.midtermproject;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qf.util.StaticData;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
                                            ,RadioGroup.OnCheckedChangeListener{
    private SharedPreferences sp;
    private ActionBar actionBar;
    private RadioGroup rg_main;
    private RadioButton rb_bbs;
    private RadioButton rb_game;
    private RadioButton rb_article;
    private TextView tv_main;
    private FragmentManager manager;
    private ArticleFragment articleFragment;
    private BbsFragment bbsFragment;
    private GameFragment gameFragment;
    private FrameLayout fg_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(StaticData.FILENAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("msg",false);
        editor.commit();

        init();

        rb_article.setChecked(true);
        rb_article.setTextColor(Color.GREEN);

        rg_main.setOnCheckedChangeListener(this);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        articleFragment = new ArticleFragment();
        transaction.add(R.id.fg_main,articleFragment);
        bbsFragment = new BbsFragment();
        transaction.add(R.id.fg_main,bbsFragment);
        gameFragment = new GameFragment();
        transaction.add(R.id.fg_main,gameFragment);
        transaction.show(articleFragment);
        transaction.hide(bbsFragment);
        transaction.hide(gameFragment);
        transaction.commit();

    }

    public void init(){
        tv_main = (TextView)findViewById(R.id.tv_main);
        rb_bbs = (RadioButton)findViewById(R.id.rb_bbs);
        rb_game = (RadioButton)findViewById(R.id.rb_game);
        rb_article = (RadioButton)findViewById(R.id.rb_article);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setAllColor();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId){
           case R.id.rb_article:
               transaction.show(articleFragment);
               transaction.hide(bbsFragment);
               transaction.hide(gameFragment);
               rb_article.setTextColor(Color.GREEN);
               tv_main.setText(rb_article.getText().toString());
               break;
           case R.id.rb_bbs:
               transaction.show(bbsFragment);
               transaction.hide(articleFragment);
               transaction.hide(gameFragment);
               rb_bbs.setTextColor(Color.GREEN);
               tv_main.setText(rb_bbs.getText().toString());
               break;
           case R.id.rb_game:
               transaction.show(gameFragment);
               transaction.hide(bbsFragment);
               transaction.hide(articleFragment);
               rb_game.setTextColor(Color.GREEN);
               tv_main.setText(rb_game.getText().toString());
               break;
           default:
               break;
        }
        transaction.commit();
    }

    public void setAllColor(){
        rb_article.setTextColor(Color.WHITE);
        rb_bbs.setTextColor(Color.WHITE);
        rb_game.setTextColor(Color.WHITE);
    }

}
