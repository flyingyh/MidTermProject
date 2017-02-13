package com.qf.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.qf.bean.CommentBean;
import com.qf.util.HandlerNetUtil;
import com.qf.util.RemoveBomUtil;
import com.qf.util.StaticData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CommentActivity extends AppCompatActivity {
    private String id,url;
    private EditText et_comment;
    private ListView lv_comment;
    private LvCommentAdapter lvCommentAdapter;
    private List<CommentBean> commentData = new ArrayList<>();
    private List<CommentBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);

        et_comment = (EditText)findViewById(R.id.et_comment);
        lv_comment = (ListView)findViewById(R.id.lv_comment);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        url = StaticData.PATH3(id);
        Down(url);
        lvCommentAdapter = new LvCommentAdapter(commentData,this);
        lv_comment.setAdapter(lvCommentAdapter);


    }

    public void Down(String url){
        HandlerNetUtil.doNetWork(url, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
                if(bytes != null){
                    String str = new String(bytes);
                    String res = RemoveBomUtil.removeBom(str);
                    list = new ArrayList<>();
                    try {
                        JSONObject obj = new JSONObject(res);
                        Log.i("mm","sdff1"+obj);
                        JSONObject obj2 = obj.optJSONObject("description");
                        Log.i("mm","sdff2"+obj2);
                        JSONArray array = obj2.optJSONArray("data");
                        Log.i("mm","sdff3"+array.length());
                        for (int i = 0; i <array.length() ; i++) {
                            JSONObject obj3 = array.getJSONObject(i);
                            CommentBean bean = new CommentBean();
                            bean.setUsername(obj3.optString("username"));
                            bean.setFloor(obj3.optString("floor"));
                            bean.setMsg(obj3.optString("msg"));
                            list.add(bean);
                        }
                        commentData.addAll(list);
                        lvCommentAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    //返回按钮
    public void BtClick(View view){
        finish();
    }

    public void bt_commit(View view){
//        Toast.makeText(this, "aaaaaaaa", Toast.LENGTH_SHORT).show();
        String url2 = StaticData.PATH6;
        String msg = et_comment.getText().toString();
        OkHttpUtils.post().url(url2).addParams("aid",id).addParams("msg",msg).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(CommentActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, String s) {
                Toast.makeText(CommentActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }
        });
        lvCommentAdapter.notifyDataSetChanged();
    }
}
