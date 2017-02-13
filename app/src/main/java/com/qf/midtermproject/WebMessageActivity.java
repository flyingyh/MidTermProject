package com.qf.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/11/24.
 */

public class WebMessageActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private WebView wv_message;
    private String id;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.article_messege_layout);

        wv_message = (WebView)findViewById(R.id.wv_message);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        id = intent.getStringExtra("id");

        wv_message.getSettings().setJavaScriptEnabled(true);
        wv_message.loadUrl(url);
        wv_message.setWebViewClient( new WebViewClient());


    }

    //返回按钮
    public void BtReturnClick(View view){
        finish();
    }

    public void BtMessageCilck(View view){
        Intent intent = new Intent(this,CommentActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
}
