package com.qf.midtermproject;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qf.bean.HeadBean;
import com.qf.bean.ListViewBean;
import com.qf.util.HandlerNetUtil;
import com.qf.util.RemoveBomUtil;
import com.qf.util.StaticData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements ViewPager.OnPageChangeListener
                ,AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener2{
    private int i=1,n=1;
    private String url2,id2;
    private PullToRefreshListView lv_article;
    private List<ListViewBean> lvData ;
    private LvAdapter lvAdapter;

    private ViewPager vp_head;
    private HeadAdapter headAdapter;
    private List<HeadBean> headData;

    private RadioGroup rg;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int curIndex = msg.what;
            vp_head.setCurrentItem(curIndex);
            int nextIndex = 0;
            if(headData.size()!=0){
                nextIndex = (curIndex+1)%headData.size();
            }
            handler.sendEmptyMessageDelayed(nextIndex,3000);
        }
    };

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_item_layout,null);

//        View v = inflater.inflate(R.layout.article_fragment,null);
//        rg = (RadioGroup)v.findViewById(R.id.rg);
//        rg.setOnCheckedChangeListener(this);

//        lv_article = (ListView) view.findViewById(R.id.lv_article);
        lv_article = (PullToRefreshListView)view.findViewById(R.id.lv_article);
        lv_article.setMode(PullToRefreshBase.Mode.BOTH);//设置刷新模式带有上拉加载（默认只有下拉刷新）
        lv_article.setOnRefreshListener(this);//绑定下拉刷新和下拉加载的监听器
        ListView listView = lv_article.getRefreshableView();//获取到刷新控件里面自带的listview


        View head = inflater.inflate(R.layout.head_layout,null);
        vp_head = (ViewPager) head.findViewById(R.id.vp_head);
        headData = new ArrayList<>();
        headAdapter = new HeadAdapter(headData,getActivity());
        vp_head.setAdapter(headAdapter);
        vp_head.addOnPageChangeListener(this);
        listView.addHeaderView(head); //给listview添加头部
        initHeadData();

        lvData = new ArrayList<>();
        lvAdapter = new LvAdapter(lvData,(MainActivity)getActivity());
        lv_article.setAdapter(lvAdapter);
        lv_article.setOnItemClickListener(this);
        initLvData(i,n);



        return view;
    }

    public void initLvData(int i,int n){
        String path = StaticData.PATH(i,1);
        HandlerNetUtil.doNetWork(path, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
                if(bytes!=null){
                    String content = new String(bytes);
                    content = RemoveBomUtil.removeBom(content);
//                    Log.i("info","========"+content);
                    try {
                        List<ListViewBean> list = new ArrayList<ListViewBean>();
                        ListViewBean bean = null;
                        JSONObject obj = new JSONObject(content);
                        JSONObject data = obj.getJSONObject("data");

                        for (int i = 0; i >=0 ; i++) {
                            JSONObject result = data.optJSONObject(i+"");
                            if(result == null)
                                break;
                            bean = new ListViewBean();
//                            Log.i("====result",result.toString());

//                            int x=0;
//                            Log.i("----",(x++)+"");
                            url2 = result.getString("arcurl");
                            bean.setArcurl(url2);
//                            Log.i("----","++"+url2);
                            id2 = result.getString("id");
                            bean.setId(id2);
//                            Log.i("----","++"+id2);
                            String img = result.getString("litpic");
//                            Log.i("----",(x++)+"");
                            bean.setLitpic(StaticData.HEAD_PIC+img);
//                            Log.i("----",(x++)+"");
                            bean.setPubdate(result.getString("pubdate"));
//                            Log.i("----",(x++)+"");
                            bean.setFeedback(result.getString("feedback"));
//                            Log.i("----",(x++)+"");
                            bean.setTitle(result.getString("title"));
//                            Log.i("----",(x++)+"");
                            list.add(bean);
                        }
//                        Log.i("====out1",lvData.size()+"");
                        lvData.addAll(list);
//                        Log.i("====out2",lvData.size()+"");
                        lvAdapter.notifyDataSetChanged();
                        lv_article.onRefreshComplete();
                    } catch (Exception e) {
//                        Log.i("====out3","JSON erro");
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public void initHeadData(){
        HandlerNetUtil.doNetWork(StaticData.PATH5, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
                if(bytes != null){
                    String str = new String(bytes);
                    List<HeadBean> list = new ArrayList<HeadBean>();
                    try {
                        JSONArray array = new JSONArray(str);
//                        Log.i("====array",array.length()+"");
                        for (int j = 0; j <array.length() ; j++) {
                            JSONObject obj = array.getJSONObject(j);
                            String res = obj.getString("pic");
                            Log.i("info","++++++res"+res);
                            HeadBean bean = new HeadBean();
                            bean.setPic(res);
                            list.add(bean);
                        }
                        headData.addAll(list);
                        headAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),WebMessageActivity.class);
        intent.putExtra("url",lvData.get(position-2).getArcurl());
        intent.putExtra("id",lvData.get(position-2).getId());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        int index = vp_head.getCurrentItem();
        handler.sendEmptyMessage(index);
    }

    //下拉刷新的时候会调用的方法
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //请求最新的数据
        n = 1;
        initLvData(i,n);
    }

    //上拉加载的时候会调用的方法
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //请求下一页的数据
        lvData.clear();
        initLvData(i,n++);
    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId){
//            case btn:
//                i=1;
//                break;
//            case btn1:
//                i=2;
//                break;
//            case btn2:
//                i=151;
//                break;
//            case btn3:
//                i=152;
//                break;
//            case btn4:
//                i=153;
//                break;
//            case btn5:
//                i=154;
//                break;
//            case btn6:
//                i=196;
//                break;
//            case btn7:
//                i=197;
//            case btn8:
//                i=199;
//                break;
//            case btn9:
//                i=25;
//                break;
//            default:
//                break;
//        }
//        lvData.clear();
//        initLvData(i,n);
//    }


}
