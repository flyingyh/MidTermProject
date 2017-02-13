package com.qf.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qf.bean.GridViewBean;
import com.qf.util.HandlerNetUtil;
import com.qf.util.RemoveBomUtil;
import com.qf.util.StaticData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class GameFragment extends Fragment
        implements AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener,
        PullToRefreshBase.OnRefreshListener2{
    private int i,page;

    private Spinner sp_game;
    private List<String> spData;
    private ArrayAdapter<String> spadapter;

    private PullToRefreshGridView gv_game;
    private GridViewAdapter gvadapter;
    private List<GridViewBean> gvData = new ArrayList<>();

    public GameFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_layout,container,false);

        sp_game = (Spinner)view.findViewById(R.id.sp_game);
        spData = Arrays.asList("游戏","动作(ACT)","射击(FPG)","角色扮演(RPG)","养成(GAL)",
                "益智(PUZ)", "即时战略(RTS)", "策略(SLG)","体育(SPG)","模拟经营(SIM)","赛车(RAC)",
                 "冒险(AVG)","动作角色(ARPG)");
        spadapter = new ArrayAdapter<String>((MainActivity)getActivity(),android.R.layout.simple_list_item_1,spData);
        sp_game.setAdapter(spadapter);
        sp_game.setSelection(0,true);
        sp_game.setOnItemSelectedListener(this);

        gv_game = (PullToRefreshGridView)view.findViewById(R.id.gv_game);
        gv_game.setOnRefreshListener(this);
        gvadapter = new GridViewAdapter(gvData,(MainActivity)getActivity());
        gv_game.setAdapter(gvadapter);
//        gv_game.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent((MainActivity)getActivity(),GameDetail.class);
        GridViewBean bean = gvData.get(position);
        intent.putExtra("name",bean.getTitle());
        intent.putExtra("type",bean.getTypename());
        intent.putExtra("relse",bean.getRelease_company());
        intent.putExtra("made",bean.getMade_company());
        intent.putExtra("web",bean.getWebsit());
        intent.putExtra("date",bean.getRelease_date());
        intent.putExtra("ter",bean.getTerrace());
        intent.putExtra("bbs",bean.getGame_bbs());
        intent.putExtra("lit",bean.getLitpic());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        page = 1;
        String str = (String)sp_game.getItemAtPosition(position);
        if (str.equals("游戏")){
            i=179;
        }else if (str.equals("动作(ACT)")){
            i=181;
        }else if (str.equals("射击(FPG)")){
            i=182;
        }else if (str.equals("角色扮演(RPG)")){
            i=183;
        }else if (str.equals("养成(GAL)")){
            i=184;
        }else if (str.equals("益智(PUZ)")) {
            i = 185;
        }else if (str.equals("即时战略(RTS)")){
            i=186;
        }else if (str.equals("策略(SLG)")){
            i=187;
        }else if (str.equals("体育(SPG)")){
            i=188;
        }else if (str.equals("模拟经营(SIM)")){
            i=189;
        }else if (str.equals("赛车(RAC)")) {
            i = 190;
        }else if (str.equals("冒险(AVG)")){
            i=191;
        }else if (str.equals("动作角色(ARPG)")){
            i=192;
        }
        gvData.clear();
        jsonGame(i,page);
        gvadapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void jsonGame(int i,int page){
        String url = StaticData.url(i,1);
        HandlerNetUtil.doNetWork(url, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
//                Log.i("info","========11");
                if(bytes != null){
//                    Log.i("info","========12");
                    String str = new String(bytes);
                    String str2 = RemoveBomUtil.removeBom(str);
//                    Log.i("info","========13"+str2);

                    List<GridViewBean> data = new ArrayList<GridViewBean>();

                    JSONObject object = null;
                    try {
                        object = new JSONObject(str2);
                        JSONObject ob2 = object.getJSONObject("data");
                        for (int i = 0; i < 12; i++) {
                            JSONObject ob3 = ob2.getJSONObject(i + "");
                            GridViewBean bean = new GridViewBean();
                            bean.setTitle(ob3.getString("title"));
                            bean.setData(ob3.getString("senddate"));
                            bean.setClick(ob3.getString("click"));
                            bean.setTypename(ob3.getString("typename"));
                            bean.setLitpic(ob3.getString("litpic"));
                            bean.setType(ob3.getString("typeid"));
                            bean.setId(ob3.getString("id"));
                            bean.setDescription(ob3.getString("description"));
                            bean.setWebsit(ob3.getString("websit"));
                            bean.setGame_bbs(ob3.getString("game_bbs"));
                            bean.setMade_company(ob3.getString("made_company"));
                            bean.setRelease_company(ob3.getString("release_company"));
                            bean.setTerrace(ob3.getString("terrace"));
                            bean.setRelease_date(ob3.getString("release_date"));
                            data.add(bean);
                        }
                        gvData.addAll(data);
                        Log.i("info","========14"+gvData);
                        gvadapter.notifyDataSetChanged();
                        gv_game.onRefreshComplete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        gv_game.setOnItemClickListener(this);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        gvData.clear();
        jsonGame(i,page);

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        jsonGame(i,page++);
    }
}
