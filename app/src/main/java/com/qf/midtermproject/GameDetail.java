package com.qf.midtermproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.util.HandlerNetUtil;
import com.qf.util.StaticData;

/**
 * Created by Administrator on 2016/11/23.
 */

public class GameDetail extends AppCompatActivity {
    private ImageView iv_detail;
    private TextView tv_name,tv_name2,tv_rel,tv_made,tv_web,tv_data,tv_ter,tv_type;
    private WebView wv_detail;
    private Intent intent;
    private ActionBar actionBar;
    private String url;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.hide();
//        actionBar.setDefaultDisplayHomeAsUpEnabled(true);//设置actionbar图标可点击，并且在左边添加一个箭头

        setContentView(R.layout.game_detail_layout);

        init();

        intent = getIntent();

        getInfo();

        url = intent.getStringExtra("bbs");
        String imageUrl = intent.getStringExtra("lit");
        String resultUrl = StaticData.MAINURL + imageUrl ;
        wv_detail.getSettings().setJavaScriptEnabled(true);
        wv_detail.loadUrl(url);
        wv_detail.setWebViewClient(new HelloWebViewClient());

        HandlerNetUtil.doNetWork(resultUrl, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
                if(bytes != null){
                    iv_detail.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
                }
            }
        });

    }

    public void init(){
        layout = (LinearLayout) findViewById(R.id.layout);
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        wv_detail = (WebView) findViewById(R.id.wv_detail);
        tv_data= (TextView) findViewById(R.id.tv_gamedata);
        tv_name= (TextView) findViewById(R.id.tv_gamename);
        tv_name2= (TextView) findViewById(R.id.tv_gamename2);
        tv_rel= (TextView) findViewById(R.id.tv_gameresl);
        tv_web= (TextView) findViewById(R.id.tv_gameweb);
        tv_made= (TextView) findViewById(R.id.tv_gameop);
        tv_ter= (TextView) findViewById(R.id.tv_gametre);
        tv_type= (TextView) findViewById(R.id.tv_gametype);
    }

    public void getInfo(){
        tv_name.setText(intent.getStringExtra("name"));
        tv_name2.setText("游戏名称："+intent.getStringExtra("name"));
        tv_type.setText("游戏类型："+intent.getStringExtra("type"));
        tv_rel.setText("发行厂商："+intent.getStringExtra("relse"));
        tv_made.setText("生产厂商："+intent.getStringExtra("made"));
        tv_web.setText("官方网站："+intent.getStringExtra("web"));
        tv_data.setText("发行日期："+intent.getStringExtra("date"));
        tv_ter.setText("游戏平台："+intent.getStringExtra("ter"));
    }

    //web视图
    class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    }

    public void returnClick(View view){
        finish();
    }

//    //设置webview的回退jian键
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if((keyCode == KeyEvent.KEYCODE_BACK && wv_detail.canGoBack())){
//            wv_detail.goBack();
//            return true;
//        }
//        return false;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home://actionbar添加的向左的箭头的id
//                onBackPressed();//相当于点击了手机上的物理返回键
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        if(action == MotionEvent.ACTION_DOWN){
//            if(actionBar.isShowing()){
//                actionBar.hide();
//            }else {
//                actionBar.show();
//            }
//        }
//        return super.onTouchEvent(event);
//    }

//    //解决菜单不显示图标问题
//    private void setIconEnable(Menu menu, boolean enable)
//    {
//        try
//        {
//            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
//            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
//            m.setAccessible(true);
//
//            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
//            m.invoke(menu, enable);
//
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }


}


