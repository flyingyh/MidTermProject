package com.qf.midtermproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.bean.GridViewBean;
import com.qf.util.HandlerNetUtil;
import com.qf.util.StaticData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<GridViewBean> gvData;
    private Context context;

    public GridViewAdapter(List<GridViewBean> gvData, Context context) {
        this.gvData = gvData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gvData==null?0:gvData.size();
    }

    @Override
    public Object getItem(int position) {
        return gvData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView iv_game;
        TextView tv_game;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.gridview_item_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.iv_game = (ImageView)convertView.findViewById(R.id.iv_game);
            viewHolder.tv_game = (TextView)convertView.findViewById(R.id.tv_game);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GridViewBean bean = gvData.get(position);
        viewHolder.tv_game.setText(bean.getTitle());
        String url = StaticData.MAINURL+bean.getLitpic();

        final ImageView imageView =  viewHolder.iv_game;

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
