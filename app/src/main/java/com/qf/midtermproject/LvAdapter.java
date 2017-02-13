package com.qf.midtermproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.bean.ListViewBean;
import com.qf.util.HandlerNetUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class LvAdapter extends BaseAdapter {
    private List<ListViewBean> lvData;
    private Context context;

    public LvAdapter(List<ListViewBean> lvData, Context context) {
        this.lvData = lvData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lvData.size();
    }

    @Override
    public Object getItem(int position) {
        return lvData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView iv;
        TextView tv_title,tv_data,tv_number;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_item_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView)convertView.findViewById(R.id.iv);
            viewHolder.tv_data = (TextView)convertView.findViewById(R.id.tv_data);
            viewHolder.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.tv_number = (TextView)convertView.findViewById(R.id.tv_number);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ListViewBean bean = lvData.get(position);
        viewHolder.tv_data.setText(bean.getPubdate());
        viewHolder.tv_number.setText(bean.getFeedback());
        viewHolder.tv_title.setText(bean.getTitle());

        String url = bean.getLitpic();
        Log.i("----url",url);
        final ImageView imageView =  viewHolder.iv;

        HandlerNetUtil.doNetWork(url, new HandlerNetUtil.DataCallBack() {
            @Override
            public void doData(byte[] bytes) {
                if(bytes!=null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
        return convertView;
    }
}
